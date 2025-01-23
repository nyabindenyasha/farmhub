package com.xplug.tech.notifications.email.context;

import com.xplug.tech.notifications.email.core.Attachment;
import org.springframework.web.multipart.MultipartFile;




public interface SendEmailService {

    SendEmailResponse sendEmail(SendEmailRequest sendEmailRequest);

    SendEmailResponse sendEmail(SendEmailRequest emailRequest, Attachment[] attachments);

    SendEmailResponse sendEmail(SendEmailRequest emailRequest, MultipartFile[] multipartFiles);

    SendEmailResponse sendEmailTest();

    SendEmailResponse sendEmail(SendEmailRequestV3 sendEmailRequestV3);
}
