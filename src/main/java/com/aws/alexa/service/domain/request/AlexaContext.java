package com.aws.alexa.service.domain.request;

import com.amazon.speech.speechlet.Interface;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class AlexaContext {

    public final AlexaSystem system;
    public final AlexaAudioplayer audioPlayer;
    public final Object display;

    public AlexaContext(@JsonProperty("System") AlexaSystem System,
                        @JsonProperty("AudioPlayer") AlexaAudioplayer audioPlayer,
                        @JsonProperty("Display") Object display) {
        this.system = System;
        this.audioPlayer = audioPlayer;
        this.display = display;
    }

    public static class AlexaSupportedInterfaces {

        public final Map<Class<? extends Interface>, Interface> supportedInterfaces;

        public AlexaSupportedInterfaces(@JsonProperty("supportedInterfaces") Map<Class<? extends Interface>, Interface> supportedInterfaces) {
            this.supportedInterfaces = supportedInterfaces;
        }
    }
}
