package com.xplug.tech.crop.management.exceptions;

/**
 * @author Nyabinde Nyasha
 * @created 12/18/2020
 * @project procurement-system
 */

public class ItemCannotBeDeletedException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public ItemCannotBeDeletedException(String message) {
        super(message + " cannot be deleted!!!");
    }
}
