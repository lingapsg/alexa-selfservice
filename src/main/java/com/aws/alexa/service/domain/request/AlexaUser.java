package com.aws.alexa.service.domain.request;

import com.amazon.speech.speechlet.Permissions;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AlexaUser {

    public final String userId;
    public final String accessToken;
    public final Permissions permissions;

    public AlexaUser(@JsonProperty("userId") final String userId,
                     @JsonProperty("accessToken") final String accessToken,
                     @JsonProperty("permissions") final Permissions permissions) {
        this.userId = userId;
        this.accessToken = accessToken;
        this.permissions = permissions;
    }
}
