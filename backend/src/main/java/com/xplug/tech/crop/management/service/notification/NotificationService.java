package com.xplug.tech.crop.management.service.notification;

import com.xplug.tech.crop.management.domain.Notification;

public sealed interface NotificationService permits NotificationServiceImpl {

    Notification create(Notification notification);

}
