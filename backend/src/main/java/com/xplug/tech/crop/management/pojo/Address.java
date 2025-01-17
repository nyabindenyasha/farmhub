package com.xplug.tech.crop.management.pojo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Address {

    private String addressLine1;
    private String addressLine2;
    private Object postalCode;
    private String city;
    private String province;
    private String country;

}
