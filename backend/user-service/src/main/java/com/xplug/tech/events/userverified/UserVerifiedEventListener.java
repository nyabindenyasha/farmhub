package com.xplug.tech.events.userverified;

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
public class UserVerifiedEventListener implements ApplicationListener<UserVerifiedEvent> {

    private final NotificationService notificationService;

    @Value("${system.name}")
    private String systemName;


    public UserVerifiedEventListener(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @Override
    public void onApplicationEvent(UserVerifiedEvent userVerifiedEvent) {

        log.info("userVerifiedEvent");

        val subject = "UserAccount Verified";

        val user = userVerifiedEvent.getUserAccount();

        CreateNotificationContext userCreatedNotificationsContext = new CreateNotificationContext();
        userCreatedNotificationsContext.setCategory(subject);
        userCreatedNotificationsContext.setMessage("Hie " + user.getUsername() + "Your user account has been verified successfully. Enjoy you experience with team Bonvie.");
        userCreatedNotificationsContext.setSubject(subject);
        userCreatedNotificationsContext.setUserId(user.getId());
        userCreatedNotificationsContext.setUsername(user.getUsername());

        SendEmailRequest emailRequest = new SendEmailRequest();

        val emailMessageFormatter = new EmailMessageFormatter();

        emailMessageFormatter.addGreeting("Hie " + user.getUsername());

        emailMessageFormatter.addParagraph("Your user account has been verified successfully.");
        emailMessageFormatter.addParagraph("Enjoy you experience with team Bonvie.");

        emailRequest.setBody(emailMessageFormatter.buildMessage());
        emailRequest.setSubject(systemName + " : " + subject);
        Set<String> emails = new HashSet<>();
        emails.add(user.getEmail());
        emailRequest.setEmailRecipients(emails);

        notificationService.create(userCreatedNotificationsContext, emailRequest);

    }
}

