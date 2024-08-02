package com.smbackoffice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "ACCOUNTPRE")
public class AccountPre extends Account {

    @Column(nullable = false)
    private Long creditValue;
}
