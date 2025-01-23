package com.xplug.tech.notifications;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class RequestSms {

    String receivingMobileNumber;

    String message;

}
