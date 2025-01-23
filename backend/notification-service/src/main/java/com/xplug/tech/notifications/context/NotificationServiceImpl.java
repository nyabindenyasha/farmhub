package com.xplug.tech.notifications.context;

import com.xplug.tech.exception.RecordNotFoundException;
import com.xplug.tech.jpa.BaseServiceImpl;
import com.xplug.tech.notifications.email.context.SendEmailRequest;
import com.xplug.tech.notifications.email.context.SendEmailService;
import lombok.val;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class NotificationServiceImpl extends BaseServiceImpl<Notification, CreateNotificationContext, Notification> implements NotificationService {

    private final NotificationRepository notificationRepository;

    private final SendEmailService sendEmailService;

    public NotificationServiceImpl(NotificationRepository notificationRepository, SendEmailService sendEmailService) {
        super(notificationRepository);
        this.notificationRepository = notificationRepository;
        this.sendEmailService = sendEmailService;
    }

    @Override
    protected Class<Notification> getEntityClass() {
        return Notification.class;
    }

    @Override
    public Notification create(CreateNotificationContext notificationCommand) {

        val notification = Notification.fromCommand(notificationCommand);

        return notificationRepository.save(notification);

    }

    @Override
    public Notification create(CreateNotificationContext notificationCommand, SendEmailRequest sendEmailRequest) {

        val notification = Notification.fromCommand(notificationCommand);

        sendEmailService.sendEmail(sendEmailRequest);

        val notificationSent = notificationRepository.save(notification);

        return notificationSent;

    }

    @Override
    public Notification update(Notification updateDto) {
        return null;
    }

    @Override
    public Notification markRead(long id) {

        val notification = findById(id);

        notification.markRead();

        return notificationRepository.save(notification);

    }

    @Override
    public void markAllAsRead(long userId) {
        Collection<Notification> allNotifications = notificationRepository.findAllByUserId(userId);
        allNotifications.forEach(notification -> markRead(notification.getId()));
    }

    @Override
    public Notification markUnread(long id) {

        val notification = notificationRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Notification record was not found."));

        notification.markUnread();

        return notificationRepository.save(notification);

    }

    @Override
    public Page<Notification> findAllNotifications(Principal principal, Pageable pageable) {
        val username = principal.getName();
        return notificationRepository.findByUsername(username, pageable);
    }

    @Override
    public Page<Notification> findAllNotificationsV2(Principal principal, Pageable pageable) {
//        todo decode token
//        val username = principal.getName();
//        val user = BeanUtil.getBean(UserAccountService.class).findByUsername(username);
//        val userId = user.getId();
//        val userNotifications = notificationRepository.findAllByUserId(userId);
        val userNotifications = notificationRepository.findAllByUserId(1);
        val sortedUserNotifications = sortByDate(userNotifications);


        int start = (int) pageable.getOffset();

        int end = Math.min((start + pageable.getPageSize()), sortedUserNotifications.size());

        return new PageImpl<>(sortedUserNotifications.subList(start, end), pageable, sortedUserNotifications.size());
    }

    @Override
    public void sendEmail(SendEmailRequest sendEmailRequest) {
        sendEmailService.sendEmail(sendEmailRequest);
    }

    @Override
    public Page<NotificationProjection> findUnreadNotifications(Principal principal, Pageable pageable) {
        val username = principal.getName();
        return notificationRepository.findByUsernameAndViewedIsFalseOrderByTimestampDesc(username, pageable);
    }

    @Override
    public Collection<NotificationProjection> findUnreadNotifications(Principal principal) {
        val username = principal.getName();
        return notificationRepository.findByUsernameAndViewedIsFalseOrderByTimestampDesc(username);
    }

    @Override
    public NotificationStats computesStats(Principal principal) {
        val username = principal.getName();
        val totalUnread = notificationRepository.countAllByUsernameAndViewedIsFalse(username);
        val notificationStats = new NotificationStats();
        notificationStats.setTotalUnread(totalUnread);
        return notificationStats;
    }

    @Override
    public Collection<Notification> findAllByUserId(long userId) {
        val userNotifications = notificationRepository.findAllByUserId(userId);
        return sortByDate(userNotifications);
    }

    List<Notification> sortByDate(Collection<Notification> oldCollection) {
        Comparator<Notification> byOrderTime = (notification1, notification2) -> {
            if (notification1.getTimestamp().isBefore(notification2.getTimestamp())) return -1;
            else return 1;
        };

        List<Notification> newCollection = oldCollection.stream().sorted(byOrderTime.reversed())
                .collect(Collectors.toList());

        return newCollection;
    }

}
