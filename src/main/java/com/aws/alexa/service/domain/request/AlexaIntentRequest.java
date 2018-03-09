package com.aws.alexa.service.domain.request;

import com.amazon.speech.slu.Intent;
import com.amazon.speech.speechlet.IntentRequest;

import java.util.Date;
import java.util.Locale;

public class AlexaIntentRequest extends IntentRequest {
    /**
     * Protected constructor used for JSON serialization and for extending this class.
     *
     * @param requestId   the request identifier
     * @param timestamp   the request timestamp
     * @param locale      the locale of the request
     * @param intent      the intent to handle
     * @param dialogState
     */
    protected AlexaIntentRequest(String requestId, Date timestamp, Locale locale, Intent intent, DialogState dialogState) {
        super(requestId, timestamp, locale, intent, dialogState);
    }
}
