package com.xplug.tech.usermanager.usergroup;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class UserGroupUpdateRequest {

    @NotNull(message = "User Group Id should be provided")
    private Long id;

    @NotBlank(message = "Group name should be provided")
    private String name;

    private String description;

}
