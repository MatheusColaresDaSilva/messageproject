package com.smbackoffice.service;

import com.smbackoffice.entity.Account;
import com.smbackoffice.entity.AccountPre;
import com.smbackoffice.exception.InsufficientFundsException;

public class PreDeductStrategy implements AccountDeductionStrategy {

    @Override
    public Double deductValue(Double value, Account account) throws IllegalArgumentException, InsufficientFundsException {
        if (!(account instanceof AccountPre)) {
            throw new IllegalArgumentException("Invalid account type");
        }

        AccountPre accountPre = (AccountPre) account;
        if (value == null) {
            throw new IllegalArgumentException("Value cannot be null.");
        }

        Double newValue = accountPre.getCreditValue() - value;
        if (value > accountPre.getCreditValue() || newValue < 0) {
            throw new InsufficientFundsException("Insufficient credit value.");
        }

        accountPre.setCreditValue(newValue);
        return newValue;
    }
}
