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
    private Double maximumValue;

    @Column(nullable = false)
    private Double currentValue = 0.0;

    @Override
    public PlanType accountType() {
        return PlanType.POS;
    }

    @Override
    public Account addingInitialValue() {
        maximumValue = 50.0;
        return this;
    }

    @Override
    public Double getBalance() {
        return this.currentValue;
    }

}
