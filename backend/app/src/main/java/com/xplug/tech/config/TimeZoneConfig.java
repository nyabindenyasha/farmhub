package com.xplug.tech.config;

import java.util.TimeZone;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class TimeZoneConfig {
    @PostConstruct
    public void init() {
        TimeZone.setDefault(TimeZone.getTimeZone("Africa/Harare"));
    }
}

