package com.xplug.tech.usermanager.user;

import lombok.Data;


@Data
public class CreateUserRequest {

    private String email;

    private String password;

    private String firstName;

    private String lastName;

    private long groupId;
}
