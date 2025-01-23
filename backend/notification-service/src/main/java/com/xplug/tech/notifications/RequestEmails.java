package com.xplug.tech.notifications;

import org.springframework.core.io.Resource;

public record RequestEmails(String emails, Resource resource, String fileName) {

}
