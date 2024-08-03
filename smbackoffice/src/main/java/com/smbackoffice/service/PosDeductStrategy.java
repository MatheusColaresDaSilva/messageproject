package com.smbackoffice.service;

import com.smbackoffice.entity.Account;
import com.smbackoffice.entity.AccountPos;
import com.smbackoffice.entity.AccountPre;
import com.smbackoffice.exception.InsufficientFundsException;
import org.springframework.stereotype.Component;

public class PosDeductStrategy implements AccountDeductionStrategy {

    @Override
    public Double deductValue(Double value, Account account) throws IllegalArgumentException, InsufficientFundsException {
        if (!(account instanceof AccountPos)) {
            throw new IllegalArgumentException("Invalid account type");
        }

        AccountPos accountPre = (AccountPos) account;
        if (value == null || accountPre.getCurrentValue() == null) {
            throw new IllegalArgumentException("Value cannot be null.");
        }

        Double newValue = accountPre.getCurrentValue() + value;
        if (newValue > accountPre.getMaximumValue()) {
            throw new InsufficientFundsException("Impossible action, you pass the limit maximum to sending message.");
        }

        accountPre.setCurrentValue(newValue);
        return newValue;
    }
}
