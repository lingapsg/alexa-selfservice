package com.aws.alexa.service.domain.response;

import com.fasterxml.jackson.annotation.JsonValue;

public enum SpeechType {

    PLAINTEXT("PlainText"), SSML("ssml");

    private final String type;

    SpeechType(String type) {
        this.type = type;
    }

    @JsonValue
    public String getType() {
        return type;
    }
}
