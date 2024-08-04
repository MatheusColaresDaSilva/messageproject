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
@Table(name = "ACCOUNTPRE")
public class AccountPre extends Account {

    @Column(nullable = false)
    private Double creditValue;

    @Override
    public PlanType accountType() {
        return PlanType.PRE;
    }

    @Override
    public Account addingInitialValue() {
        this.creditValue = 100.0;
        return this;
    }

    @Override
    public Double getBalance() {
        return this.creditValue;
    }
}
