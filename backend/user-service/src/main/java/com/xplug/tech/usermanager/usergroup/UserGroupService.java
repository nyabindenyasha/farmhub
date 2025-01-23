package com.xplug.tech.usermanager.usergroup;

import com.xplug.tech.jpa.BaseService;
import com.xplug.tech.usermanager.UserGroup;


public interface UserGroupService extends BaseService<UserGroup, UserGroupRequest, UserGroupUpdateRequest> {

    UserGroup getByName(String name);

    boolean existsByName(String name);

    UserGroup createFromEnum(UserGroupRequest userGroupRequest);

}
