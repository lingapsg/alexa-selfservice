package com.aws.alexa.service.domain.request;

import com.amazon.speech.speechlet.Application;
import com.amazon.speech.speechlet.User;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AlexaSystem {

    public final String apiAccessToken;
    public final String apiEndpoint;
    public final Application application;
    public final AlexaDevice device;
    public final User user;

    public AlexaSystem(@JsonProperty("apiAccessToken") String apiAccessToken,
                       @JsonProperty("apiEndpoint") String apiEndpoint,
                       @JsonProperty("application") Application application,
                       @JsonProperty("device") AlexaDevice device,
                       @JsonProperty("user") User user) {
        this.apiAccessToken = apiAccessToken;
        this.apiEndpoint = apiEndpoint;
        this.application = application;
        this.device = device;
        this.user = user;
    }
}
