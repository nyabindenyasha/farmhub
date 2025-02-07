package com.xplug.tech.event;

import com.xplug.tech.crop.Crop;
import com.xplug.tech.cropprograms.CropProgramPDFGeneratorTest;
import com.xplug.tech.notifications.context.CreateNotificationContext;
import com.xplug.tech.notifications.context.NotificationService;
import com.xplug.tech.notifications.email.context.ResourceMultipartFile;
import com.xplug.tech.notifications.email.context.SendEmailRequest;
import com.xplug.tech.notifications.email.core.Attachment;
import com.xplug.tech.notifications.email.core.EmailMessageFormatter;
import com.xplug.tech.usermanager.UserAccount;
import com.xplug.tech.usermanager.user.UserAccountService;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Slf4j
@Component
public class TaskReadyForExecutionEventListener implements ApplicationListener<TaskReadyForExecutionEvent> {

    @Value("${system.name}")
    private String systemName;
    private final UserAccountService userAccountService;

    private final CropProgramPDFGeneratorTest cropProgramPDFGeneratorTest;

    private final NotificationService notificationService;

    public TaskReadyForExecutionEventListener(UserAccountService userAccountService, CropProgramPDFGeneratorTest cropProgramPDFGeneratorTest, NotificationService notificationService) {
        this.userAccountService = userAccountService;
        this.cropProgramPDFGeneratorTest = cropProgramPDFGeneratorTest;
        this.notificationService = notificationService;
    }

    @Override
    public void onApplicationEvent(TaskReadyForExecutionEvent taskReadyForExecutionEvent) {
        log.info("### TaskReadyForExecutionEvent");

        val subject = "Task Ready For Execution";

        UserAccount farmer = userAccountService.findById(taskReadyForExecutionEvent.getCropScheduleTask().getCropBatch().getUserAccount().getId());
        Crop crop = taskReadyForExecutionEvent.getCropScheduleTask().getCropBatch().getCropProgram().getCrop();
        String taskName = taskReadyForExecutionEvent.getCropScheduleTask().getTaskName();
        LocalDateTime taskDate = taskReadyForExecutionEvent.getCropScheduleTask().getTaskDate();

        CreateNotificationContext userCreatedNotificationsContext = new CreateNotificationContext();
        userCreatedNotificationsContext.setCategory(subject);
        userCreatedNotificationsContext.setMessage("Hie " + farmer.getUsername() + " you have a new task: \n " + taskName);
        userCreatedNotificationsContext.setSubject(subject);
        userCreatedNotificationsContext.setUserId(farmer.getId());
        userCreatedNotificationsContext.setUsername(farmer.getUsername());

        SendEmailRequest emailRequest = new SendEmailRequest();

        val emailMessageFormatter = new EmailMessageFormatter();

        emailMessageFormatter.addGreeting("Hie " + farmer.getUsername());

        emailMessageFormatter.addParagraph("You have a new task awaiting;");
        emailMessageFormatter.addParagraph(taskName);
        emailMessageFormatter.addParagraph("The task is due on " + taskDate);
        emailMessageFormatter.addParagraph("Please complete the task and log into the system to update the status of the task.");

        emailRequest.setBody(emailMessageFormatter.buildMessage());
        emailRequest.setSubject(systemName + " : " + subject);
        Set<String> emails = new HashSet<>();
        emails.add(farmer.getEmail());
        emailRequest.setEmailRecipients(emails);

        String fileName = crop.getName() + "_Program.pdf";
        Resource resource = convertToByteArrayResource(cropProgramPDFGeneratorTest.generateCabbageProgramPdf(crop.getId()));
        MultipartFile multipartFile = new ResourceMultipartFile(resource);
        Attachment attachment = new Attachment();
        attachment.setName(fileName);
        attachment.setFile(multipartFile);
        emailRequest.setAttachment(attachment);


        notificationService.create(userCreatedNotificationsContext, emailRequest);
    }

//    private File convert(byte[] bytes, String fileName) throws IOException {
//        File tempFile = File.createTempFile(fileName, null); // Creates a temp file
//        try (FileOutputStream fos = new FileOutputStream(tempFile)) {
//            fos.write(bytes);
//        }
//        return tempFile;
//    }

    static Resource convertToByteArrayResource(byte[] bytes) {
        ByteArrayResource resource = new ByteArrayResource(bytes);
        return resource;
    }

}
