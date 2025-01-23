package com.xplug.tech.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.SERVICE_UNAVAILABLE)
public class SystemErrorException extends RuntimeException{

    public SystemErrorException(String msg){
        super(msg);
    }

}
