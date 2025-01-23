package com.xplug.tech.personelinfo;

import lombok.*;

import javax.persistence.Embeddable;
import javax.validation.constraints.Email;

@Embeddable
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ContactDetails {

    @Email
    private String email;
    private String countryCode;
    private String phoneNumber;
    private String alternativeCountryCode;
    private String alternativeContactNumber;

}
