package com.xplug.tech.usermanager;


import com.xplug.tech.usermanager.permissions.permission.PermissionRequest;
import com.xplug.tech.usermanager.permissions.permission.PermissionService;
import com.xplug.tech.usermanager.permissions.permission.PermissionUpdateRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;


@RestController
@CrossOrigin
@RequestMapping("")

public class PermissionsController {

    private final PermissionService permissionService;

    public PermissionsController(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @GetMapping("/v1/permissions")

    public Page<Permission> getAll(@PageableDefault Pageable pageable, @RequestParam(required = false) String search) {
        return permissionService.findAll(pageable, search);
    }

    @GetMapping("/v1/permissions/all")

    public Collection<Permission> getAll() {
        return permissionService.findAll();
    }

    @GetMapping("/system/v1/permission/{permissionId}")

    public Permission getPermission(@PathVariable long permissionId) {
        return permissionService.findById(permissionId);
    }

    @PostMapping("/system/v1/permissions")

    //@PreAuthorize("hasAuthority('CREATE_PERMISSION')")
    public Permission create(@RequestBody PermissionRequest permissionRequest) {
        return permissionService.create(permissionRequest);
    }

    @PostMapping("/system/v1/permissions/bulk")

    //@PreAuthorize("hasAuthority('CREATE_PERMISSION')")
    public Collection<Permission> create(@RequestBody Collection<PermissionRequest> permissionRequests) {
        return permissionService.create(permissionRequests);
    }

    @PutMapping("/system/v1/permissions/{permissionId}")
    public Permission update(@RequestBody PermissionUpdateRequest permissionUpdateRequest, @PathVariable long permissionId) {
        permissionUpdateRequest.setId(permissionId);
        return permissionService.update(permissionUpdateRequest);
    }

}

