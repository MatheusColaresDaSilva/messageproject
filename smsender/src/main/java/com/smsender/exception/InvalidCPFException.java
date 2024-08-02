package com.smsender.exception;

public class InvalidCPFException extends BusinessException{

    public InvalidCPFException() {
        super("Invalid CPF");
    }
}
