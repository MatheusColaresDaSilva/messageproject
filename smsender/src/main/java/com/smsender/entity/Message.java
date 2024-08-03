package com.smsender.entity;

import com.smsender.builder.MessageBuilder;
import com.smsender.enums.StatusType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "MESSAGE")
@NoArgsConstructor
@AllArgsConstructor
public class Message extends BaseEntity {

    @Column(length = 200, nullable = false)
    private String content;

    @Column(nullable = false)
    private Integer recipientPhoneNumber;

    @Column
    private Boolean isWhats;

    @Enumerated(EnumType.STRING)
    @Column(length = 1)
    private StatusType status;

    public Message(MessageBuilder builder) {
        this.content = builder.getContent();
        this.recipientPhoneNumber = builder.getRecipientPhoneNumber();
        this.isWhats = builder.getIsWhats();
        this.status = builder.getStatus();
    }

    public static MessageBuilder builder() {
        return new MessageBuilder();
    }

}
