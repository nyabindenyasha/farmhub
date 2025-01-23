package com.xplug.tech.notifications;

import java.util.List;

public interface InvoiceNotificationsService {


    void sendEmail(RequestEmails requestEmails);

    void sendSms(List<RequestSms> requestSms);
}
