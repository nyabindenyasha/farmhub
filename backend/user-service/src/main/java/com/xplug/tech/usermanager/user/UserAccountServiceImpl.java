package com.xplug.tech.usermanager.user;

import com.xplug.tech.events.forgotpassword.ForgotPasswordEvent;
import com.xplug.tech.events.passwordupdated.PasswordUpdatedEvent;
import com.xplug.tech.events.usercreated.UserCreatedEvent;
import com.xplug.tech.events.userverified.UserVerifiedEvent;
import com.xplug.tech.exception.InvalidRequestException;
import com.xplug.tech.exception.ItemNotFoundException;
import com.xplug.tech.jpa.BaseServiceImpl;
import com.xplug.tech.usermanager.UserAccount;
import com.xplug.tech.usermanager.UserAccountDao;
import com.xplug.tech.usermanager.permissions.permission.PermissionService;
import com.xplug.tech.usermanager.token.TokenService;
import com.xplug.tech.usermanager.usergroup.UserGroupService;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;
import java.security.Principal;
import java.util.Collection;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Slf4j
@Service
public class UserAccountServiceImpl extends BaseServiceImpl<UserAccount, UserAccountRequest, UserAccountUpdateRequest> implements UserAccountService {

    private final UserAccountDao userAccountDao;

    private final PasswordEncoder passwordEncoder;

    private final TokenService tokenService;

    private final UserGroupService userGroupService;

    private final PermissionService permissionService;

    private final UserAccountMapper userAccountMapper;

    private final ApplicationEventPublisher applicationEventPublisher;

