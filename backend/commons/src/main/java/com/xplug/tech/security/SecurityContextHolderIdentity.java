package com.xplug.tech.security;

public interface SecurityContextHolderIdentity {

    String getUsername();

    String getRemoteIp();

    String getRemoteHost();

    String getRemoteUser();

    String getCountry();

    String getTimeZone();

    String getUserAgent();

    String getOperatingSystem();

    String getBrowser();

}
