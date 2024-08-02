package com.smbackoffice.exception;

public class MessageNotFoundException extends BusinessException{

    public MessageNotFoundException() {
        super("Message Not Found");
    }
}
