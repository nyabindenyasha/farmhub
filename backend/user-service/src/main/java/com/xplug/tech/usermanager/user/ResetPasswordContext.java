package com.xplug.tech.usermanager.user;

import lombok.Data;

import javax.validation.constraints.NotBlank;


@Data
public class ResetPasswordContext {

    @NotBlank(message = "Token should be provided")
    private String token;

    @NotBlank(message = "Password should be provided")
    private String password;

    @NotBlank(message = "Confirm Password should be provided")
    private String confirmPassword;

}
