package com.smsender.exception;

public class ClientPlanNotFoundException extends BusinessException{

    public ClientPlanNotFoundException() {
        super("Client Plan Not Found");
    }
}
