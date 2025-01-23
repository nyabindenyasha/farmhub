package com.xplug.tech.usermanager.permissions.permission;

import com.xplug.tech.usermanager.permissions.PermissionsEnum;
import lombok.Data;
import lombok.val;

import javax.validation.constraints.NotBlank;



@Data
public class PermissionRequest {

    @NotBlank(message = "Permission name should be provided")
    private String name;

    private String description;

    private boolean isForAdminOnly;

    private boolean isForCompanyAdmin;

    public static PermissionRequest fromEnum(PermissionsEnum permissions) {

        val context = new PermissionRequest();
        context.setDescription(permissions.name().replace("_", " "));
        context.setName(permissions.name());
        context.setForAdminOnly(permissions.isForAdminOnly());
        context.setForCompanyAdmin(permissions.isForCompanyAdmin());
        return context;
    }
}
