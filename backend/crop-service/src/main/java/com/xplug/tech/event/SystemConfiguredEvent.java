package com.xplug.tech.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class SystemConfiguredEvent extends ApplicationEvent {

    public SystemConfiguredEvent(Object source) {
        super(source);
    }

}