package com.xplug.tech.usermanager.usergroup;

import com.xplug.tech.usermanager.UserGroup;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Slf4j
@Component
public non-sealed class UserGroupMapperImpl implements UserGroupMapper {

    @Override
    public UserGroup userGroupFromUserGroupRequest(UserGroupRequest userGroupRequest) {
        Objects.requireNonNull(userGroupRequest, "UserGroupRequest must not be null");
        UserGroup userGroup = UserGroup.builder()
                .name(userGroupRequest.getName())
                .description(userGroupRequest.getDescription())
                .build();
        log.info("### UserGroup: {}", userGroup);
        return userGroup;
    }

    @Override
    public UserGroup userGroupFromUserGroupUpdateRequest(UserGroup userGroup, UserGroupUpdateRequest userGroupUpdateRequest) {
        return null;
    }

    @Override
    public UserGroupResponse userGroupResponseFromUserGroup(UserGroup userGroup) {
        Objects.requireNonNull(userGroup, "UserGroup must not be null");
        UserGroupResponse userGroupResponse = UserGroupResponse.builder()
                .id(userGroup.getId())
                .name(userGroup.getName())
                .description(userGroup.getDescription())
                .build();
        log.info("### UserGroupResponse: {}", userGroupResponse);
        return userGroupResponse;
    }

}
