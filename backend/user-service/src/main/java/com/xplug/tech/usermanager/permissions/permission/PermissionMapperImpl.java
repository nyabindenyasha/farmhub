package com.xplug.tech.usermanager.permissions.permission;

import com.xplug.tech.usermanager.Permission;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Slf4j
@Component
public non-sealed class PermissionMapperImpl implements PermissionMapper {

    @Override
    public Permission permissionFromPermissionRequest(PermissionRequest permissionRequest) {
        Objects.requireNonNull(permissionRequest, "PermissionRequest must not be null");
        Permission permission = Permission.builder()
                .name(permissionRequest.getName())
                .description(permissionRequest.getDescription())
                .build();
        log.info("### Permission: {}", permission);
        return permission;
    }

    @Override
    public Permission permissionFromPermissionUpdateRequest(Permission permission, PermissionUpdateRequest permissionUpdateRequest) {
        Objects.requireNonNull(permissionUpdateRequest, "PermissionUpdateRequest must not be null");
        Objects.requireNonNull(permission, "Permission must not be null");
        permission.setName(permissionUpdateRequest.getName());
        permission.setDescription(permissionUpdateRequest.getDescription());
        return permission;
    }

    @Override
    public PermissionResponse permissionResponseFromPermission(Permission permission) {
        Objects.requireNonNull(permission, "Permission must not be null");
        PermissionResponse permissionResponse = PermissionResponse.builder()
                .id(permission.getId())
                .name(permission.getName())
                .description(permission.getDescription())
                .build();
        log.info("### PermissionResponse: {}", permissionResponse);
        return permissionResponse;
    }

}
