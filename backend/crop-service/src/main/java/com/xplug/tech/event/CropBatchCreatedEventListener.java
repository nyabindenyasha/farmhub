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
public class CropBatchCreatedEventListener implements ApplicationListener<CropBatchCreatedEvent> {

    @Value("${system.name}")
    private String systemName;
    private final UserAccountService userAccountService;

    private final NotificationService notificationService;

    public CropBatchCreatedEventListener(UserAccountService userAccountService, NotificationService notificationService) {
        this.userAccountService = userAccountService;
        this.notificationService = notificationService;
    }

    @Override
    public void onApplicationEvent(CropBatchCreatedEvent cropBatchCreatedEvent) {
        log.info("### CropBatchCreatedEvent");

        val subject = "Crop Batch Created";

        var farmer = userAccountService.findById(cropBatchCreatedEvent.getCropBatch().getCropFarmer().getId());

        var crop = cropBatchCreatedEvent.getCropBatch().getCropFarmer().getCrop();

        CreateNotificationContext userCreatedNotificationsContext = new CreateNotificationContext();
        userCreatedNotificationsContext.setCategory(subject);
        userCreatedNotificationsContext.setMessage("Hie " + farmer.getUsername() + " you have created a new crop batch successfully.");
        userCreatedNotificationsContext.setSubject(subject);
        userCreatedNotificationsContext.setUserId(farmer.getId());
        userCreatedNotificationsContext.setUsername(farmer.getUsername());

        SendEmailRequest emailRequest = new SendEmailRequest();

        val emailMessageFormatter = new EmailMessageFormatter();

        emailMessageFormatter.addGreeting("Hie " + farmer.getUsername());

        emailMessageFormatter.addParagraph("You have created a new crop batch successfully.");
        emailMessageFormatter.addParagraph("The crop " + crop.getName() + " was successfully planted on " + cropBatchCreatedEvent.getCropBatch().getCropFarmer().getDateOfTransplant());
        emailMessageFormatter.addParagraph("");

        emailRequest.setBody(emailMessageFormatter.buildMessage());
        emailRequest.setSubject(systemName + " : " + subject);
        Set<String> emails = new HashSet<>();
        emails.add(farmer.getEmail());
        emailRequest.setEmailRecipients(emails);
        //todo
 //       notificationService.create(userCreatedNotificationsContext, emailRequest);
    }

}
