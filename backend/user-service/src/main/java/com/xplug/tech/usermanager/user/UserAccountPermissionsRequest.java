package com.xplug.tech.usermanager.user;

import lombok.Data;

import java.util.Set;


@Data
public class UserAccountPermissionsRequest {

    private long userAccountId;
    private Set<String> permissions;

}
