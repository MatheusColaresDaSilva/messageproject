package com.smsender.service;

import com.smsender.entity.Account;
import com.smsender.entity.AccountPos;
import com.smsender.entity.AccountPre;
import com.smsender.entity.Plan;

public class AccountFactory {

    public static Account createAccount(Plan plan) {
        switch (plan.getType()) {
            case POS:
                return new AccountPos();
            case PRE:
                return new AccountPre();
            default:
                throw new IllegalArgumentException("Invalid plan type: " + plan.getType());
        }
    }
}
