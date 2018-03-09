package com.aws.alexa.service.config;

import com.amazon.speech.speechlet.verifier.TimestampSpeechletRequestVerifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class AlexaConfiguration {

    @Bean
    public TimestampSpeechletRequestVerifier timestampSpeechletRequestVerifier() {
        return new TimestampSpeechletRequestVerifier(60, TimeUnit.SECONDS);
    }
}
