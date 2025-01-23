package com.xplug.tech.events.usercreated;

import com.xplug.tech.usermanager.Token;
import com.xplug.tech.usermanager.UserAccount;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;


@Getter
public class UserCreatedEvent extends ApplicationEvent {

    private final UserAccount userAccount;

    private final Token token;

    public UserCreatedEvent(Object source, UserAccount userAccount, Token token) {
        super(source);
        this.userAccount = userAccount;
        this.token = token;
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public Token getToken() {
        return token;
    }
}
