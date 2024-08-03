package com.smbackoffice.entity;

import com.smbackoffice.enums.StatusType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "MESSAGE")
public class Message extends BaseEntity {

    @Column(length = 200, nullable = false)
    private String content;

    @Column(nullable = false)
    private Integer recipientPhoneNumber;

    @Column
    private Boolean isWhats = Boolean.FALSE;

    @Enumerated(EnumType.STRING)
    @Column(length = 1)
    private StatusType status;

}
