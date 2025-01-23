package com.xplug.tech.exception;


public class ItemCannotBeDeletedException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public ItemCannotBeDeletedException(String message) {
        super(message + " cannot be deleted!!!");
    }
}
