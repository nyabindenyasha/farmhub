package com.xplug.tech.usermanager.permissions.permission;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author Nyabinde Nyasha
 * @created 12/28/2020
 * @project procurement-system
 */

@Data
public class PermissionUpdateRequest {

    private long id;

    @NotBlank(message = "Permission name should be provided")
    private String name;

    private String description;

    private boolean isForAdminOnly;
}
