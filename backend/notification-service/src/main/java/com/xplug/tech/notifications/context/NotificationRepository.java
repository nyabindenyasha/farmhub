package com.xplug.tech.notifications.context;

import com.xplug.tech.jpa.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Collection;



@Repository
public interface NotificationRepository extends BaseRepository<Notification> {

    Page<Notification> findByUsername(String username, Pageable pageable);

    long countAllByUsernameAndViewedIsFalse(String username);

    Collection<Notification> findAllByUserId(long userId);

    Collection<NotificationProjection> findByUsernameAndViewedIsFalseOrderByTimestampDesc(String username);

    Page<NotificationProjection> findByUsernameAndViewedIsFalseOrderByTimestampDesc(String username, Pageable pageable);
}
