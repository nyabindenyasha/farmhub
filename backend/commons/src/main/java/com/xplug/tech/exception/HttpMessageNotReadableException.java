package com.xplug.tech.exception;

/**
 * @author Nyabinde Nyasha
 * @created 3/27/2021
 * @project procurement-system
 */

public class HttpMessageNotReadableException extends RuntimeException {

    public HttpMessageNotReadableException(String message) {
        super(message);
    }

}
