package com.xplug.tech.usermanager.user;

import com.xplug.tech.jpa.BaseService;
import com.xplug.tech.usermanager.UserAccount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.security.Principal;
import java.util.Collection;

public interface UserAccountService extends BaseService<UserAccount, UserAccountRequest, UserAccountUpdateRequest> {

    UserAccount findByUsername(String username);

    UserAccount findByEmail(String email);

    UserAccount create(UserAccountRequest createDto, Principal principal);

    UserAccount update(UserAccountUpdateRequest userAccountUpdateRequest, Principal principal);

    Collection<UserAccount> findByGroup(long groupId);

    void forgotPassword(String username, String email);

    UserAccount updatePassword(UpdatePasswordContext updatePasswordContext);

    void resetPassword(ResetPasswordContext resetPasswordContext);

    void resetPassword(long userId);

    boolean existsByUsername(String username);

    UserAccount activateOrDeActivate(long userAccountId, boolean isActive);

    UserAccount save(UserAccount userAccount);

    void verifyUser(String tokenValue);

    Collection<UserAccount> findAllMyUsers();

    Page<UserAccount> findMyActiveUsers(String tenantId, Pageable pageable);

    Page<UserAccount> findMyNonActiveUsers(String tenantId, Pageable pageable);
}


