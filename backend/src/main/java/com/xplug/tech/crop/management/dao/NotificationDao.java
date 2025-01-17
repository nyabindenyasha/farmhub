package com.xplug.tech.crop.management.dao;

import com.xplug.tech.crop.management.domain.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationDao extends JpaRepository<Notification, Long> {

}
