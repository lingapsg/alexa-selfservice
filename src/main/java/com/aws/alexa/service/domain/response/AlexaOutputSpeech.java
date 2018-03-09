package com.aws.alexa.service.domain.response;

import com.aws.alexa.service.domain.response.SpeechType;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AlexaOutputSpeech {

    public final SpeechType type;
    public final String text;
    public final String ssml;

    @JsonCreator
    public AlexaOutputSpeech(SpeechType type, String text, String ssml) {
        this.type = type;
        this.text = text;
        this.ssml = ssml;
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
