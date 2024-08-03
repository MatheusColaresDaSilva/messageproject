package com.smsender.entity;

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
    public PlanType accountType() {
        return PlanType.POS;
    }

    @Override
    public Account addingInitialValue() {
        maximumValue = 50L;
        return this;
    }

    @Override
    public Long getBalance() {
        return this.maximumValue;
    }

}
