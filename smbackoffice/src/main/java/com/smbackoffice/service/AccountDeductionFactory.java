package com.smbackoffice.service;

import com.smbackoffice.entity.Account;
import com.smbackoffice.entity.AccountPos;
import com.smbackoffice.entity.AccountPre;

public class AccountDeductionFactory {

    public static AccountDeductionStrategy accountDeductionDiscover(Account account) {
        if (account instanceof AccountPre) {
            return new PreDeductStrategy();
        }
        if (account instanceof AccountPos) {
            return new PosDeductStrategy();
        }
        return null;
    }
}
