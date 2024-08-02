package com.smsender.exception;

public class InvalidEmailException extends BusinessException{

    public InvalidEmailException() {
        super("Email Inválido");
    }
}
