package com.smbackoffice.entity;

import com.smsender.enums.PlanType;
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
    private Long maximumValue;

    @Override
    public Long deductValue(Long value) {
        return this.maximumValue - value;
    }
}
