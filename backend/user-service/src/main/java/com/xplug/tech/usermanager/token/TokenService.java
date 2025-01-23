package com.xplug.tech.usermanager.token;


import com.xplug.tech.usermanager.Token;
import com.xplug.tech.usermanager.UserAccount;

public interface TokenService {

    Token create(UserAccount userAccount);

    void useToken(Token token);

    Token findByValue(String tokenValue);

    void validateToken(Token token);

}
