package com.xplug.tech.usermanager.permissions.user;

import com.xplug.tech.usermanager.UserPermission;
import com.xplug.tech.usermanager.permissions.permission.PermissionService;
import com.xplug.tech.usermanager.permissions.group.GroupPermissionService;
import com.xplug.tech.usermanager.UserAccountDao;
import com.xplug.tech.usermanager.GroupPermission;
import com.xplug.tech.usermanager.Permission;
import com.xplug.tech.usermanager.UserPermissionDao;
import lombok.val;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

@Service
public class UserPermissionsServiceImpl implements UserPermissionsService {

    private final UserPermissionDao userPermissionDao;


    private final UserAccountDao userAccountDao;
    private final PermissionService permissionService;

    private final GroupPermissionService groupPermissionService;

    UserPermissionsServiceImpl(UserPermissionDao userPermissionDao, UserAccountDao userAccountDao, PermissionService permissionService, GroupPermissionService groupPermissionService) {
        this.userPermissionDao = userPermissionDao;
        this.userAccountDao = userAccountDao;
        this.permissionService = permissionService;
        this.groupPermissionService = groupPermissionService;
    }

    @Override
    public UserPermission create(long userId, long permissionId) {

        val optionalPermission = userPermissionDao.findByUser_IdAndPermission_Id(userId, permissionId);

        if (!optionalPermission.isEmpty()) {
            return optionalPermission.get(0);
        }

        val user = userAccountDao.findById(userId).get();

        val permission = permissionService.findById(permissionId);

        val userPermission = new UserPermission(user, permission);

        return userPermissionDao.save(userPermission);
    }

    @Override
    public Collection<UserPermission> create(long userId, Collection<Long> permissionIds) {

        val user = userAccountDao.findById(userId).get();

        val permissions = permissionService.findByIds(permissionIds);

        val existing = userPermissionDao.findByUser_Id(userId)
                .stream().map(UserPermission::getPermission)
                .collect(toSet());

        permissions.removeAll(existing);

        val userPermissions = permissions.parallelStream()
                .map(permission -> new UserPermission(user, permission))
                .collect(toSet());

        return userPermissionDao.saveAll(userPermissions);
    }

    @Override
    public Collection<UserPermission> findAllByUserId(long userId) {
        return userPermissionDao.findByUser_Id(userId);
    }

    @Override
    public Page<UserPermission> findByUserId(long id, Pageable pageable) {
        return userPermissionDao.findByUser_Id(id, pageable);
    }

    @Override
    public void delete(long userPermissionId) {
        userPermissionDao.deleteById(userPermissionId);
    }

    @Override
    @Transactional
    public void delete(Collection<Long> userPermissionIds) {
        userPermissionDao.deleteAllByIds(userPermissionIds);
    }

    @Override
    public Collection<Permission> findUnassignedPermissions(long userId) {
        Collection<Permission> allPermissions = permissionService.findAll();
        val assignedPermissions = userPermissionDao.findByUser_Id(userId)
                .parallelStream()
                .map(UserPermission::getPermission)
                .collect(toList());

        allPermissions.removeAll(assignedPermissions);
        Collection<Permission> assignedGroupPermissions =
                this.groupPermissionService
                        .findAllByGroupId(userAccountDao.findById(userId).get().getGroup().getId())
                        .stream()
                        .map(GroupPermission::getPermission)
                        .collect(toList());
        allPermissions.removeAll(assignedGroupPermissions);
        return allPermissions;
    }

}
