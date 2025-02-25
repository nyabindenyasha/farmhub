package com.xplug.tech.usermanager.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xplug.tech.usermanager.Permission;
import com.xplug.tech.usermanager.UserGroup;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Collection;


@Data
public class UserAccountUpdateRequest {

    private long id;

    @NotBlank(message = "First name should be provided")
    @Size(max = 255, message = "First name should not be more than 255 characters")
    private String firstName;

    @NotBlank(message = "Last name should be provided")
    @Size(max = 255, message = "Last should not be more than 255 characters")
    private String lastName;

    @JsonIgnore
    private String username;

    @Email(message = "A valid email address should be provided")
    @NotBlank(message = "Email address should be provided")
    private String email;

    private String phoneNumber;

    @JsonIgnore
    private Collection<Permission> permissions;

    private String password; //to be changed to a temp password that is updated after a create

    private long groupId;

    @JsonIgnore
    private UserGroup group;

    private boolean isActive;

    @JsonIgnore
    private boolean isAdmin;

    private UserPortal userPortal;

}
