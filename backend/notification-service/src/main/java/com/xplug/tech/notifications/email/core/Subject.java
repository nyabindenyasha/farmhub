package com.xplug.tech.notifications.email.core;

import lombok.Data;

@Data
public final class Subject {

    private String value;

    public Subject(String value) {
        this.value = value;
    }

    public Subject() {
    }
}
