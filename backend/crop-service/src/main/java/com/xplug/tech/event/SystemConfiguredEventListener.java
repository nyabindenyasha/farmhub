package com.xplug.tech.event;

import com.xplug.tech.notifications.context.CreateNotificationContext;
import com.xplug.tech.notifications.context.NotificationService;
import com.xplug.tech.notifications.email.context.SendEmailRequest;
import com.xplug.tech.notifications.email.core.EmailMessageFormatter;
import com.xplug.tech.usermanager.user.UserAccountService;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Component
public class SystemConfiguredEventListener implements ApplicationListener<SystemConfiguredEvent> {

    @Value("${system.name}")
    private String systemName;
    private final UserAccountService userAccountService;

    private final NotificationService notificationService;

    public SystemConfiguredEventListener(UserAccountService userAccountService, NotificationService notificationService) {
        this.userAccountService = userAccountService;
        this.notificationService = notificationService;
    }

    @Override
    public void onApplicationEvent(SystemConfiguredEvent cropBatchCreatedEvent) {

        log.info("### SystemConfiguredEvent");

        val subject = "System Configured";

        var admin = userAccountService.findByUsername("admin");

        CreateNotificationContext userCreatedNotificationsContext = new CreateNotificationContext();
        userCreatedNotificationsContext.setCategory(subject);
        userCreatedNotificationsContext.setMessage("Hie " + admin.getUsername() + " The system has been configured successfully.");
        userCreatedNotificationsContext.setSubject(subject);
        userCreatedNotificationsContext.setUserId(admin.getId());
        userCreatedNotificationsContext.setUsername(admin.getUsername());

        SendEmailRequest emailRequest = new SendEmailRequest();

        val emailMessageFormatter = new EmailMessageFormatter();

        emailMessageFormatter.addGreeting("Hie " + admin.getUsername());

        emailMessageFormatter.addParagraph("The system has been configured successfully.");
        emailMessageFormatter.addParagraph("You can now start using the system by following the link below.");
        emailMessageFormatter.addParagraph("");

        emailRequest.setBody(emailMessageFormatter.buildMessage());
        emailRequest.setSubject(systemName + " : " + subject);
        Set<String> emails = new HashSet<>();
        emails.add(admin.getEmail());
        emailRequest.setEmailRecipients(emails);
        //todo uncomment
  //      notificationService.create(userCreatedNotificationsContext, emailRequest);
    }

}
