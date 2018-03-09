package com.aws.alexa.service.domain.response;

import com.aws.alexa.service.domain.response.AlexaResponse;

import java.util.Map;

public class SelfServiceResponse {

    public final String version;
    public final Map<String, Object> sessionAttributes;
    public final AlexaResponse response;

    public SelfServiceResponse(String version, Map<String, Object> sessionAttributes, AlexaResponse response) {
        this.version = version;
        this.sessionAttributes = sessionAttributes;
        this.response = response;
    }
}
