package com.xplug.tech.exception;

/**
 * @author Nyabinde Nyasha
 * @created 3/23/2021
 * @project procurement-system
 */

public class MethodArgumentNotValidException extends RuntimeException {

    public MethodArgumentNotValidException(String message) {
        super(message);
    }
}
