package com.xplug.tech.notifications.context;

import lombok.Data;



@Data
public class CreateNotificationContext {

    private String username;

    private long userId;

    private String subject;

    private String message;

    private String category;

}
