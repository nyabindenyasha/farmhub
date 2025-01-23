package com.xplug.tech.notifications.email.context;

import com.xplug.tech.notifications.email.core.Attachment;
import com.xplug.tech.notifications.email.core.Body;
import com.xplug.tech.notifications.email.core.EmailMessageFormatter;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.HashSet;
import java.util.Set;




@Slf4j
@Service
public class SendEmailServiceImpl implements SendEmailService {

    private final SmtpMailSender smtpMailSender;

    public SendEmailServiceImpl(SmtpMailSender smtpMailSender) {
        this.smtpMailSender = smtpMailSender;
    }

    @Override
    public SendEmailResponse sendEmail(SendEmailRequest sendEmailRequest) {
        smtpMailSender.sendSimpleEmail(sendEmailRequest);
        return SendEmailResponse.builder().message("Email(s) have been sent awaiting delivery").build();
    }

    @Override
    public SendEmailResponse sendEmail(SendEmailRequest emailRequest, Attachment[] attachments) {
        return null;
    }

    @Override
    public SendEmailResponse sendEmail(SendEmailRequest emailRequest, MultipartFile[] multipartFiles) {
        return null;
    }

    @Override
    public SendEmailResponse sendEmailTest() {
        SendEmailRequest emailRequest = new SendEmailRequest();

        val emailMessageFormatter = new EmailMessageFormatter();

        emailRequest.setSubject("Test Bonvie");

        emailMessageFormatter.addGreeting("Hie Damba");

        emailMessageFormatter.addParagraph("Welcome to the Bonvie Family.");
        emailMessageFormatter.addParagraph("An account has been created for you.");
        emailMessageFormatter.addParagraph("Please click the link below to verify your account");

        Body body = new Body(emailMessageFormatter.buildMessage());

        emailRequest.setBody(emailMessageFormatter.buildMessage());

        Set<String> emails = new HashSet<>();
//        emails.add("dambakudzai@gmail.com");
        emails.add("nyabindenyasha@gmail.com");
//        emails.add("terrymaff@gmail.com");
        emailRequest.setEmailRecipients(emails);


        Resource resource = new ClassPathResource("test123.txt");
        FileSystemResource file = new FileSystemResource(new File("classpath:android.png"));
        MultipartFile multipartFile = new ResourceMultipartFile(resource);

        Attachment attachment = new Attachment();
        attachment.setFile(multipartFile);
        emailRequest.setAttachment(attachment);
        smtpMailSender.sendSimpleEmail(emailRequest);
        return SendEmailResponse.builder().message("Email(s) have been sent awaiting delivery").build();
    }

    @Override
    public SendEmailResponse sendEmail(SendEmailRequestV3 sendEmailRequestV3) {
        return null;
    }
}
