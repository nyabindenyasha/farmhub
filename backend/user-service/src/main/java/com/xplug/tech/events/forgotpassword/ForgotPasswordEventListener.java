package com.xplug.tech.events.forgotpassword;

import com.xplug.tech.notifications.context.CreateNotificationContext;
import com.xplug.tech.notifications.context.NotificationService;
import com.xplug.tech.notifications.email.context.SendEmailRequest;
import com.xplug.tech.notifications.email.core.EmailMessageFormatter;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Component
public class ForgotPasswordEventListener implements ApplicationListener<ForgotPasswordEvent> {

    private final NotificationService notificationService;

    @Value("${system.name}")
    private String systemName;

    public ForgotPasswordEventListener(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @Override
    public void onApplicationEvent(ForgotPasswordEvent forgotPasswordEvent) {

        log.info("forgotPasswordEvent");

        val subject = "Forgot Password Change Request";

        val token = forgotPasswordEvent.getToken();

        val user = token.getUserAccount();

        CreateNotificationContext forgotPasswordNotificationsContext = new CreateNotificationContext();
        forgotPasswordNotificationsContext.setCategory(subject);
        forgotPasswordNotificationsContext.setMessage("Hie " + user.getFirstName() + ". Your forgot password request was mailed to your email.");
        forgotPasswordNotificationsContext.setSubject(subject);
        forgotPasswordNotificationsContext.setUserId(user.getId());
        forgotPasswordNotificationsContext.setUsername(user.getUsername());

        SendEmailRequest emailRequest = new SendEmailRequest();

        val emailMessageFormatter = new EmailMessageFormatter();

        emailMessageFormatter.addGreeting("Hi " + user.getUsername());

        emailMessageFormatter.addParagraph("You have requested a reset of your password. Please use the token provided below to reset your password.");

        emailMessageFormatter.addParagraph(EmailMessageFormatter.boldText("Token: " + token.getValue()));

        emailRequest.setBody(emailMessageFormatter.buildMessage());
        emailRequest.setSubject(systemName + " : " + subject);
        Set<String> emails = new HashSet<>();
        emails.add(user.getEmail());
        emailRequest.setEmailRecipients(emails);

        notificationService.create(forgotPasswordNotificationsContext, emailRequest);

    }
}
