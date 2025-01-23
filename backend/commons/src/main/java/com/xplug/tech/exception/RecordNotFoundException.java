package com.xplug.tech.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
@Slf4j
public class RecordNotFoundException extends RuntimeException {
    public RecordNotFoundException(String msg){
        super(msg);
        log.warn(msg);
    }
}
