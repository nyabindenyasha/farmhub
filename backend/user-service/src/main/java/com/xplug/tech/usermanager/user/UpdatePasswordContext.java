package com.xplug.tech.usermanager.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.validation.constraints.NotBlank;


@Data
public class UpdatePasswordContext {

    @JsonIgnore
    private String username;

    @NotBlank(message = "New Password should be provided")
    private String newPassword;

    @NotBlank(message = "Old Password should be provided")
    private String oldPassword;

    @NotBlank(message = "Confirm New Password should be provided")
    private String confirmNewPassword;

}
