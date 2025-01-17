package com.xplug.tech.crop.management.api;

import com.xplug.tech.crop.management.domain.UserAccount;
import com.xplug.tech.crop.management.service.usermanager.UserAccountService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("v1/api/user")
public class UserAccountController {

    private final UserAccountService userAccountService;

    public UserAccountController(UserAccountService userAccountService) {
        this.userAccountService = userAccountService;
    }

    @PostMapping
    @Operation(summary = "Create User")
    public UserAccount createUser(@RequestBody UserAccount userAccount) {
        return userAccountService.create(userAccount);
    }

}
