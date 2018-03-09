package com.aws.alexa.service.util;

import com.aws.alexa.service.domain.response.AlexaOutputSpeech;
import com.aws.alexa.service.domain.response.SpeechType;

public class AlexaSpeechOutputFactory {

    public static AlexaOutputSpeech getAlexaOutput(SpeechType speechType, String speechText) {
        return new AlexaOutputSpeech(SpeechType.PLAINTEXT,"Please provide your mobile number.", null);
    }
}
