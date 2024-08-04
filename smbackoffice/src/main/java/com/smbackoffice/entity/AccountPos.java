package com.smbackoffice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "ACCOUNTPOS")
public class AccountPos extends Account {

    @Column(nullable = false)
    private Double maximumValue;

    @Column(nullable = false)
    private Double currentValue = 0.0;

}
