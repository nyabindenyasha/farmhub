package com.xplug.tech.notifications.context;

import com.xplug.tech.jpa.BaseService;
import com.xplug.tech.notifications.email.context.SendEmailRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.security.Principal;
import java.util.Collection;



public interface NotificationService extends BaseService<Notification, CreateNotificationContext, Notification> {

    Notification create(CreateNotificationContext notificationCommand, SendEmailRequest sendEmailRequest);

    Notification markRead(long id);

    void markAllAsRead(long userId);

    Notification markUnread(long id);

    Page<NotificationProjection> findUnreadNotifications(Principal principal, Pageable pageable);

    Collection<NotificationProjection> findUnreadNotifications(Principal principal);

    Page<Notification> findAllNotifications(Principal principal, Pageable pageable);

    NotificationStats computesStats(Principal principal);

    Collection<Notification> findAllByUserId(long userId);

    Page<Notification> findAllNotificationsV2(Principal principal, Pageable pageable);

    void sendEmail(SendEmailRequest sendEmailRequest);
}
