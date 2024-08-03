package com.smsender.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//@Entity
//@Table(name = "TRANSACTION")
public class Transaction {

    //@OneToOne
    //@JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account account;

    //@OneToOne
    //@JoinColumn(name = "message_id", referencedColumnName = "id")
    private Message message;

    //@Column(nullable = false)
    private Long messageCost;

}
