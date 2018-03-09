package com.aws.alexa.service.domain.response;

import com.fasterxml.jackson.annotation.JsonInclude;

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
}
