package com.smsender.exception;

public class PlanNotFoundException extends BusinessException{

    public PlanNotFoundException() {
        super("Plan Not Found");
    }
}
