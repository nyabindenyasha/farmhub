package com.xplug.tech.security;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Slf4j
@Component
public class SecurityContextHolderIdentityImpl implements SecurityContextHolderIdentity {

    private final HttpServletRequest httpServletRequest;

    public SecurityContextHolderIdentityImpl(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    @Override
    public String getUsername() {
        if(httpServletRequest.equals("Current HttpServletRequest")){
            log.info("yes");
        }
        log.info(String.valueOf(isNull(httpServletRequest)));
        val principal = httpServletRequest.getUserPrincipal();
        if (nonNull(principal)) {
            log.trace("### Security Context Holder obtained, the holder's name is {}", principal.getName());
            return principal.getName();
        } else
            return "Not Authenticated";
    }

    @Override
    public String getRemoteIp() {
        String ipAddress = httpServletRequest.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) {
            ipAddress = httpServletRequest.getRemoteAddr();
        }
        return ipAddress;
    }

    @Override
    public String getRemoteHost() {
        String remoteHost = httpServletRequest.getHeader("X-Host-Header");
        if (remoteHost == null) {
            remoteHost = httpServletRequest.getRemoteHost();
        }
        return remoteHost;
    }

    @Override
    public String getRemoteUser() {
        val remoteUser = httpServletRequest.getRemoteUser();
        return remoteUser;
    }

    @Override
    public String getCountry() {
        return httpServletRequest.getHeader("X-Country-Header");
    }

    @Override
    public String getTimeZone() {
        return httpServletRequest.getHeader("X-Time-Zone-Header");
    }

    @Override
    public String getUserAgent() {
        return httpServletRequest.getHeader("X-User-Agent-Header");
    }

    @Override
    public String getOperatingSystem() {
        return httpServletRequest.getHeader("X-OS-Header");
    }

    @Override
    public String getBrowser() {
        return httpServletRequest.getHeader("X-Browser-Header");
    }
}
