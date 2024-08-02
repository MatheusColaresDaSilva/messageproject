package com.smsender.exception;

public class ClientNotFoundException extends BusinessException{

    public ClientNotFoundException() {
        super("Client Not Found");
    }
}
