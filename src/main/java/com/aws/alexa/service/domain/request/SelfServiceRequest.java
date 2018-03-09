package com.aws.alexa.service.domain.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class SelfServiceRequest {

    public final String version;
    public final AlexaSession session;
    public final AlexaContext context;
    public final AlexaRequest request;

    public SelfServiceRequest(@JsonProperty("version") String version,
                              @JsonProperty("session") AlexaSession session,
                              @JsonProperty("context") AlexaContext context,
                              @JsonProperty("request") AlexaRequest request) {
        this.version = version;
        this.session = session;
        this.context = context;
        this.request = request;
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
