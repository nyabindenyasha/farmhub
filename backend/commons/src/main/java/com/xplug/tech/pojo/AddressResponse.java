package com.xplug.tech.pojo;


import com.xplug.tech.personelinfo.Address;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Setter
@Getter
@Builder
public class AddressResponse {
    private Long id;
    private String addressLine1;
    private String addressLine2;
    private String addressLine3;
    private String province;
    private String country;
    private String postalCode;
    private String addressType;


    public static AddressResponse of(Address address) {
        if (Objects.isNull(address)) {
            return null;
        }
        return AddressResponse.builder()
                .id(address.getId())
                .addressLine3(address.getCity())
                .addressLine2(address.getAddressLine2())
                .addressLine1(address.getAddressLine1())
                .province(address.getProvince())
                .postalCode(address.getPostalCode())
                .addressType(Objects.isNull(address.getAddressType()) ? "NOT DEFINED" : address.getAddressType().name())
                .country(address.getCountry())
                .build();

    }

    public static Set<AddressResponse> of(Collection<Address> addressCollection) {
        if (Objects.isNull(addressCollection)) {
            return Collections.emptySet();
        }
        return addressCollection.stream().map(AddressResponse::of).collect(Collectors.toSet());
    }
}
