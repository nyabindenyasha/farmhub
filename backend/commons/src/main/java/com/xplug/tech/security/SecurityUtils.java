package com.xplug.tech.security;

import com.xplug.tech.utils.BeanUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SecurityUtils {

    private static final SecurityContextHolderIdentity securityContextHolderIdentity;

    static {
        securityContextHolderIdentity = BeanUtil.getBean(SecurityContextHolderIdentity.class); // Assuming a bean utility
    }

    private SecurityUtils() {
    }

    public static String getSignedInUsername() {
        log.info("### SecurityUtils, getSignedInUsername(()");
        String username = securityContextHolderIdentity.getUsername();
        log.info("signedInUser: {}", username);
        return username;
    }

}

