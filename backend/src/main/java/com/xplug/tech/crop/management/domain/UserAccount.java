package com.xplug.tech.crop.management.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@ToString
public class UserAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, updatable = false)
    @Size(min = 3, max = 250)
    private String username;

    @NotNull
    @Size(min = 6, max = 250)
    private String password;

    private String firstName;

    private String lastName;

    private String phoneNumber;

}
