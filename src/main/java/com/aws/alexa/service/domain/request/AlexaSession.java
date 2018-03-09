package com.aws.alexa.service.domain.request;

import com.amazon.speech.speechlet.Application;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.nonNull;

public class AlexaSession {

    public final boolean isNew;
    public final String sessionId;
    public final Application application;
    public final Map<String, Object> attributes;
    public final AlexaUser user;


    public AlexaSession(@JsonProperty("new") final boolean isNew,
                        @JsonProperty("sessionId") final String sessionId,
                        @JsonProperty("application") final Application application,
                        @JsonProperty("attributes") final Map<String, Object> attributes,
                        @JsonProperty("user") final AlexaUser user) {
        this.isNew = isNew;
        this.sessionId = sessionId;
        this.application = application;
        this.attributes = nonNull(attributes) ? attributes : new HashMap<>();
        this.user = user;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public boolean equals(Object o) {
        return EqualsBuilder.reflectionEquals(this, o);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }
}
