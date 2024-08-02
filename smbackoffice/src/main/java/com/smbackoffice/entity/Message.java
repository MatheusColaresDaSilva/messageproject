package com.smbackoffice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
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
    private Boolean isWhats;


}
