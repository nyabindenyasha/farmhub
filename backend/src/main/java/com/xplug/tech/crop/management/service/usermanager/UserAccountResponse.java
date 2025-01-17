package com.xplug.tech.crop.management.service.usermanager;

import com.xplug.tech.crop.management.domain.UserAccount;
import lombok.Builder;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

@Setter
@ToString
@Builder
public class UserAccountResponse {

    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String phoneNumber;

    public static UserAccountResponse of(UserAccount userAccount) {
        Objects.requireNonNull(userAccount, "UserAccount cannot be null!");
        return UserAccountResponse.builder()
                .id(userAccount.getId())
                .username(userAccount.getUsername())
                .firstName(userAccount.getFirstName())
                .lastName(userAccount.getLastName())
                .phoneNumber(userAccount.getPhoneNumber())
                .build();
    }

}
