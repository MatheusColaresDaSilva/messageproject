package com.smbackoffice.service;

import com.smbackoffice.entity.Account;
import com.smbackoffice.exception.InsufficientFundsException;

public interface AccountDeductionStrategy {

    Double deductValue(Double value, Account account) throws IllegalArgumentException, InsufficientFundsException;
}
