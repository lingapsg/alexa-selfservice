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

import static com.aws.alexa.service.util.AlexaSpeechOutputFactory.getAlexaOutput;

@Repository
public class InvoiceRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(InvoiceRepository.class);

    public AlexaOutputSpeech getInvoiceResponse(Intent intent, SelfServiceRequest selfServiceRequest) {
        Slot monthSlot = intent.getSlot("month");
        if (monthSlot != null && monthSlot.getValue() != null) {
            LOGGER.info(monthSlot.getValue());
            if (!ValidationUtil.isValidMonth(monthSlot.getValue())) {
                return getAlexaOutput(SpeechType.PLAINTEXT, "I can't find invoice for the given month. Please provide a valid month");
            }
            return getAlexaOutput(SpeechType.PLAINTEXT, "Your invoice amount is 300 SEK for the month of " + monthSlot.getValue());
        } else {
            return getAlexaOutput(SpeechType.PLAINTEXT, "Which month's invoice amount you are looking for ?");
        }
    }
}
