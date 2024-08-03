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
    private Long creditValue;

    @Override
    public PlanType accountType() {
        return PlanType.PRE;
    }

    @Override
    public Account addingInitialValue() {
        this.creditValue = 100L;
        return this;
    }

    @Override
    public Long getBalance() {
        return this.creditValue;
    }
}
