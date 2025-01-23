package com.xplug.tech.usermanager;


import com.xplug.tech.exception.InvalidRequestException;
import com.xplug.tech.pojo.ResponseMessage;
import com.xplug.tech.usermanager.user.*;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

import static java.util.Objects.isNull;


@Slf4j
@CrossOrigin
@RestController
public class UserAccountController {

    private final UserAccountService userService;


    public UserAccountController(UserAccountService userService) {
        this.userService = userService;
    }

    @GetMapping("/v1/users")
    public ResponseEntity<?> getAll(@PageableDefault(sort = "username") Pageable pageable, @RequestParam(required = false) String search) {
        log.info("getAll() request: {} ", pageable);
        try {
            val users = userService.findAll(pageable, search);
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseMessage(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/v1/users/all")
    public ResponseEntity<?> getAll() {
        log.info("getUser() request: {} ");
        try {
            val users = userService.findAll();
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseMessage(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/v1/user/{userId}")
    public ResponseEntity<?> getUser(@PathVariable long userId) {
        log.info("getUser() request: {} ", userId);
        try {
            val user = userService.findById(userId);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseMessage(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/v1/user/{userId}")
    public ResponseEntity<?> delete(@PathVariable long userId) {
        log.info("delete() request: {} ", userId);
        try {
            userService.delete(userId);
            return new ResponseEntity<>(new ResponseMessage("success"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseMessage(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/v1/verify-user")
    public void verifyUser(@RequestParam String token) {
        userService.verifyUser(token);
    }

    @GetMapping("/v1/verify-user")
    public ResponseMessage verifyUserV2(@RequestParam String token) {
        try {
            userService.verifyUser(token);
            return new ResponseMessage("Verification Successful");
        } catch (Exception e) {
            return new ResponseMessage("Invalid Token");
        }
    }


    @PostMapping("/v1/users")
    public ResponseEntity<?> create(@Valid @RequestBody UserAccountRequest userAccountRequest) {
        log.info("create() request: {} ", userAccountRequest);
        try {
            val user = userService.create(userAccountRequest);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseMessage(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }


    @PatchMapping("/v1/users/{userId}/reset-user-password")
    @PreAuthorize("hasAnyAuthority('RESET_USER_PASSWORD', 'SUPER_ADMIN')")
    public ResponseEntity<?> resetUserPassword(@PathVariable long userId) {
        log.info("resetUserPassword() request: {} ", userId);
        try {
            userService.resetPassword(userId);
            return new ResponseEntity<>(new ResponseMessage("success"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseMessage(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }


    @PatchMapping("/v1/users/{userId}/change-user-group")
    @PreAuthorize("hasAnyAuthority('CHANGE_USER_GROUP', 'SUPER_ADMIN')")
    public ResponseEntity<?> changeUserGroup(@PathVariable long userId, @RequestParam long groupId) {
//        return groupService.changeUserGroup(userId, groupId);
        return null;
    }


    @PutMapping("/v1/users/{userId}")

    @PreAuthorize("hasAuthority('UPDATE_USER')")
    public ResponseEntity<?> update(@RequestBody UserAccountUpdateRequest userAccountUpdateRequest, @PathVariable long userId) {
        log.info("updateUser() request: {} ", userAccountUpdateRequest);
        try {
            if (userAccountUpdateRequest.getId() != userId) {
                throw new InvalidRequestException("Record not found!");
            }
            val user = userService.update(userAccountUpdateRequest);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseMessage(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/v1/users")
    public ResponseEntity<?> update(@RequestBody UserAccountUpdateRequest userAccountUpdateRequest, Principal principal) {
        log.info("updateMyUserProfile() request: {} ", userAccountUpdateRequest);
        try {
            val user = userService.update(userAccountUpdateRequest, principal);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseMessage(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/v1/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestParam(required = false) String username, @RequestParam(required = false) String email) {
        log.info("forgotPassword() request: {} " + username + " " + email);
        try {
            userService.forgotPassword(username, email);
            return new ResponseEntity<>(new ResponseMessage("success"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseMessage(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/v1/reset-password")
    public ResponseEntity<?> resetPassword(@Valid @RequestBody ResetPasswordContext resetPasswordContext) {
        log.info("resetPassword() request: {} ", resetPasswordContext);
        try {
            userService.resetPassword(resetPasswordContext);
            return new ResponseEntity<>(new ResponseMessage("success"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseMessage(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/v1/update-password")
    public ResponseEntity<?> changePassword(@Valid @RequestBody UpdatePasswordContext updatePasswordContext, Principal principal) {
        log.info("changePassword() request: {} ", updatePasswordContext);
        try {
            if (isNull(principal)) {
                throw new InvalidRequestException("You need to be logged in to perform this action.");
            }
            updatePasswordContext.setUsername(principal.getName());
            val user = userService.updatePassword(updatePasswordContext);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseMessage(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/system/v1/users/by-username")
    public ResponseEntity<?> findByUsername(@RequestParam String username) {
        log.info("findByUsername() request: {} ", username);
        try {
            val user = userService.findByUsername(username);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseMessage(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/system/v1/groups/{groupId}/users")
    public ResponseEntity<?> findByGroup(@PathVariable long groupId) {
        log.info("findByGroup() request: {} ", groupId);
        try {
            val users = userService.findByGroup(groupId);
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseMessage(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

}
