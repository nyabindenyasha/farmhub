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

    private String password;

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
                .firstName(userAccount.getFirstName())
                .lastName(userAccount.getLastName())
                .phoneNumber(userAccount.getPhoneNumber())
                .build();
    }

}
