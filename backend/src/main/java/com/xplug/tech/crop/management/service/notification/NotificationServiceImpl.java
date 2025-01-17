package com.xplug.tech.crop.management.service.notification;

import com.xplug.tech.crop.management.dao.NotificationDao;
import com.xplug.tech.crop.management.domain.Notification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public non-sealed class NotificationServiceImpl implements NotificationService {

    private final NotificationDao notificationRepository;

    public NotificationServiceImpl(NotificationDao notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Override
    public Notification create(Notification notification) {
        return notificationRepository.save(notification);
    }

}
