package com.xplug.tech.config;

import com.xplug.tech.security.SecurityContextHolderIdentity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

@Slf4j
//@Service
public class AuditorAwareImpl implements AuditorAware<String> {

    @Autowired
    private SecurityContextHolderIdentity securityContextHolderIdentity;

    @Override
    public Optional<String> getCurrentAuditor() {
        log.info("### AuditorAwareImpl, getCurrentAuditor()");
        String signedInUser = securityContextHolderIdentity.getUsername();
        log.info("### signedInUser: {}", signedInUser);
        return Optional.of(signedInUser);
    }

}
