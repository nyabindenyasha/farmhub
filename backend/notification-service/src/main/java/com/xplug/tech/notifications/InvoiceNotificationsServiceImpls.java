//package com.xplug.tech.notifications;
//
//import com.google.gson.Gson;
//import com.sendgrid.Method;
//import com.sendgrid.Request;
//import com.sendgrid.Response;
//import com.sendgrid.SendGrid;
//import com.sendgrid.helpers.mail.Mail;
//import com.sendgrid.helpers.mail.objects.Attachments;
//import com.sendgrid.helpers.mail.objects.Content;
//import com.sendgrid.helpers.mail.objects.Email;
//import com.sendgrid.helpers.mail.objects.Personalization;
//import lombok.*;
//import lombok.extern.slf4j.Slf4j;
//import org.jobrunr.jobs.annotations.Job;
//import org.jobrunr.scheduling.JobScheduler;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.web.client.RestTemplateBuilder;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
//
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//import java.util.ArrayList;
//import java.util.Base64;
//import java.util.HashMap;
//import java.util.List;
//
//@Service
//@Slf4j
//public class InvoiceNotificationsServiceImpls implements InvoiceNotificationsService {
//    private final JobScheduler jobScheduler;
//
//    @Autowired
//    public InvoiceNotificationsServiceImpls(JobScheduler jobScheduler) {
//        this.jobScheduler = jobScheduler;
//    }
//
//    private static List<Email> createEmailList(List<String> emailAddresses) {
//        List<Email> emailList = new ArrayList<>();
//        for (String emailAddress : emailAddresses) {
//            emailList.add(new Email(emailAddress));
//        }
//        return emailList;
//    }
//
//    @Job(name = "send emails")
//    public void sendEmail(RequestEmails mails) {
//        log.info("Sending emails now");
//        jobScheduler.enqueue(() -> sendMailsJob(mails));
//
//    }
//
//    @Override
//    public void sendSms(List<RequestSms> requestSms) {
//        log.info("Sending sms with Request: {} ", requestSms);
//        jobScheduler.enqueue(() -> sendSmsJob(requestSms));
//    }
//
//    @Job(name = "send sms")
//    public void sendSmsJob(List<RequestSms> requestSmses) {
//        log.info("Request body : {}", requestSmses);
//        String tokenEndpoint = "https://api.socket.xplug.tech/fulfillment/";
//        RestTemplate restTemplate = new RestTemplateBuilder()
//                .defaultHeader("Content-Type", "application/json")
//                .defaultHeader("x-api-id", "d52a6f94a96a3a76bf8b828976d06d46")
//                .defaultHeader("x-api-secret", "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VyIjoiMCIsIm9yZ2FuaXNhdGlvbklkIjoiMiIsIm9yZ2FuaXNhdGlvbk5hbWUiOiJWU2VuZCBNb25leSBUcmFuc2ZlciIsImNoYW5uZWxJRCI6IjAiLCJjaGFubmVsVHlwZSI6Ik1PQklMRV9BUFBMSUNBVElPTiIsImtleSI6Ik1BIiwic3ViIjoiZDUyYTZmOTRhOTZhM2E3NmJmOGI4Mjg5NzZkMDZkNDYiLCJleHAiOjE3MDUyNTEwNjUsIm5iZiI6MTcwNDg5MTA2NSwiaWF0IjoxNzA0ODkxMDY1fQ.ovLh7q2cuetfGAgDv5_2f0w46j9AAv5fJuWqwo6gpKo")
//                .defaultHeader("organisationID", "3")
//                .build();
//
//        requestSmses.forEach(requestSms -> {
//            RequestInhouse request = new RequestInhouse();
//            HashMap<String, String> requestBody = new HashMap<>();
//            requestBody.put("receivingMobileNumber", requestSms.getReceivingMobileNumber());
//            requestBody.put("message", requestSms.getMessage());
//            requestBody.put("senderID", "Bonvie");
//            request.setOperation("send_sms");
//            request.setRequestBody(requestBody);
//
//            Gson gson = new Gson();
//            String requestJson = gson.toJson(request);
//            log.info("SMS sending with request:{}", requestSms);
//            ResponseEntity<String> responseEntity = restTemplate.postForEntity(tokenEndpoint, requestJson, String.class);
//            log.info("Message sent");
//
//        });
//
//    }
//
//
//    @Job(name = "send email")
//    public void sendMailsJob(RequestEmails emailsList) throws IOException {
//        String fromEmail = "info@bonvie.co.zw";
//        String apiKey = "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";
//
//        // Recipient information
//        List<String> toEmails = List.of("kudzieydamba@gmail.com");
//        List<String> ccEmails = List.of("dambakudzai@gmail.cm");
////        List<String> bccEmails = List.of("mildreds@xplug.co.zw", "annsam@xplug.co.zw");
//
//        // File attachment information
//        String attachmentFilePath = "backend/app/src/main/resources/invoice16568217_12_2023 T 23_29.pdf";
//
//        // SendGrid mail setup
//        Email from = new Email(fromEmail);
//        List<Email> toList = createEmailList(toEmails);
//        List<Email> ccList = createEmailList(ccEmails);
////        List<Email> bccList = createEmailList(bccEmails);
//        String subject = "Test Email";
//        Content content = new Content("text/plain", "Bonvie Medical Aid in Zimbabwe is a leading healthcare provider committed to ensuring accessible and quality medical services for individuals and families. With a focus on enhancing the well-being of the community, Bonvie Medical Aid offers comprehensive medical coverage, including consultations, diagnostics, hospitalization, and specialized treatments. Our commitment to excellence is reflected in our state-of-the-art facilities, experienced healthcare professionals, and a customer-centric approach. At Bonvie Medical Aid, we understand the importance of health and strive to make quality healthcare accessible to all, ensuring peace of mind and a healthier future for our members.");
//
//        // Create SendGrid mail object
//        Mail mail = new Mail(from, subject, toList.get(0), content);
//
//        // Add Cc and Bcc recipients
//        Personalization personalization = new Personalization();
//        for (Email ema : toList) {
//            personalization.addTo(ema);
//        }
//
////        for (Email ema : bccList) {
////            personalization.addBcc(ema);
////        }
//
//        for (Email ema : ccList) {
//            personalization.addCc(ema);
//        }
//
//        // Attach PDF file
//        try {
//            byte[] fileBytes = Files.readAllBytes(Paths.get(attachmentFilePath));
//            Attachments attachments = new Attachments();
//            attachments.setContent(Base64.getEncoder().encodeToString(fileBytes));
//            attachments.setType("application/pdf");
//            attachments.setFilename("InternalTransferDigitalReceipt.pdf");
//            attachments.setDisposition("attachment");
//            mail.addAttachments(attachments);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        mail.addPersonalization(personalization);
//
//        // Create SendGrid client
//        SendGrid sg = new SendGrid(apiKey);
//        Request request = new Request();
//
//        try {
//            // Set request parameters
//            request.setMethod(Method.POST);
//            request.setEndpoint("mail/send");
//            request.setBody(mail.build());
//
//            // Send the email
//            Response response = sg.api(request);
//
//            // Print the status code and body of the response
//            System.out.println("Status Code: " + response.getStatusCode());
//            System.out.println("Response Body: " + response.getBody());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Builder
//    @Data
//    @ToString
//    @AllArgsConstructor
//    @RequiredArgsConstructor
//    static class RequestInhouse {
//        private String operation;
//        private HashMap<String, String> requestBody;
//
//
//    }
//
//}
//
//
