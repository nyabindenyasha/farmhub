package com.xplug.tech.usermanager.permissions.permission;

import com.xplug.tech.usermanager.Permission;
import com.xplug.tech.usermanager.permissions.PermissionsEnum;
import lombok.val;

public sealed interface PermissionMapper permits PermissionMapperImpl {

    Permission permissionFromPermissionRequest(PermissionRequest permissionRequest);

    Permission permissionFromPermissionUpdateRequest(Permission permission, PermissionUpdateRequest permissionUpdateRequest);

    PermissionResponse permissionResponseFromPermission(Permission permission);

    static PermissionRequest fromEnum(PermissionsEnum permissionsEnum) {
        val permissionRequest = new PermissionRequest();
        permissionRequest.setDescription(permissionsEnum.name().replace("_", " "));
        permissionRequest.setName(permissionsEnum.name());
        return permissionRequest;
    }

}
