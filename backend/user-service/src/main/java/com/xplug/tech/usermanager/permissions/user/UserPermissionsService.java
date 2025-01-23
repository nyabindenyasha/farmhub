package com.xplug.tech.usermanager.permissions.user;

import com.xplug.tech.usermanager.Permission;
import com.xplug.tech.usermanager.UserPermission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;

public interface UserPermissionsService {

    UserPermission create(long userId, long permissionId);

    Collection<UserPermission> create(long userId, Collection<Long> permissionIds);

    Collection<UserPermission> findAllByUserId(long userId);

    Page<UserPermission> findByUserId(long id, Pageable pageable);

    void delete(long userPermissionId);

    void delete(Collection<Long> userPermissionIds);

    Collection<Permission> findUnassignedPermissions(long userId);

}
