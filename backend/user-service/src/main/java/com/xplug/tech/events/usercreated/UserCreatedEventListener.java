package com.xplug.tech.events.usercreated;

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
public class UserCreatedEventListener implements ApplicationListener<UserCreatedEvent> {

    private final NotificationService notificationService;
    @Value("${system.baseURL}")
    private String baseURL;
    @Value("${system.name}")
    private String systemName;

    public UserCreatedEventListener(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @Override
    public void onApplicationEvent(UserCreatedEvent userCreatedEvent) {

        log.info("userCreatedEvent");

        val subject = "UserAccount Created";

        val user = userCreatedEvent.getUserAccount();

        val token = userCreatedEvent.getToken();

        CreateNotificationContext userCreatedNotificationsContext = new CreateNotificationContext();
        userCreatedNotificationsContext.setCategory(subject);
        userCreatedNotificationsContext.setMessage("Hie " + user.getUsername() + " Welcome to the Bonvie Medical Society. A verification email was mailed to your email");
        userCreatedNotificationsContext.setSubject(subject);
        userCreatedNotificationsContext.setUserId(user.getId());
        userCreatedNotificationsContext.setUsername(user.getUsername());

        SendEmailRequest emailRequest = new SendEmailRequest();

        val emailMessageFormatter = new EmailMessageFormatter();

        emailMessageFormatter.addGreeting("Hie " + user.getUsername());

        emailMessageFormatter.addParagraph("Welcome to the Bonvie Family.");
        emailMessageFormatter.addParagraph("An account has been created for you.");
        emailMessageFormatter.addParagraph("Please click the link below to verify your account. " + baseURL + "/v1/verify-user?token="
                + token.getValue());

        emailRequest.setBody(emailMessageFormatter.buildMessage());
        emailRequest.setSubject(systemName + " : " + subject);
        Set<String> emails = new HashSet<>();
        emails.add(user.getEmail());
        emailRequest.setEmailRecipients(emails);

        notificationService.create(userCreatedNotificationsContext, emailRequest);

    }
}
