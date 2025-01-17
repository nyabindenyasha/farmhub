package com.xplug.tech.crop.management.service.usermanager;

import com.xplug.tech.crop.management.domain.UserAccount;

public sealed interface UserAccountService permits UserAccountServiceImpl {

    UserAccount create(UserAccount userAccount);

    UserAccount getById(Long id);

}
