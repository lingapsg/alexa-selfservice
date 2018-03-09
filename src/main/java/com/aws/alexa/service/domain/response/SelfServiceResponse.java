package com.aws.alexa.service.domain.response;

import com.aws.alexa.service.domain.response.AlexaResponse;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

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
