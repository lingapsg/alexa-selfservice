package com.aws.alexa.service.util;

import com.aws.alexa.service.domain.request.SelfServiceRequest;
import com.aws.alexa.service.domain.response.AlexaOutputSpeech;
import com.aws.alexa.service.domain.response.SelfServiceResponse;
import com.aws.alexa.service.domain.response.SpeechType;

public class AlexaSpeechOutputFactory {

    public static AlexaOutputSpeech getAlexaOutput(SpeechType speechType, String speechText) {
        return new AlexaOutputSpeech(speechType, speechText, null);
    }

    public static boolean checkAccountLinked(SelfServiceRequest alexaRequest) {
//         return alexaRequest.session.user.accessToken != null;
        return true;
    }
}
