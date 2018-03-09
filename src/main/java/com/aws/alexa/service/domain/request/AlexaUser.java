package com.aws.alexa.service.domain.request;

import com.amazon.speech.speechlet.Permissions;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

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
