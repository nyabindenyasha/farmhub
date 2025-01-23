package com.xplug.tech.notifications.context;



public interface NotificationProjection {

    long getId();

    String getSubject();

    String getCategory();

    String getUsername();

}
