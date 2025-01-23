package com.xplug.tech.usermanager.usergroup;

import com.xplug.tech.usermanager.UserGroup;
import com.xplug.tech.usermanager.UserGroupEnum;
import lombok.val;

public sealed interface UserGroupMapper permits UserGroupMapperImpl {

    UserGroup userGroupFromUserGroupRequest(UserGroupRequest userGroupRequest);

    UserGroup userGroupFromUserGroupUpdateRequest(UserGroup userGroup, UserGroupUpdateRequest userGroupUpdateRequest);

    UserGroupResponse userGroupResponseFromUserGroup(UserGroup userGroup);

    static UserGroupRequest fromEnum(UserGroupEnum userGroupEnum) {
        val userGroupRequest = new UserGroupRequest();
        userGroupRequest.setDescription(userGroupEnum.name().replace("_", " "));
        userGroupRequest.setName(userGroupEnum.name());
        return userGroupRequest;
    }

}
