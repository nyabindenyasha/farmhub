package com.xplug.tech.usermanager.user;

import com.xplug.tech.usermanager.UserAccount;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Slf4j
@Component
public non-sealed class UserAccountMapperImpl implements UserAccountMapper {

    @Override
    public UserAccount userAccountFromUserAccountRequest(UserAccountRequest userAccountRequest) {
        Objects.requireNonNull(userAccountRequest, "UserAccountRequest must not be null");
        UserAccount userAccount = UserAccount.builder()
                .firstName(userAccountRequest.getFirstName())
                .lastName(userAccountRequest.getLastName())
                .email(userAccountRequest.getEmail())
                .username(userAccountRequest.getUsername())
                .password(userAccountRequest.getPassword())
                //todo
                .group(userAccountRequest.getUserGroup())
                .phoneNumber(userAccountRequest.getPhoneNumber())
                .isActive(false)
                .build();
        log.info("### UserAccount: {}", userAccount);
        return userAccount;
    }

    @Override
    public UserAccount userAccountFromUserAccountUpdateRequest(UserAccount userAccount, UserAccountUpdateRequest userAccountUpdateRequest) {
        Objects.requireNonNull(userAccountUpdateRequest, "UserAccountUpdateRequest must not be null");
        Objects.requireNonNull(userAccount, "User Account must not be null");
        userAccount.setFirstName(userAccountUpdateRequest.getFirstName());
        userAccount.setLastName(userAccountUpdateRequest.getLastName());
        userAccount.setEmail(userAccountUpdateRequest.getEmail());
        userAccount.setPassword(userAccountUpdateRequest.getPassword());
        userAccount.setActive(userAccountUpdateRequest.isActive());
        userAccount.setGroup(userAccountUpdateRequest.getGroup());
        userAccount.setPhoneNumber(userAccountUpdateRequest.getPhoneNumber());
        return userAccount;
    }

    @Override
    public UserAccountResponse userAccountResponseFromUserAccount(UserAccount userAccount) {
        Objects.requireNonNull(userAccount, "UserAccount must not be null");
        UserAccountResponse userAccountResponse = UserAccountResponse.builder()
                .firstName(userAccount.getFirstName())
                .lastName(userAccount.getLastName())
                .email(userAccount.getEmail())
                .username(userAccount.getUsername())
                //todo
                .group(userAccount.getGroup())
                .phoneNumber(userAccount.getPhoneNumber())
                .isActive(userAccount.isActive())
                .build();
        log.info("### UserAccountResponse: {}", userAccountResponse);
        return userAccountResponse;
    }

}
