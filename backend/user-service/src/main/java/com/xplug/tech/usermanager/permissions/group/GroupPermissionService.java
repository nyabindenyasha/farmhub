package com.xplug.tech.usermanager.permissions.group;

import com.xplug.tech.usermanager.GroupPermission;
import com.xplug.tech.usermanager.Permission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;

public interface GroupPermissionService {

    GroupPermission create(GroupPermissionRequest groupPermissionRequest);

    GroupPermission create(long groupId, long permissionId);

    Collection<GroupPermission> create(long groupId, Collection<Long> permissionIds);

    Collection<GroupPermission> createFromPermissions(long groupId, Collection<Permission> permissions);

    Collection<GroupPermission> findAllByGroupId(long groupId);

    Page<GroupPermission> findByGroupId(long id, Pageable pageable);

    void delete(long groupPermissionId);

    void delete(Collection<Long> groupPermissionIds);

    Collection<Permission> getPermissionsThatBelongToGroup(long groupId);

    Page<Permission> getPermissionsThatBelongToGroup(long groupId, Pageable pageable);

    Collection<Permission> findUnassignedPermissions(long groupId);

}
