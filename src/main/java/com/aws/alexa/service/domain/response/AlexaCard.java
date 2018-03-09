package com.aws.alexa.service.domain.response;

import com.aws.alexa.service.domain.response.CardType;

public class AlexaCard {

    public final CardType type;
    public final String title;
    public final String content;
    public final String text;

    public AlexaCard(CardType type, String title, String content, String text) {
        this.type = type;
        this.title = title;
        this.content = content;
        this.text = text;
    }
}
