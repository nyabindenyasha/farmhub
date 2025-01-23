package com.xplug.tech.notifications.email.core;

import lombok.Data;

@Data
public final class Body {

    /**
     * This is the html representation of the email message body.
     */
    private String message;

    public Body(String message) {
        this.message = message;
    }

    public Body() {
    }
}
