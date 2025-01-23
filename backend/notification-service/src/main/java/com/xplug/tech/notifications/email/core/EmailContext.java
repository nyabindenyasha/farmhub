package com.xplug.tech.notifications.email.core;

import java.util.Set;

public interface EmailContext {

    Set<EmailRecipient> getEmailRecipients();

    Set<Attachment> getAttachments();

    String getFrom();

    Body getBody();

    Subject getSubject();

    String getEmailFromHeader();


}
