package com.aws.alexa.service.domain.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@JsonInclude(NON_NULL)
public class AlexaResponse {

    public final AlexaOutputSpeech outputSpeech;
    public final AlexaCard card;
    public final AlexaOutputSpeech reprompt;
    public final boolean shouldEndSession;
    public final DeviceDirective[] directives;

    public AlexaResponse(AlexaOutputSpeech outputSpeech,
                         AlexaCard card,
                         AlexaOutputSpeech reprompt,
                         boolean shouldEndSession,
                         DeviceDirective[] directives) {
        this.outputSpeech = outputSpeech;
        this.card = card;
        this.reprompt = reprompt;
        this.shouldEndSession = shouldEndSession;
        this.directives = directives;
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
