package com.aws.alexa.service.domain.response;

import com.fasterxml.jackson.annotation.JsonValue;

public enum CardType {

    SIMPLE("Simple"), STANDARD("Standard"), LINKACCOUNT("LinkAccount");

    private final String type;

    CardType(String type) {
        this.type = type;
    }

    @JsonValue
    public String getType() {
        return type;
    }
}
