package com.aws.alexa.service.domain.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AlexaAudioplayer {

    public final String token;
    public final long offsetInMilliseconds;
    public final String playerActivity;

    public AlexaAudioplayer(@JsonProperty("token") String token,
                            @JsonProperty("offsetInMilliseconds") long offsetInMilliseconds,
                            @JsonProperty("playerActivity") String playerActivity) {
        this.token = token;
        this.offsetInMilliseconds = offsetInMilliseconds;
        this.playerActivity = playerActivity;
    }
}
