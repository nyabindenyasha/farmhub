package com.xplug.tech.usermanager;


import com.xplug.tech.usermanager.permissions.group.GroupPermissionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;


@CrossOrigin
//@Tag(name = "Group Permission", description = "Group Permission Related Resources")
@RestController
@RequestMapping("v1/api/group-permissions/")
public class GroupPermissionsController {

    private final GroupPermissionService groupPermissionService;

    public GroupPermissionsController(GroupPermissionService groupPermissionService) {
        this.groupPermissionService = groupPermissionService;
    }

    @GetMapping("all/{groupId}/permissions/page")

    public Page<GroupPermission> getAll(Pageable pageable, @PathVariable long groupId) {
        return groupPermissionService.findByGroupId(groupId, pageable);
    }

    @GetMapping("all/{groupId}/permissions/all")

    public Collection<GroupPermission> getAll(@PathVariable long groupId) {
        return groupPermissionService.findAllByGroupId(groupId);
    }

    @GetMapping("page/{groupId}/permissions")

    public Page<Permission> getPermissionsForGroup(Pageable pageable, @PathVariable long groupId) {
        return groupPermissionService.getPermissionsThatBelongToGroup(groupId, pageable);
    }

    @GetMapping("/v1/permissions/{groupId}/permissions/all")

    public Collection<Permission> getAllPermissionsForGroup(@PathVariable long groupId) {
        return groupPermissionService.getPermissionsThatBelongToGroup(groupId);
    }

    @PatchMapping("group-permissions/revoke")

    public void delete(@RequestParam long groupPermissionId) {
        groupPermissionService.delete(groupPermissionId);
    }

    @PatchMapping("group-permissions/revoke-bulk")

    public void delete(@RequestBody Collection<Long> groupPermissionIds) {
        groupPermissionService.delete(groupPermissionIds);
    }

    @PostMapping("/v1/group-permissions")

    public GroupPermission create(@RequestParam long groupId, @RequestParam long permissionId) {
        return groupPermissionService.create(groupId, permissionId);
    }

    @PostMapping("group-permissions/bulk")

    public Collection<GroupPermission> create(@RequestParam long groupId, @RequestBody Collection<Long> permissionIds) {
        return groupPermissionService.create(groupId, permissionIds);
    }

    @GetMapping("group-permissions/unassigned/{groupId}")

    public Collection<Permission> unassignedPermissions(@PathVariable long groupId) {
        return groupPermissionService.findUnassignedPermissions(groupId);
    }

}
