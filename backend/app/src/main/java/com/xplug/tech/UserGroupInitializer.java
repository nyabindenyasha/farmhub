package com.xplug.tech;

import com.xplug.tech.usermanager.UserGroupEnum;
import com.xplug.tech.usermanager.usergroup.UserGroupMapper;
import com.xplug.tech.usermanager.usergroup.UserGroupService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.annotation.Order;

import javax.annotation.Priority;
import java.util.stream.Stream;


@Slf4j
@Configuration
@Order(2)
@Priority(2)
@DependsOn("permissionsInitializer")
@AllArgsConstructor
public class UserGroupInitializer implements InitializingBean {

    private final UserGroupService userGroupService;

    private final UserGroupMapper userGroupMapper;

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("### UserGroupsService initializer: {}", (Object) UserGroupEnum.values());
        Stream.of(UserGroupEnum.values())
                .map(UserGroupMapper::fromEnum)
                .forEach(userGroupService::createFromEnum);
    }
}
