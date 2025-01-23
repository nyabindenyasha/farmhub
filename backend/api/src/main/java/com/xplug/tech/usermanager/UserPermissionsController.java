package com.xplug.tech.usermanager;


import com.xplug.tech.usermanager.permissions.user.UserPermissionsService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@CrossOrigin
@RequestMapping("")

public class UserPermissionsController {

    private final UserPermissionsService userPermissionService;

    public UserPermissionsController(UserPermissionsService userPermissionService) {
        this.userPermissionService = userPermissionService;
    }

    @GetMapping("/v1/user/{userId}/permissions")

    public Page<UserPermission> getAll(@PageableDefault Pageable pageable, @PathVariable long userId) {
        return userPermissionService.findByUserId(userId, pageable);
    }

    @GetMapping("/v1/user/{userId}/permissions/all")

    public Collection<UserPermission> getAll(@PathVariable long userId) {
        return userPermissionService.findAllByUserId(userId);
    }

    @PostMapping("/v1/user-permissions")

    @PreAuthorize("hasAnyAuthority('CREATE_USER_PERMISSION', 'SUPER_ADMIN')")
    public UserPermission create(@RequestParam long userId, @RequestParam long permissionId) {
        return userPermissionService.create(userId, permissionId);
    }

    @PostMapping("/v1/user-permissions/bulk")

    @PreAuthorize("hasAnyAuthority('CREATE_USER_PERMISSION', 'SUPER_ADMIN')")
    public Collection<UserPermission> create(@RequestParam long userId, @RequestBody Collection<Long> permissionIds) {
        return userPermissionService.create(userId, permissionIds);
    }

    @PatchMapping("/v1/user-permissions/revoke")

    @PreAuthorize("hasAuthority('REVOKE_USER_PERMISSION')")
    public void delete(@RequestParam long userPermissionId) {
        userPermissionService.delete(userPermissionId);
    }

    @PatchMapping("/v1/user-permissions/revoke-bulk")

    @PreAuthorize("hasAuthority('REVOKE_USER_PERMISSION')")
    public void delete(@RequestBody Collection<Long> userPermissionIds) {
        userPermissionService.delete(userPermissionIds);
    }

    @GetMapping("/v1/user-permissions/unassigned/{userId}")

    public Collection<Permission> unassignedPermissions(@PathVariable long userId) {
        return userPermissionService.findUnassignedPermissions(userId);
    }

}
