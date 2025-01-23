package com.xplug.tech.events.forgotpassword;

import com.xplug.tech.usermanager.Token;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;




@Getter
public class ForgotPasswordEvent extends ApplicationEvent {

    private final Token token;

    public ForgotPasswordEvent(Object source, Token token) {
        super(source);
        this.token = token;
    }

    public Token getToken() {
        return token;
    }
}
