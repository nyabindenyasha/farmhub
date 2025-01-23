package com.xplug.tech.commons.security.jwt;

import com.xplug.tech.usermanager.UserAccount;
import lombok.Data;


@Data
public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private String username;
    private UserAccount userAccount;
//    private String[] authorities;

    public JwtResponse(String accessToken, String username, UserAccount userAccount) {
        this.token = accessToken;
        this.username = username;
        this.userAccount = userAccount;
//        this.authorities = Arrays.stream(authorities.toArray())
//                .map(Object::toString)
//                .toArray(String[]::new);
    }

}