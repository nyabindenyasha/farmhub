package com.xplug.tech.usermanager.usergroup;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class UserGroupRequest {

    @NotBlank(message = "Group name should be provided")
    private String name;

    private String description;

}
