package com.xplug.tech.notifications.email.context;

import com.xplug.tech.notifications.email.core.*;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Objects.isNull;
import static java.util.Objects.requireNonNull;



@Data
public class SendEmailRequestV3 implements EmailContext {

    private String body;

    private String subject;

    private String[] emailRecipients;

    private MultipartFile[] attachments;

    private String from;

    private String emailFromHeader;

    public Set<EmailRecipient> getEmailRecipients() {
        if (isNull(emailRecipients))
            return Collections.emptySet();
        return Stream.of(emailRecipients)
                .map(EmailRecipient::new)
                .collect(Collectors.toSet());
    }

    public Set<Attachment> getAttachments() {
        if (isNull(attachments))
            return Collections.emptySet();
        requireNonNull(attachments, "Email attachments should not be null");
        return Stream.of(attachments)
                .map(Attachment::new)
                .collect(Collectors.toSet());
    }

    public Body getBody() {
        return new Body(body);
    }

    public Subject getSubject() {
        return new Subject(subject);
    }

}
