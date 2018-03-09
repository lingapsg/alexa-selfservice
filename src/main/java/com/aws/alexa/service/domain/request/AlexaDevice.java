package com.aws.alexa.service.domain.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AlexaDevice {

    public final String deviceId;
    public final AlexaContext.AlexaSupportedInterfaces supportedInterfaces;

    public AlexaDevice(@JsonProperty("deviceId") String deviceId,
                       @JsonProperty("supportedInterfaces") AlexaContext.AlexaSupportedInterfaces supportedInterfaces) {
        this.deviceId = deviceId;
        this.supportedInterfaces = supportedInterfaces;
    }
}
