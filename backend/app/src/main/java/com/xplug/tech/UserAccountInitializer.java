package com.xplug.tech;

import com.xplug.tech.exception.InvalidRequestException;
import com.xplug.tech.usermanager.UserAccount;
import com.xplug.tech.usermanager.UserAccountDao;
import com.xplug.tech.usermanager.UserGroupEnum;
import com.xplug.tech.usermanager.permissions.group.GroupPermissionService;
import com.xplug.tech.usermanager.permissions.permission.PermissionService;
import com.xplug.tech.usermanager.user.UserAccountService;
import com.xplug.tech.usermanager.usergroup.UserGroupService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;


@Slf4j
@Configuration
@Order(3)
@DependsOn({"userGroupInitializer", "permissionsInitializer"})
@AllArgsConstructor
public class UserAccountInitializer implements InitializingBean {

    private final UserGroupService userGroupService;

    private final UserAccountService userAccountService;

    private final UserAccountDao userAccountDao;

    private final PermissionService permissionService;

    private final GroupPermissionService groupPermissionService;

    private final PasswordEncoder passwordEncoder;

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("### UserAccount initializer");

        var adminUserExists = userAccountService.existsByUsername("admin");

        if (!adminUserExists) {
            createInternal();
        }

        var farmerUserExists = userAccountService.existsByUsername("farmer");

        if (!farmerUserExists) {
            createFarmer();
        }
    }

    private UserAccount createInternal() {
        val usernameExists = userAccountDao.existsByUsername("admin");
        if (usernameExists) {
            throw new InvalidRequestException("Username already exists in the system");
        }
        val emailExists = userAccountDao.existsByEmail("nyabindenyasha@gmail.com");
        if (emailExists) {
            throw new InvalidRequestException("Email already exists in the system, already being used by another user.");
        }
        var adminGroup = userGroupService.getByName(UserGroupEnum.ADMIN.name());
        var permissions = permissionService.findAll();
        groupPermissionService.createFromPermissions(adminGroup.getId(), permissions);
        UserAccount userAccount = UserAccount.builder()
                .firstName("Admin")
                .lastName("Admin")
                .email("nyabindenyasha@gmail.com")
                .username("admin")
                .password(passwordEncoder.encode("admin@123"))
                .group(adminGroup)
                .phoneNumber("+263773202921")
                .isActive(false)
                .build();
        UserAccount newUser = userAccountDao.save(userAccount);
        log.info("New User: {}", newUser);
        return newUser;
    }

    private UserAccount createFarmer() {
        val usernameExists = userAccountDao.existsByUsername("farmer");
        if (usernameExists) {
            throw new InvalidRequestException("Username already exists in the system");
        }
        val emailExists = userAccountDao.existsByEmail("nyabindenyashae@gmail.com");
        if (emailExists) {
            throw new InvalidRequestException("Email already exists in the system, already being used by another user.");
        }
        var farmerGroup = userGroupService.getByName(UserGroupEnum.FARMER.name());

        UserAccount userAccount = UserAccount.builder()
                .firstName("Farmer")
                .lastName("Farmer")
                .email("nyabindenyashae@gmail.com")
                .username("farmer")
                .password(passwordEncoder.encode("farmer@123"))
                .group(farmerGroup)
                .phoneNumber("+263773202921")
                .isActive(false)
                .build();
        UserAccount newUser = userAccountDao.save(userAccount);
        log.info("New User: {}", newUser);
        return newUser;
    }
}
