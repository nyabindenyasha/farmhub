package com.xplug.tech.notifications.context;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDateTime;



@Data
@Entity
public class Notification implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String username;

    private long userId;

    private String subject;

    private String message;

    private String category;

    private LocalDateTime timestamp;

    private boolean viewed;

    private LocalDateTime dateTimeViewed;

    void markRead() {
        this.dateTimeViewed = LocalDateTime.now();
        this.viewed = true;
    }

    void markUnread() {
        this.dateTimeViewed = null;
        this.viewed = false;
    }

    static Notification fromCommand(CreateNotificationContext notificationCommand) {

        if (notificationCommand == null) {
            return null;
        }
        Notification notification = new Notification();
        notification.setUsername(notificationCommand.getUsername());
        notification.setUserId(notificationCommand.getUserId());
        notification.setSubject(notificationCommand.getSubject());
        notification.setMessage(notificationCommand.getMessage());
        notification.setCategory(notificationCommand.getCategory());
        notification.setTimestamp(LocalDateTime.now());

        return notification;
    }

}
