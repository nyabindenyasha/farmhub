package com.xplug.tech.usermanager.permissions.group;

import com.xplug.tech.exception.InvalidRequestException;
import com.xplug.tech.usermanager.GroupPermission;
import com.xplug.tech.usermanager.GroupPermissionDao;
import com.xplug.tech.usermanager.Permission;
import com.xplug.tech.usermanager.permissions.PermissionsEnum;
import com.xplug.tech.usermanager.permissions.permission.PermissionService;
import com.xplug.tech.usermanager.usergroup.UserGroupService;
import com.xplug.tech.utils.PageBuilder;
import lombok.val;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;


@Service
class GroupPermissionServiceImpl implements GroupPermissionService {

    private final UserGroupService userGroupService;

    private final PermissionService permissionService;

    private final GroupPermissionDao groupPermissionDao;

    private final PageBuilder pageBuilder;

    GroupPermissionServiceImpl(UserGroupService userGroupService, PermissionService permissionService, GroupPermissionDao groupPermissionDao, PageBuilder pageBuilder) {
        this.userGroupService = userGroupService;
        this.permissionService = permissionService;
        this.groupPermissionDao = groupPermissionDao;
        this.pageBuilder = pageBuilder;
    }

    @Override
    public GroupPermission create(GroupPermissionRequest groupPermissionRequest) {

        val group = groupPermissionRequest.getGroup();
        val groupId = group.getId();
        val permissionId = groupPermissionRequest.getPermission().getId();

        val permission = permissionService.findById(permissionId);

        val permssionEnum = PermissionsEnum.valueOf(permission.getName());

        if (permssionEnum.isForAdminOnly() && !(group.getName().equalsIgnoreCase("ADMIN") || group.getName().equalsIgnoreCase("ADMINS")))
            throw new InvalidRequestException("This permission is for admins only. Consider upgrading user(s) in that group to admin group.");

        val optionalPermission = groupPermissionDao.findByUserGroup_IdAndPermission_Id(groupId, permissionId);

        if (!optionalPermission.isEmpty()) {
            return optionalPermission.get(0);
        }

        val groupPermission = new GroupPermission(group, permission);

        return groupPermissionDao.save(groupPermission);
    }

    @Override
    public GroupPermission create(long groupId, long permissionId) {

        val optionalPermission = groupPermissionDao.findByUserGroup_IdAndPermission_Id(groupId, permissionId);

        if (!optionalPermission.isEmpty()) {
            return optionalPermission.get(0);
        }

        val group = userGroupService.findById(groupId);

        val permission = permissionService.findById(permissionId);

        val permssionEnum = PermissionsEnum.valueOf(permission.getName());

        if (permssionEnum.isForAdminOnly() && !(group.getName().equalsIgnoreCase("ADMIN") || group.getName().equalsIgnoreCase("ADMINS")))
            throw new InvalidRequestException("This permission is for admins only. Consider upgrading user(s) in that group to admin group.");

        val groupPermission = new GroupPermission(group, permission);

        return groupPermissionDao.save(groupPermission);
    }

    @Override
    public Collection<GroupPermission> create(long groupId, Collection<Long> permissionIds) {

        val group = userGroupService.findById(groupId);

        val permissions = permissionService.findByIds(permissionIds);

        val existing = groupPermissionDao.findByUserGroup_Id(groupId)
                .stream().map(GroupPermission::getPermission)
                .collect(toSet());

        permissions.forEach(permission -> {
            val permssionEnum = PermissionsEnum.valueOf(permission.getName());
            if (permssionEnum.isForAdminOnly())
                permissions.remove(permission);
        });

        permissions.removeAll(existing);

        val groupPermissions = permissions.parallelStream()
                .map(permission -> new GroupPermission(group, permission))
                .collect(toSet());

        return groupPermissionDao.saveAll(groupPermissions);
    }

    @Override
    public Collection<GroupPermission> createFromPermissions(long groupId, Collection<Permission> permissions) {

        val group = userGroupService.findById(groupId);

        val existing = groupPermissionDao.findByUserGroup_Id(groupId)
                .stream().map(GroupPermission::getPermission)
                .collect(toSet());

        permissions.removeAll(existing);

        val groupPermissions = permissions.parallelStream()
                .map(permission -> new GroupPermission(group, permission))
                .collect(toSet());

        return groupPermissionDao.saveAll(groupPermissions);
    }

    @Override
    public Collection<GroupPermission> findAllByGroupId(long groupId) {
        return groupPermissionDao.findByUserGroup_Id(groupId);
    }

    @Override
    public Page<GroupPermission> findByGroupId(long id, Pageable pageable) {
        return groupPermissionDao.findByUserGroup_Id(id, pageable);
    }

    @Override
    public Collection<Permission> getPermissionsThatBelongToGroup(long groupId) {

        val groupPermissions = findAllByGroupId(groupId);

        Collection<Permission> permissionThatBelongToGroup = new ArrayList<>();

        if (!groupPermissions.isEmpty())
            groupPermissions.forEach(groupPermission -> {
                permissionThatBelongToGroup.add(groupPermission.getPermission());
            });

        return permissionThatBelongToGroup;
    }

    @Override
    public Page<Permission> getPermissionsThatBelongToGroup(long groupId, Pageable pageable) {

        val permissions = (List<Permission>) getPermissionsThatBelongToGroup(groupId);

        return pageBuilder.collectionToPage(permissions, pageable);

    }

    @Override
    public void delete(long groupPermissionId) {
        groupPermissionDao.deleteById(groupPermissionId);
    }

    @Override
    @Transactional
    public void delete(Collection<Long> groupPermissionIds) {
        groupPermissionDao.deleteAllByIds(groupPermissionIds);
    }

    @Override
    public Collection<Permission> findUnassignedPermissions(long groupId) {
        val allPermissions = permissionService.findAll();
        val assignedPermissions = groupPermissionDao.findByUserGroup_Id(groupId)
                .parallelStream()
                .map(GroupPermission::getPermission)
                .collect(toList());

        allPermissions.removeAll(assignedPermissions);

        return allPermissions;
    }

}
