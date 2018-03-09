package com.aws.alexa.service.application;

import com.amazon.speech.slu.Intent;
import com.amazon.speech.slu.Slot;
import com.amazon.speech.speechlet.CoreSpeechletRequest;
import com.amazon.speech.speechlet.IntentRequest;
import com.amazon.speech.speechlet.LaunchRequest;
import com.amazon.speech.speechlet.SessionEndedRequest;
import com.aws.alexa.service.domain.request.AlexaSession;
import com.aws.alexa.service.domain.request.SelfServiceRequest;
import com.aws.alexa.service.domain.response.AlexaOutputSpeech;
import com.aws.alexa.service.domain.response.AlexaResponse;
import com.aws.alexa.service.domain.response.SelfServiceResponse;
import com.aws.alexa.service.domain.response.SpeechType;
import com.aws.alexa.service.repository.DataRepository;
import com.aws.alexa.service.repository.InvoiceRepository;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.aws.alexa.service.util.AlexaSpeechOutputFactory.getAlexaOutput;

@Service
public class AlexaService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AlexaService.class);

    private final InvoiceRepository invoiceRepository;
    private final DataRepository dataRepository;

    @Autowired
    public AlexaService(InvoiceRepository invoiceRepository, DataRepository dataRepository) {
        this.invoiceRepository = invoiceRepository;
        this.dataRepository = dataRepository;
    }

    public SelfServiceResponse getServiceResponse(CoreSpeechletRequest request, SelfServiceRequest alexaRequest) {
        LOGGER.info(": In Service :"+alexaRequest.request.type);
        if (request instanceof LaunchRequest) {
            return getLaunchIntentResponse((LaunchRequest) request, alexaRequest);
        } else if (request instanceof IntentRequest) {
            return getIntentResponse((IntentRequest) request, alexaRequest);
        } else if (request instanceof SessionEndedRequest) {
            return getSessionEndedRequest((SessionEndedRequest) request, alexaRequest);
        } else {
            return null;
        }
    }

    private SelfServiceResponse getIntentResponse(IntentRequest intentRequest, SelfServiceRequest alexaRequest) {
        if (validateMsisdn(alexaRequest.session, intentRequest.getIntent())) {
            return new SelfServiceResponse(
                    alexaRequest.version,
                    alexaRequest.session.attributes,
                    new AlexaResponse(
                            getAlexaOutput(SpeechType.PLAINTEXT, "Please provide your mobile number"),
                            null,
                            getAlexaOutput(SpeechType.PLAINTEXT, "Please provide your mobile number"),
                            false,
                            null
                    ));
        }
        alexaRequest.session.attributes.put("previousIntent", intentRequest.getIntent().getName());
        LOGGER.info("Intent name is :"+intentRequest.getIntent().getName());
        if (intentRequest.getIntent().getName().equalsIgnoreCase("GetInvoiceIntent")) {
            return getInvoiceResponse(intentRequest, alexaRequest);
        } else if (intentRequest.getIntent().getName().equalsIgnoreCase("GetDataIntent")) {
            return getDataResponse(intentRequest, alexaRequest);
        } else if (intentRequest.getIntent().getName().equalsIgnoreCase("mobileNoIntent")) {
            String previousIntent = (String) alexaRequest.session.attributes.get("previousIntent");
            if (previousIntent.equalsIgnoreCase("GetInvoiceIntent")) {
                return getInvoiceResponse(intentRequest, alexaRequest);
            } else if (previousIntent.equalsIgnoreCase("GetDataIntent")) {
                return getDataResponse(intentRequest, alexaRequest);
            }
        }
        return getSelfServiceResponse(
                getAlexaOutput(SpeechType.PLAINTEXT, "Hey I can help you to get your invoice amount"),
                alexaRequest
        );
    }

    private SelfServiceResponse getDataResponse(IntentRequest intentRequest, SelfServiceRequest alexaRequest) {
        AlexaOutputSpeech outputSpeech = dataRepository.getDataResponse(intentRequest.getIntent(), alexaRequest);
        return getSelfServiceResponse(outputSpeech, alexaRequest);
    }

    private SelfServiceResponse getInvoiceResponse(IntentRequest intentRequest, SelfServiceRequest alexaRequest) {
        AlexaOutputSpeech outputSpeech = invoiceRepository.getInvoiceResponse(intentRequest.getIntent(), alexaRequest);
        return getSelfServiceResponse(outputSpeech, alexaRequest);
    }

    private SelfServiceResponse getSelfServiceResponse(AlexaOutputSpeech outputSpeech, SelfServiceRequest alexaRequest) {
        return new SelfServiceResponse(
                alexaRequest.version,
                alexaRequest.session.attributes,
                new AlexaResponse(outputSpeech, null, outputSpeech, false, null)
        );
    }

    private SelfServiceResponse getLaunchIntentResponse(LaunchRequest launchRequest, SelfServiceRequest alexaRequest) {
        return new SelfServiceResponse(alexaRequest.version, null, new AlexaResponse(
                new AlexaOutputSpeech(SpeechType.SSML, "<speak>Welcome to Mitt3. </speak>", "SSML"),
                null,
                new AlexaOutputSpeech(SpeechType.PLAINTEXT, "I can help you to get your invoice amount. Please provide your mobile number", null),
                false,
                null
        ));
    }

    private SelfServiceResponse getSessionEndedRequest(SessionEndedRequest sessionEndedRequest, SelfServiceRequest alexaRequest) {
        LOGGER.info("Considered as sessionEndedRequest");
        alexaRequest.session.attributes.clear();
        return new SelfServiceResponse(
                alexaRequest.version, alexaRequest.session.attributes,
                new AlexaResponse(
                        getAlexaOutput(SpeechType.PLAINTEXT, "Something went wrong"),
                        null,
                        null,
                        true,
                        null
                )
        );
    }

    private boolean validateMsisdn(AlexaSession session, Intent intent) {
        Slot msisdnSlot = intent.getSlot("msisdn");
        if (msisdnSlot != null && msisdnSlot.getValue() != null) {
            session.attributes.put("msisdn", msisdnSlot.getValue());
        }
        return StringUtils.isEmpty((String) session.attributes.get("msisdn"));
    }
}
