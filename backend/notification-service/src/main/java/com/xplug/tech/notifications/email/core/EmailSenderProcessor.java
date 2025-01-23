package com.xplug.tech.notifications.email.core;

@FunctionalInterface
public interface EmailSenderProcessor {

    void process(EmailContext emailContext);

}
