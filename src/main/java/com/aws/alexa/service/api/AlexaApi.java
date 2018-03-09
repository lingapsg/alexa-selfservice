package com.aws.alexa.service.api;

import com.amazon.speech.speechlet.*;
import com.amazon.speech.speechlet.interfaces.system.Error;
import com.amazon.speech.speechlet.interfaces.system.ErrorType;
import com.amazon.speech.speechlet.verifier.TimestampSpeechletRequestVerifier;
import com.aws.alexa.service.application.AlexaService;
import com.aws.alexa.service.domain.request.AlexaRequest;
import com.aws.alexa.service.domain.request.AlexaSession;
import com.aws.alexa.service.domain.request.SelfServiceRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.ISO8601DateFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Date;
import java.util.Locale;

import static java.util.Objects.nonNull;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("/api/v1/self-service")
public class AlexaApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(AlexaApi.class);

    private static final String appId = "amzn1.ask.skill.488e82f8-4b65-42b8-8b39-362f6eef7057";

    private final TimestampSpeechletRequestVerifier timestampSpeechletRequestVerifier;
    private final AlexaService alexaService;

    @Autowired
    public AlexaApi(TimestampSpeechletRequestVerifier timestampSpeechletRequestVerifier,
                    AlexaService alexaService) {
        this.timestampSpeechletRequestVerifier = timestampSpeechletRequestVerifier;
        this.alexaService = alexaService;
    }

    @RequestMapping(method = POST, produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity getResponse(@RequestBody SelfServiceRequest alexaRequest) {
        LOGGER.info("Request :" + alexaRequest);
        LOGGER.info("Request Intent type :" + alexaRequest.request.type);
        CoreSpeechletRequest speechletRequest = deserialize(alexaRequest.request);
        if (!verifyRequest(alexaRequest, speechletRequest)) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(alexaService.getServiceResponse(speechletRequest, alexaRequest));
    }

    private boolean verifyRequest(SelfServiceRequest alexaRequest, CoreSpeechletRequest speechletRequest) {
        if (alexaRequest.session.isNew) {
            // make initialization
        }
        return alexaRequest.session.application.getApplicationId().equals(appId);
        /*return timestampSpeechletRequestVerifier.verify(
                speechletRequest,
                convertToAmazonSession(alexaRequest.session));*/
    }

    private Session convertToAmazonSession(AlexaSession alexaSession) {
        return Session.builder()
                .withIsNew(alexaSession.isNew)
                .withSessionId(alexaSession.sessionId)
                .withApplication(alexaSession.application)
                .withAttributes(alexaSession.attributes)
                .withUser(
                        com.amazon.speech.speechlet.User.builder()
                                .withUserId(alexaSession.user.userId)
                                .withAccessToken(alexaSession.user.accessToken)
                                .withPermissions(alexaSession.user.permissions)
                                .build()
                ).build();
    }

    private CoreSpeechletRequest deserialize(AlexaRequest alexaRequest) {
        ObjectMapper mapper = new ObjectMapper();
        if (alexaRequest.type.equalsIgnoreCase("LaunchRequest")) {
            return LaunchRequest.builder()
                    .withLocale(new Locale(alexaRequest.locale))
                    .withRequestId(alexaRequest.requestId)
                    .withTimestamp(getDateFromISOString(alexaRequest.timestamp))
                    .build();
        } else if (alexaRequest.type.equalsIgnoreCase("IntentRequest")) {
            return IntentRequest.builder()
                    .withLocale(new Locale(alexaRequest.locale))
                    .withRequestId(alexaRequest.requestId)
                    .withTimestamp(getDateFromISOString(alexaRequest.timestamp))
                    .withDialogState(
                            nonNull(alexaRequest.dialogState) ?
                                    IntentRequest.DialogState.valueOf(alexaRequest.dialogState) : null
                    )
                    .withIntent(alexaRequest.intent)
                    .build();
        } else if (alexaRequest.type.equalsIgnoreCase("SessionEndedRequest")) {
            LOGGER.info("Deserializing SessionEndedRequest");
            return SessionEndedRequest.builder()
                    .withLocale(new Locale(alexaRequest.locale))
                    .withRequestId(alexaRequest.requestId)
                    .withTimestamp(getDateFromISOString(alexaRequest.timestamp))
                    .withReason(SessionEndedRequest.Reason.valueOf(alexaRequest.reason))
                    .withError(
                            nonNull(alexaRequest.error) ? Error.builder()
                                    .withMessage(alexaRequest.error.error)
                                    .withType(ErrorType.valueOf(alexaRequest.error.type))
                                    .build() : null)
                    .build();
        } else {
            return null;
        }
    }

    private Date getDateFromISOString(String dateString) {
        try {
            return new ISO8601DateFormat().parse(dateString);
        } catch (Exception e) {
            return null;
        }
    }
}
