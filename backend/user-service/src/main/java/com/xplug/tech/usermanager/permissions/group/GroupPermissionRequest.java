package com.xplug.tech.usermanager.permissions.group;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.xplug.tech.usermanager.Permission;
import com.xplug.tech.usermanager.UserGroup;
import com.xplug.tech.usermanager.permissions.permission.PermissionDeserializer;
import com.xplug.tech.usermanager.usergroup.UserGroupDeserializer;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class GroupPermissionRequest {

    @JsonProperty("groupId")
    @NotNull(message = "UserGroup should be provided.")
    @JsonDeserialize(using = UserGroupDeserializer.class)
    private UserGroup group;

    @JsonProperty("permissionId")
    @NotNull(message = "Permission should be provided.")
    @JsonDeserialize(using = PermissionDeserializer.class)
    private Permission permission;

}
