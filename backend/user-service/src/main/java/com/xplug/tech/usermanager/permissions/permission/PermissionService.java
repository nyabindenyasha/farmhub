package com.xplug.tech.usermanager.permissions.permission;


import com.xplug.tech.jpa.BaseService;
import com.xplug.tech.usermanager.Permission;

import java.util.Collection;


public interface PermissionService extends BaseService<Permission, PermissionRequest, PermissionUpdateRequest> {

    Collection<Permission> create(Collection<PermissionRequest> permissionRequests);

    Collection<Permission> findByIds(Collection<Long> ids);

    Permission findByName(String name);

}
