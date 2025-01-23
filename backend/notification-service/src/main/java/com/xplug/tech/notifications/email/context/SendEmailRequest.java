package com.xplug.tech.notifications.email.context;

import com.xplug.tech.notifications.email.core.Attachment;
import lombok.Data;

import java.util.Set;



@Data
public class SendEmailRequest {

    private String subject;

    private String body;

    private String from;

//    private Set<Attachment> attachments;

    private Attachment attachment;

//    private Set<EmailRecipient> emailRecipients;

    private Set<String> emailRecipients;

    String getFirstEmailFromSet() {
        return this.emailRecipients.stream().findFirst().get();
    }

    String getRecipients() {
        return String.join(", ", this.emailRecipients);
    }

}
