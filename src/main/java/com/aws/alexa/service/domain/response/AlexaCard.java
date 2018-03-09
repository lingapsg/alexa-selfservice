package com.aws.alexa.service.domain.response;

import com.aws.alexa.service.domain.response.CardType;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

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
