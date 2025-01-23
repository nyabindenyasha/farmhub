package com.xplug.tech.usermanager.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xplug.tech.usermanager.UserGroup;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class UserAccountRequest {

    @NotBlank(message = "First name should be provided")
    @Size(max = 50, message = "First name should not be more than 50 characters")
    private String firstName;
    @NotBlank(message = "Last name should be provided")
    @Size(max = 50, message = "Last should not be more than 50 characters")
    private String lastName;
    @NotBlank(message = "Username should be provided")
    @Size(max = 50, message = "Username should not be more than 50 characters")
    private String username;
    private String password; //to be changed to a temp password that is updated after a create
    @NotNull
    private Long groupId;
    @JsonIgnore
    private UserGroup userGroup;
    @Email(message = "A valid email address should be provided")
    @NotBlank(message = "Email address should be provided")
    private String email;
    private String phoneNumber;

}