    UserAccountServiceImpl(UserAccountDao userAccountDao, PasswordEncoder passwordEncoder, TokenService tokenService, UserGroupService userGroupService, PermissionService permissionService, UserAccountMapper userAccountMapper, ApplicationEventPublisher applicationEventPublisher) {
        super(userAccountDao);
        this.userAccountDao = userAccountDao;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
        this.userGroupService = userGroupService;
        this.permissionService = permissionService;
        this.userAccountMapper = userAccountMapper;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    protected Class<UserAccount> getEntityClass() {
        return UserAccount.class;
    }

    @Override

    public UserAccount create(UserAccountRequest userAccountRequest, Principal principal) {

        if (isNull(principal)) {
            throw new InvalidRequestException("You need to be logged in to perform this action.");
        }

        var userGroup = userGroupService.findById(userAccountRequest.getGroupId());
        userAccountRequest.setUserGroup(userGroup);

        val owner = findByUsername(principal.getName());

//        userAccountRequest.setOwnerUuid(owner.getOwnerUuid());
//        userAccountRequest.setTenantId(owner.getTenantId());
//
//
//
//        userAccountRequest.setAdmin(userAccountRequest.isAdmin());
//        userAccountRequest.setVendorAdmin(userAccountRequest.isVendorAdmin());
//        userAccountRequest.setCompanyAdmin(userAccountRequest.isCompanyAdmin());

        return createInternal(userAccountRequest);
    }


    @Override
    public UserAccount update(UserAccountUpdateRequest userAccountUpdateRequest) {
        return updateInternal(userAccountUpdateRequest);
    }

    @Override
    public UserAccount update(UserAccountUpdateRequest userAccountUpdateRequest, Principal principal) {
        if (isNull(principal)) {
            throw new InvalidRequestException("You need to be logged in to perform this action.");
        }
        userAccountUpdateRequest.setUsername(principal.getName());
        return updateInternal(userAccountUpdateRequest);
    }

    @Override
    public UserAccount findByUsername(String username) {
        return userAccountDao.findByUsername(username)
                .orElseThrow(() -> new ItemNotFoundException("User record was not found for the supplied username"));
    }

    @Override
    public UserAccount findByEmail(String email) {
        return userAccountDao.findByEmail(email)
                .orElseThrow(() -> new ItemNotFoundException("User record was not found for the supplied email"));
    }

    @Override
    public Collection<UserAccount> findByGroup(long groupId) {
        return userAccountDao.findByGroup_Id(groupId);
    }

    @Override
    public void forgotPassword(String username, String email) {
        boolean userByUsernameExists = userAccountDao.existsByUsername(username);
        boolean userByEmailExists = userAccountDao.existsByEmail(email);
        if (userByUsernameExists) {
            val user = findByUsername(username);
            val token = tokenService.create(user);

            applicationEventPublisher.publishEvent(new ForgotPasswordEvent(this, token));
        } else if (userByEmailExists) {
            val user = findByEmail(email);
            val token = tokenService.create(user);

            applicationEventPublisher.publishEvent(new ForgotPasswordEvent(this, token));
        } else
            throw new InvalidRequestException("No User record found for the supplied username and or email");
    }


    @Override
    public void delete(Long id) {
        try {
            super.delete(id);
        } catch (ConstraintViolationException var3) {
            throw new InvalidRequestException("You can not delete this record is might be used by another record");
        }
    }

    @Override
    public UserAccount create(UserAccountRequest userAccountRequest) {
        return createInternal(userAccountRequest);
    }

    private UserAccount createInternal(UserAccountRequest userAccountRequest) {
        val usernameExists = userAccountDao.existsByUsername(userAccountRequest.getUsername());
        if (usernameExists) {
            throw new InvalidRequestException("Username already exists in the system");
        }
        val emailExists = userAccountDao.existsByEmail(userAccountRequest.getEmail());
        if (emailExists) {
            throw new InvalidRequestException("Email already exists in the system, already being used by another user.");
        }
        val user = userAccountMapper.userAccountFromUserAccountRequest(userAccountRequest);
        val userGroup = userGroupService.findById(userAccountRequest.getGroupId());
        user.setGroup(userGroup);
        String hashedPassword = passwordEncoder.encode(userAccountRequest.getPassword());
        user.setPassword(hashedPassword);
        val newUser = userAccountDao.save(user);
        log.info("New User: {}", newUser);
        val token = tokenService.create(user);
   //     applicationEventPublisher.publishEvent(new UserCreatedEvent(this, newUser, token));
        return newUser;
    }


    private UserAccount updateInternal(UserAccountUpdateRequest userAccountUpdateRequest) {
        final UserAccount user;
        if (nonNull(userAccountUpdateRequest.getUsername())) {
            user = userAccountDao.findByUsername(userAccountUpdateRequest.getUsername())
                    .orElseThrow(() -> new ItemNotFoundException("User record was not found"));
        } else {
            user = findById(userAccountUpdateRequest.getId());
        }
        var updatedUser = userAccountMapper.userAccountFromUserAccountUpdateRequest(user, userAccountUpdateRequest);
        return userAccountDao.save(updatedUser);
    }

    public void resetPassword(ResetPasswordContext resetPasswordContext) {
        if (!resetPasswordContext.getPassword().equals(resetPasswordContext.getConfirmPassword())) {
            throw new InvalidRequestException("Password and confirm password do not match");
        }
        val token = tokenService.findByValue(resetPasswordContext.getToken());
        tokenService.validateToken(token);
        tokenService.useToken(token);
        val user = token.getUserAccount();
        user.setPassword(passwordEncoder.encode(resetPasswordContext.getPassword()));
        userAccountDao.save(user);
    }

    @Override
    public void resetPassword(long userId) {

    }

    public UserAccount updatePassword(UpdatePasswordContext updatePasswordContext) {
        if (!updatePasswordContext.getNewPassword().equals(updatePasswordContext.getConfirmNewPassword())) {
            throw new InvalidRequestException("Password and confirm password do not match");
        }
        val user = findByUsername(updatePasswordContext.getUsername());
        if (!passwordEncoder.matches(updatePasswordContext.getOldPassword(), user.getPassword())) {
            throw new InvalidRequestException("Wrong old password has been provided.");
        }
        if (updatePasswordContext.getOldPassword().equals(updatePasswordContext.getNewPassword())) {
            throw new InvalidRequestException("You can not repeat the same password you were using");
        }
        user.setPassword(passwordEncoder.encode(updatePasswordContext.getNewPassword()));
        val updatedUser = userAccountDao.save(user);
        applicationEventPublisher.publishEvent(new PasswordUpdatedEvent(this, updatedUser));
        return updatedUser;
    }

    @Override
    public boolean existsByUsername(String username) {
        return userAccountDao.existsByUsername(username);
    }

    @Override
    public UserAccount activateOrDeActivate(long userAccountId, boolean isActive) {
        val user = userAccountDao.findById(userAccountId).orElseThrow(() -> new ItemNotFoundException("User Account"));
        user.setActive(isActive);
        return userAccountDao.save(user);
    }

    @Override
    public UserAccount save(UserAccount userAccount) {
        return userAccountDao.save(userAccount);
    }

    public void verifyUser(String tokenValue) {
        val token = tokenService.findByValue(tokenValue);
        tokenService.validateToken(token);
        tokenService.useToken(token);
        val user = token.getUserAccount();
        user.setActive(true);
        val verifiedUser = userAccountDao.save(user);
        applicationEventPublisher.publishEvent(new UserVerifiedEvent(this, verifiedUser));
    }

    @Override
    public Collection<UserAccount> findAllMyUsers() {
//        return userAccountRepo.findByTenantId("0");
        return null;
    }

    @Override
    public Page<UserAccount> findMyActiveUsers(String tenantId, Pageable pageable) {
//        val users = userAccountRepo.findByTenantId(tenantId, pageable)
//                .filter(UserAccount::isActive).toList();
//        return new PageImpl<>(users);
        return null;
    }

    @Override
    public Page<UserAccount> findMyNonActiveUsers(String tenantId, Pageable pageable) {
//        val users = userAccountRepo.findByTenantId(tenantId, pageable)
//                .filter(user -> !user.isActive()).toList();
//        return new PageImpl<>(users);
        return null;
    }

}
