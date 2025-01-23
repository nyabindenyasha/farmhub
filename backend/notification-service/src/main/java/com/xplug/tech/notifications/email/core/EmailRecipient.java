package com.xplug.tech.notifications.email.core;

import lombok.Data;

@Data
public final class EmailRecipient {

    private RecipientType type;

    private String emailAddress;

    public EmailRecipient() {
    }

    public EmailRecipient(String emailAddress) {
        this.emailAddress = emailAddress;
        this.type = RecipientType.TO;
    }
}
