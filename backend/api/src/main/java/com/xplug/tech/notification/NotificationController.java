package com.xplug.tech.notification;

import com.xplug.tech.exception.AccessDeniedException;
import com.xplug.tech.notifications.context.Notification;
import com.xplug.tech.notifications.context.NotificationProjection;
import com.xplug.tech.notifications.context.NotificationService;
import com.xplug.tech.notifications.context.NotificationStats;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Collection;

import static java.util.Objects.isNull;

@CrossOrigin
@RestController
@RequestMapping("/v1/notifications")

public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping("/all")

    public Collection<Notification> findAllNotifications() {
        return notificationService.findAll();
    }

    @GetMapping("/findByUserId/{userId}/all")

    public Collection<Notification> findAllByUserId(@PathVariable long userId) {
        return notificationService.findAllByUserId(userId);
    }

    @GetMapping("/my-notifications")

    public Page<Notification> findCurrentUserNotifications(
            Principal principal, @PageableDefault(sort = "timestamp", direction = Sort.Direction.DESC) Pageable pageable) {
        if (isNull(principal)) {
            throw new AccessDeniedException("You need to be logged in to perform this action.");
        }
        return notificationService.findAllNotifications(principal, pageable);
    }

    @GetMapping("/my-notifications/v2")

    public Page<Notification> findCurrentUserNotificationsV2(
            Principal principal, @PageableDefault(sort = "timestamp", direction = Sort.Direction.DESC) Pageable pageable) {
        if (isNull(principal)) {
            throw new AccessDeniedException("You need to be logged in to perform this action.");
        }
        return notificationService.findAllNotificationsV2(principal, pageable);
    }

    @GetMapping("/my-notifications/unread")

    public Page<NotificationProjection> findCurrentUserUnreadNotifications(
            Principal principal, @PageableDefault(sort = "timestamp", direction = Sort.Direction.DESC) Pageable pageable) {
        if (isNull(principal)) {
            throw new AccessDeniedException("You need to be logged in to perform this action.");
        }
        return notificationService.findUnreadNotifications(principal, pageable);
    }

    @GetMapping("/my-notifications/unread/all")

    public Collection<NotificationProjection> findCurrentUserUnreadNotifications(Principal principal) {
        if (isNull(principal)) {
            throw new AccessDeniedException("You need to be logged in to perform this action.");
        }
        return notificationService.findUnreadNotifications(principal);
    }

    @GetMapping("/{notificationId}")

    public Notification findNotificationById(@PathVariable long notificationId) {
        return notificationService.findById(notificationId);
    }

    @PatchMapping("/{notificationId}/mark-read")

    public Notification markRead(@PathVariable long notificationId) {
        return notificationService.markRead(notificationId);
    }

    @PatchMapping("/my-notifications/mark-all-as-read/{userId}")

    public void markAllAsRead(@PathVariable long userId) {
        notificationService.markAllAsRead(userId);
    }

    @PatchMapping("/{notificationId}/mark-unread")

    public Notification markUnread(@PathVariable long notificationId) {
        return notificationService.markUnread(notificationId);
    }

    @GetMapping("/my-stats")

    public NotificationStats getUserStats(Principal principal) {
        return notificationService.computesStats(principal);
    }
}