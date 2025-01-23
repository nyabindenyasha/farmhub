package com.xplug.tech.usermanager.user;

import com.xplug.tech.usermanager.UserAccount;

public sealed interface UserAccountMapper permits UserAccountMapperImpl {

    UserAccount userAccountFromUserAccountRequest(UserAccountRequest userAccountRequest);

    UserAccount userAccountFromUserAccountUpdateRequest(UserAccount userAccount, UserAccountUpdateRequest userAccountUpdateRequest);

    UserAccountResponse userAccountResponseFromUserAccount(UserAccount userAccount);

}
