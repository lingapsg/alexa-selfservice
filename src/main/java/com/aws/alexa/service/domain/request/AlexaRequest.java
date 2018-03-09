package com.aws.alexa.service.domain.request;

import com.amazon.speech.slu.Intent;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class AlexaRequest {
    public final String type;
    public final String requestId;
    public final String timestamp;
    public final String locale;
    public final String dialogState;
    public final Intent intent;
    public final String reason;
    public final Error error;

    public AlexaRequest(@JsonProperty("type") String type,
                        @JsonProperty("requestId") String requestId,
                        @JsonProperty("timestamp") String timestamp,
                        @JsonProperty("locale") String locale,
                        @JsonProperty("dialogState") String dialogState,
                        @JsonProperty("intent") Intent intent,
                        @JsonProperty("reason") String reason,
                        @JsonProperty("error") Error error) {
        this.type = type;
        this.requestId = requestId;
        this.timestamp = timestamp;
        this.locale = locale;
        this.dialogState = dialogState;
        this.intent = intent;
        this.reason = reason;
        this.error = error;
    }

    public static class Error {

        public final String type;
        public final String message;

        public Error(@JsonProperty("type") String type, @JsonProperty("message") String message) {
            this.type = type;
            this.message = message;
        }
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
