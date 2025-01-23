package com.xplug.tech.events.passwordupdated;

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
public class PasswordUpdatedEventListener implements ApplicationListener<PasswordUpdatedEvent> {

    private final NotificationService notificationService;

    @Value("${system.name}")
    private String systemName;

    public PasswordUpdatedEventListener(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @Override
    public void onApplicationEvent(PasswordUpdatedEvent passwordUpdatedEvent) {

        log.info("passwordUpdatedEvent");

        val user = passwordUpdatedEvent.getUserAccount();

        val subject = "Password Updated";

        CreateNotificationContext notificationsContext = new CreateNotificationContext();
        notificationsContext.setCategory(subject);
        notificationsContext.setMessage("Hie " + user.getUsername() + "Your user password has been changed successfully. Enjoy you experience with team Bonvie.");
        notificationsContext.setSubject(subject);
        notificationsContext.setUserId(user.getId());
        notificationsContext.setUsername(user.getUsername());

        SendEmailRequest emailRequest = new SendEmailRequest();

        val emailMessageFormatter = new EmailMessageFormatter();

        emailMessageFormatter.addGreeting("Hie " + user.getUsername());

        emailMessageFormatter.addParagraph("Your user password has been changed successfully.");
        emailMessageFormatter.addParagraph("Enjoy you experience with team Bonvie.");

        emailRequest.setBody(emailMessageFormatter.buildMessage());
        emailRequest.setSubject(systemName + " : " + subject);
        Set<String> emails = new HashSet<>();
        emails.add(user.getEmail());
        emailRequest.setEmailRecipients(emails);

        notificationService.create(notificationsContext, emailRequest);

    }
}
