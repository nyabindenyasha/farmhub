package com.xplug.tech.pojo;

import com.xplug.tech.personelinfo.AddressType;


public record AddressRequest(String city,

                             String addressLine1,

                             String addressLine2,

                             String province,

                             String country,

                             AddressType addressType,
                             Long policyId,
                             String postalCode) {

    public AddressRequest withPolicyId(Long policyId) {
        return new AddressRequest(city(), addressLine1(), addressLine2(), province(), country(), addressType(), policyId, postalCode());
    }

}
