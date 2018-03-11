package com.aws.alexa.service.repository;

import com.amazon.speech.slu.Intent;
import com.amazon.speech.slu.Slot;
import com.aws.alexa.service.domain.request.SelfServiceRequest;
import com.aws.alexa.service.domain.response.AlexaOutputSpeech;
import com.aws.alexa.service.domain.response.SpeechType;
import com.aws.alexa.service.util.ValidationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.Calendar;

import static com.aws.alexa.service.util.AlexaSpeechOutputFactory.getAlexaOutput;

@Repository
public class DataRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(DataRepository.class);

    public AlexaOutputSpeech getDataResponse(Intent intent, SelfServiceRequest alexaRequest) {
        Slot monthSlot = intent.getSlot("month");
        if (intent.getSlot("month") != null && intent.getSlot("month").getValue() == null) {
            return getMonthsDataResponse(ValidationUtil.getMonthByIndex(Calendar.getInstance().get(Calendar.MONTH)));
        } else {
            if (monthSlot != null && monthSlot.getValue() != null) {
                return getMonthsDataResponse(monthSlot.getValue());
            }
        }
        return getAlexaOutput(SpeechType.PLAINTEXT, "Hey I can help you to get your data usage");
    }

    private AlexaOutputSpeech getMonthsDataResponse(String month) {
        String outputText = "You have 300 MB data left for the month of " + ValidationUtil.getMonthByOthers(month);
        return getAlexaOutput(SpeechType.PLAINTEXT, outputText);
    }

    public AlexaOutputSpeech sendDataResponse(Intent intent, SelfServiceRequest alexaRequest) {
        Slot receiverMsisdnSlot = intent.getSlot("receiverMsisdn");
        Slot dataSlot = intent.getSlot("data");
        if (alexaRequest.session.attributes.get("receiverMsisdn") == null || receiverMsisdnSlot == null || receiverMsisdnSlot.getValue() == null) {
            return getAlexaOutput(SpeechType.PLAINTEXT, "Please provide mobile number and amount of mobile data to transfer");
        } else if (dataSlot == null || dataSlot.getValue() == null) {
            return getAlexaOutput(SpeechType.PLAINTEXT, "Please provide amount of data to transfer.");
        } else {
            if (ValidationUtil.isValidMsisdn(receiverMsisdnSlot.getValue()) && ValidationUtil.isValidData(dataSlot.getValue())) {
                alexaRequest.session.attributes.remove("receiverMsisdn");
                return getAlexaOutput(SpeechType.PLAINTEXT,
                        String.format("We have transferred %s to mobilenumber %s", dataSlot.getValue(), receiverMsisdnSlot.getValue()));
            } else {
                return getAlexaOutput(SpeechType.PLAINTEXT, "You have not provided valid input to transfer data");
            }
        }
    }
}
