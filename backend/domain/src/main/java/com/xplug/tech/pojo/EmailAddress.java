package com.xplug.tech.pojo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailAddress {

    private String emailAddress;
    private String label;
    private boolean isPrimary;

}
