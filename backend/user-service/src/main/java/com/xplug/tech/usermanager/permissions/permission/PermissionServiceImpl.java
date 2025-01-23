package com.xplug.tech.usermanager.permissions.permission;

import com.xplug.tech.exception.RecordNotFoundException;
import com.xplug.tech.jpa.BaseServiceImpl;
import com.xplug.tech.usermanager.Permission;
import com.xplug.tech.usermanager.PermissionDao;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.Collection;

import static java.util.stream.Collectors.toSet;


@Service
public class PermissionServiceImpl extends BaseServiceImpl<Permission, PermissionRequest, PermissionUpdateRequest> implements PermissionService {

    private final PermissionDao permissionDao;

    private final PermissionMapper permissionMapper;

    PermissionServiceImpl(PermissionDao permissionDao, PermissionMapper permissionMapper) {
        super(permissionDao);
        this.permissionDao = permissionDao;
        this.permissionMapper = permissionMapper;
    }

    @Override
    protected Class<Permission> getEntityClass() {
        return Permission.class;
    }

    @Override
    public Permission create(PermissionRequest permissionRequest) {

        val optionalPermission = permissionDao.findByName(permissionRequest.getName());

        if (optionalPermission.isPresent()) {
            val permission = optionalPermission.get();
            permission.setDescription(permissionRequest.getDescription());
            return permissionDao.save(permission);
        } else {
            val permission = permissionMapper.permissionFromPermissionRequest(permissionRequest);
            return permissionDao.save(permission);
        }
    }


    public Permission update(PermissionUpdateRequest permissionUpdateRequest) {
        val permission = findById(permissionUpdateRequest.getId());
        val updatedPermission = permissionMapper.permissionFromPermissionUpdateRequest(permission, permissionUpdateRequest);
        return permissionDao.save(permission);
    }

    @Override
    public Collection<Permission> create(Collection<PermissionRequest> permissionRequests) {
        Collection<Permission> permissions = permissionRequests.parallelStream()
                .map(permissionRequest -> {
                    val optionalPermission = permissionDao.findByName(permissionRequest.getName());
                    if (optionalPermission.isPresent()) {
                        val permission = optionalPermission.get();
                        permission.setDescription(permissionRequest.getDescription());
                        return permission;
                    } else {
                        return permissionMapper.permissionFromPermissionRequest(permissionRequest);
                    }
                })
                .collect(toSet());

        return permissionDao.saveAll(permissions);
    }

    @Override
    public Collection<Permission> findByIds(Collection<Long> ids) {
        return permissionDao.findAllById(ids);
    }

    @Override
    public Permission findByName(String name) {
        return permissionDao.findByName(name).orElseThrow(() -> new RecordNotFoundException("Permission with name not found"));
    }

}
