package com.xplug.tech.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class RecordAlreadyExistException extends RuntimeException {
    public RecordAlreadyExistException(String msg) {
        super(msg);
        log.warn(msg);
    }
}
