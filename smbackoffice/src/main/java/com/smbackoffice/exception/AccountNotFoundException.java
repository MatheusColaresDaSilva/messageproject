package com.smbackoffice.exception;

public class AccountNotFoundException extends BusinessException{

    public AccountNotFoundException() {
        super("Account Not Found");
    }
}
