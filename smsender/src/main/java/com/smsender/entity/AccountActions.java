package com.smsender.entity;

import com.smsender.enums.PlanType;

public interface AccountActions {

    PlanType accountType();

    Account addingInitialValue();

    Long getBalance();
}
