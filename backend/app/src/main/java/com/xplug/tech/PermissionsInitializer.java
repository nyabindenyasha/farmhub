package com.xplug.tech;

import com.xplug.tech.usermanager.permissions.PermissionsEnum;
import com.xplug.tech.usermanager.permissions.permission.PermissionRequest;
import com.xplug.tech.usermanager.permissions.permission.PermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.util.Arrays;
import java.util.stream.Stream;

@Slf4j
@Configuration
@Order(1)  // First to run
public class PermissionsInitializer implements InitializingBean {

    private final PermissionService permissionService;

    public PermissionsInitializer(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("Permissions initializer" + Arrays.toString(PermissionsEnum.values()));
        Stream.of(PermissionsEnum.values())
                .map(PermissionRequest::fromEnum)
                .forEach(permissionService::create);
    }
}
