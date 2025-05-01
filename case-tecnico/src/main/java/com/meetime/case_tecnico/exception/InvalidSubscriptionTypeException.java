package com.meetime.case_tecnico.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidSubscriptionTypeException extends RuntimeException{
    public InvalidSubscriptionTypeException(String message){super(message);}
}
