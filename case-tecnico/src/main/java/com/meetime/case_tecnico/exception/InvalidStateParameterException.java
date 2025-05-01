package com.meetime.case_tecnico.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidStateParameterException extends RuntimeException{
    public InvalidStateParameterException(String message){super(message);}
}
