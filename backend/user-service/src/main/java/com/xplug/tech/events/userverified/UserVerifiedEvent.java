package com.xplug.tech.events.userverified;

import com.xplug.tech.usermanager.UserAccount;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;




@Getter
public class UserVerifiedEvent extends ApplicationEvent {

    private final UserAccount userAccount;

    public UserVerifiedEvent(Object source, UserAccount userAccount) {
        super(source);
        this.userAccount = userAccount;
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }
}
