package com.smsender.builder;

import com.smsender.entity.Message;
import com.smsender.enums.StatusType;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MessageBuilder {
    private String content;
    private Integer recipientPhoneNumber;
    private Boolean isWhats;
    private StatusType status;

    public MessageBuilder content(String content) {
        this.content = content;
        return this;
    }

    public MessageBuilder recipientPhoneNumber(Integer recipientPhoneNumber) {
        this.recipientPhoneNumber = recipientPhoneNumber;
        return this;
    }

    public MessageBuilder isWhats(Boolean isWhats) {
        this.isWhats = isWhats;
        return this;
    }

    public MessageBuilder status(StatusType status) {
        this.status = status;
        return this;
    }

    public Message build() {
        return new Message(this);
    }
}
