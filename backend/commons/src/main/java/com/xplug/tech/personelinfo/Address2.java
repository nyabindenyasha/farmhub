package com.xplug.tech.personelinfo;

import lombok.*;

import javax.persistence.Embeddable;

@Embeddable
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Address2 {
    private String addressLine1;
    private String addressLine2;
    private String addressLine3;
    private String province;
    private String country;
    private String postalCode;
    private AddressType addressType;

}
