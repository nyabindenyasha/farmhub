package com.xplug.tech.notifications.email.context;

import com.xplug.tech.notifications.email.core.EmailHtmlRepresentationBuilder;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.InputStream;
import java.util.Date;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.Objects.nonNull;
import static org.springframework.mail.javamail.MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED;

@Slf4j
@Component
public class SmtpMailSender {

    private final JavaMailSender javaMailSender;

    private final EmailHtmlRepresentationBuilder emailHtmlRepresentationBuilder;

    @Value("${spring.mail.username}")
    private String emailFrom;

    @Value("${system.name}")
    private String systemName;

    @Autowired
    public SmtpMailSender(JavaMailSender javaMailSender, EmailHtmlRepresentationBuilder emailHtmlRepresentationBuilder) {
        this.javaMailSender = javaMailSender;
        this.emailHtmlRepresentationBuilder = emailHtmlRepresentationBuilder;
    }

    @SneakyThrows
    public void sendSimpleEmail(SendEmailRequest sendEmailRequest) {
        sendInternal(sendEmailRequest);
    }

    @SneakyThrows
    void sendInternal(SendEmailRequest sendEmailRequest) {
        log.info("### Processing email: {}", sendEmailRequest);
        log.info("emailFrom: {}", emailFrom);

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,
                MULTIPART_MODE_MIXED_RELATED,
                UTF_8.name());

        if (sendEmailRequest.getEmailRecipients().size() == 1) {
            addSubject(mimeMessageHelper, sendEmailRequest);
            addFrom(mimeMessageHelper, sendEmailRequest.getFrom(), emailFrom);
            mimeMessageHelper.setSentDate(new Date());
            mimeMessageHelper.setTo(sendEmailRequest.getRecipients());
//            mimeMessageHelper.setTo("nyabindenyasha@gmail.com");
            val htmlText = emailHtmlRepresentationBuilder.buildHtmlRepresentation(sendEmailRequest.getBody(), systemName);
            mimeMessageHelper.setText(htmlText, true);
            if (nonNull(sendEmailRequest.getAttachment())) {
                mimeMessageHelper.addAttachment(sendEmailRequest.getAttachment().getName(), sendEmailRequest.getAttachment().getFile());
            }
            javaMailSender.send(mimeMessage);
        } else {
            sendEmailRequest.getEmailRecipients().forEach(recipient -> {
                addSubject(mimeMessageHelper, sendEmailRequest);
                addFrom(mimeMessageHelper, sendEmailRequest.getFrom(), emailFrom);
                try {
                    mimeMessageHelper.setSentDate(new Date());
                    mimeMessageHelper.setFrom(emailFrom);
                    val htmlText = emailHtmlRepresentationBuilder.buildHtmlRepresentation(sendEmailRequest.getBody(), systemName);
                    mimeMessageHelper.setText(htmlText, true);
                    javaMailSender.send(mimeMessage);
                } catch (Exception e) {
                    log.info("### Exception: " + e.getMessage());
                }
            });

        }

        log.info("### Email sent");
    }

    @SneakyThrows
    private void addFrom(MimeMessageHelper mimeMessageHelper, String from, String fromPersonal) {
        try {

            if (nonNull(from))
                mimeMessageHelper.setFrom(from);

            else if (nonNull(fromPersonal))
                mimeMessageHelper.setFrom(fromPersonal);

            else
                mimeMessageHelper.setFrom(emailFrom);

        } catch (MessagingException e) {
            log.error("Failed to add FROM : {} to the mime message due to : {}" + e.getMessage());
        }
    }

    private void addSubject(MimeMessageHelper mimeMessageHelper, SendEmailRequest emailContext) {
        try {
            mimeMessageHelper.setSubject(emailContext.getSubject());
        } catch (MessagingException e) {
            log.error("Failed to add subject to the email; caused by : {}", e.getMessage());
        }
    }

    @SneakyThrows
    public void sendMultimediaEmail(SendEmailRequest sendEmailRequest) {

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper;

        helper = new MimeMessageHelper(message, true);
        helper.setTo(sendEmailRequest.getRecipients());
        helper.setFrom(emailFrom);
        helper.setSubject(sendEmailRequest.getSubject());
        helper.setText(sendEmailRequest.getBody(), true);
        javaMailSender.send(message);
    }

    @SneakyThrows
    public void sendEmailWithAttachment() {

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo("nyabindenyashae@gmail.com");
        helper.setFrom(emailFrom);
        helper.setSubject("Testing from Spring Boot");

        helper.setText("Check attachment for image!");

        helper.setText("<h1>Check attachment for image!</h1>", true);

        FileSystemResource file = new FileSystemResource(new File("classpath:android.png"));

        Resource resource = new ClassPathResource("android.png");
        InputStream input = resource.getInputStream();

        ResourceUtils.getFile("classpath:android.png");

        helper.addAttachment("my_photo.png", new ClassPathResource("android.png"));

        javaMailSender.send(message);

    }


}
