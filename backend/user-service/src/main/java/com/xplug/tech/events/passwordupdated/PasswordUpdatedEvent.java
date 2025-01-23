package com.xplug.tech.events.passwordupdated;

import com.xplug.tech.usermanager.Token;
import com.xplug.tech.usermanager.UserAccount;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;




@Getter
public class PasswordUpdatedEvent extends ApplicationEvent {

    private final UserAccount userAccount;

    private Token token;

    public PasswordUpdatedEvent(Object source, UserAccount userAccount) {
        super(source);
        this.userAccount = userAccount;
    }
}
