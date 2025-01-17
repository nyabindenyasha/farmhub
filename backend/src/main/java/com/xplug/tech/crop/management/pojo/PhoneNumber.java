package com.xplug.tech.crop.management.pojo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PhoneNumber {
    //todo countryCode, mno, number
    private String phoneNumber;
    private String label;
    private boolean isPrimary;

}
