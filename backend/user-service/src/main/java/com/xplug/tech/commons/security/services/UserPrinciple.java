package com.xplug.tech.commons.security.services;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xplug.tech.usermanager.UserAccount;
import lombok.val;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Objects;


public class UserPrinciple implements UserDetails {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

    private String username;

    private String email;

    @JsonIgnore
    private String password;

    private boolean isAccountNonExpired;

    private boolean isAccountNonLocked;

    private boolean isEnabled;

    private boolean isCredentialsNonExpired;

    private Collection<? extends GrantedAuthority> authorities;

    public UserPrinciple() {
    }

    public UserPrinciple(Long id, String name,
                         String username, String email, String password,
                         Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

    public UserPrinciple build(UserAccount user) {
//        List<GrantedAuthority> authorities = user.getPermissions().stream().map(role ->
//                new SimpleGrantedAuthority(role.getName())
//        ).collect(Collectors.toList());

        this.isAccountNonExpired = user.isActive();
        this.isAccountNonLocked = user.isActive();
        this.isCredentialsNonExpired = user.isActive();
        this.isEnabled = user.isActive();

        val authorities = user.getAuthorities();

        user.addPermission(new SimpleGrantedAuthority(user.getGroup().getName().toUpperCase()));

        return new UserPrinciple(
                user.getId(),
                user.getFirstName(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                authorities
        );
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
//        return this.isAccountNonExpired;
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
//        return this.isAccountNonLocked;
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
//        return this.isCredentialsNonExpired;
        return true;
    }

    @Override
    public boolean isEnabled() {
//        return this.isEnabled;
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserPrinciple user = (UserPrinciple) o;
        return Objects.equals(id, user.id);
    }
}