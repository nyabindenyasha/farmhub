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

    private final FertilizerDataLoader fertilizerDataLoader;

    private final PesticideDataLoader pesticideDataLoader;

    @Override
    @Transactional
    public void run(String... args) {

        try {
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

}
