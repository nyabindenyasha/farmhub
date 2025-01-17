package com.xplug.tech.crop.management.exceptions;

/**
 * @author Nyabinde Nyasha
 * @created 12/18/2020
 * @project procurement-system
 */

public class ItemAlreadyExistsException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public ItemAlreadyExistsException(String message) {
        super(message + " already exists!!!");
    }
}
