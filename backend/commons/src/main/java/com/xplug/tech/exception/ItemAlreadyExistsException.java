package com.xplug.tech.exception;


public class ItemAlreadyExistsException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public ItemAlreadyExistsException(String message) {
        super(message + " already exists!!!");
    }
}
