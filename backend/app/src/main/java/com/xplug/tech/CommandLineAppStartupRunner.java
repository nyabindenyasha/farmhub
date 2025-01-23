package com.xplug.tech;

import com.xplug.tech.cropdata.FertilizerDataLoader;
import com.xplug.tech.cropdata.PesticideDataLoader;
import com.xplug.tech.exception.InvalidRequestException;
import com.xplug.tech.fertilizer.FertilizerRequest;
import com.xplug.tech.pesticide.PesticideRequest;
import com.xplug.tech.usermanager.UserAccount;
import com.xplug.tech.usermanager.UserAccountDao;
import com.xplug.tech.usermanager.UserGroupEnum;
import com.xplug.tech.usermanager.permissions.group.GroupPermissionService;
import com.xplug.tech.usermanager.permissions.permission.PermissionService;
import com.xplug.tech.usermanager.user.UserAccountService;
import com.xplug.tech.usermanager.usergroup.UserGroupService;
import com.xplug.tech.utils.AppConstantsConfig;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@Component
@AllArgsConstructor
public class CommandLineAppStartupRunner implements CommandLineRunner {

    private final UserGroupService userGroupService;

    private final UserAccountService userAccountService;

    private final UserAccountDao userAccountDao;

    private final PermissionService permissionService;

    private final GroupPermissionService groupPermissionService;

    private final PasswordEncoder passwordEncoder;

    private final FertilizerDataLoader fertilizerDataLoader;

    private final PesticideDataLoader pesticideDataLoader;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        log.info("Creating New User.");
        try {

            var adminUserExists = userAccountService.existsByUsername("admin");

            if (!adminUserExists) {
                createInternal();
            }

            AppConstantsConfig.init();
            FertilizerRequest fertilizer = fertilizerDataLoader.getByName("Compound C");
            PesticideRequest pesticide = pesticideDataLoader.getByName("lambda");
            log.info("Fertilizer: {}", fertilizer);
            log.info("Pesticide: {}", pesticide);
            log.info("### Application Constants Initialized Successfully!");

        } catch (Exception e) {
            log.info("Exception @ CommandLineRunner: " + e.getMessage());
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
        var adminGroup = userGroupService.getByName(UserGroupEnum.SUPER_ADMIN.name());
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

}
