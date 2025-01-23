package com.xplug.tech.usermanager.user;

import lombok.Data;


@Data
public class ActivateOrDeActivateUserRequest {

    private long userAccountId;
    private boolean isActive;

}
