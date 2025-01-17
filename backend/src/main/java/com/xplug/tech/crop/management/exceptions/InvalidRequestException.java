package com.xplug.tech.crop.management.exceptions;

/**
 * @author Nyabinde Nyasha
 * @created 12/18/2020
 * @project procurement-system
 */


public class InvalidRequestException extends RuntimeException {

    public InvalidRequestException(String message) {
        super(message);
    }

}
