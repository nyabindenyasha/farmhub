package com.xplug.tech.usermanager.usergroup;

import com.xplug.tech.exception.InvalidRequestException;
import com.xplug.tech.jpa.BaseServiceImpl;
import com.xplug.tech.usermanager.UserGroup;
import com.xplug.tech.usermanager.UserGroupDao;
import lombok.val;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;


@Service
class UserGroupServiceImpl extends BaseServiceImpl<UserGroup, UserGroupRequest, UserGroupUpdateRequest> implements UserGroupService {

    private final UserGroupDao userGroupDao;

    private final UserGroupMapper userGroupMapper;

    UserGroupServiceImpl(UserGroupDao userGroupDao, UserGroupMapper userGroupMapper) {
        super(userGroupDao);
        this.userGroupDao = userGroupDao;
        this.userGroupMapper = userGroupMapper;
    }

    @Override
    protected Class<UserGroup> getEntityClass() {
        return UserGroup.class;
    }

    @Override
    public UserGroup create(UserGroupRequest request) {
        boolean detailsExists = userGroupDao.existsByName(request.getName());
        if (detailsExists) {
            throw new InvalidRequestException("UserGroups with the same name already exists");
        }
        UserGroup role = userGroupMapper.userGroupFromUserGroupRequest(request);
        return userGroupDao.save(role);
    }

    @Override
    public UserGroup createFromEnum(UserGroupRequest request) {
        val optionalUserGroups = userGroupDao.findByName(request.getName());
        if (optionalUserGroups.isPresent()) {
            val userGroups = optionalUserGroups.get();
            userGroups.setDescription(request.getDescription());
            return userGroupDao.save(userGroups);
        } else {
            UserGroup userGroup = userGroupMapper.userGroupFromUserGroupRequest(request);
            return userGroupDao.save(userGroup);
        }
    }

    @Override
    public UserGroup update(UserGroupUpdateRequest request) {
        boolean detailsExists = userGroupDao.existsByName(request.getName());
        UserGroup userGroup = findById(request.getId());
        var updatedUserGroup = userGroupMapper.userGroupFromUserGroupUpdateRequest(userGroup, request);
        return userGroupDao.save(updatedUserGroup);
    }

    @Override
    public void delete(Long id) {
        try {
            super.delete(id);
        } catch (ConstraintViolationException var3) {
            throw new InvalidRequestException("You can not delete this record is might be used by another record");
        }
    }

    @Override
    public UserGroup getByName(String name) {
        return userGroupDao.getByName(name);
    }

    @Override
    public boolean existsByName(String name) {
        return userGroupDao.existsByName(name);
    }

}