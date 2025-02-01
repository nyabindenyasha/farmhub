package com.xplug.tech.usermanager.user;


import com.xplug.tech.usermanager.UserAccount;
import com.xplug.tech.usermanager.UserGroup;
import lombok.*;

import java.util.Objects;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserAccountResponse {

    private long id;

    private String username;

    private String email;

    private String firstName;

    private String lastName;

    private boolean isActive;

    private UserGroup group;

    private String phoneNumber;

    public static UserAccountResponse of(UserAccount userAccount) {
        Objects.requireNonNull(userAccount, "UserAccount cannot be null!");
        return UserAccountResponse.builder()
                .id(userAccount.getId())
                .username(userAccount.getUsername())
                .email(userAccount.getEmail())
                .firstName(userAccount.getFirstName())
                .lastName(userAccount.getLastName())
                .group(userAccount.getGroup())
                .phoneNumber(userAccount.getPhoneNumber())
                .build();
    }

}
