package com.xplug.tech.usermanager;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xplug.tech.jpa.BaseEntity;
import lombok.*;
import org.hibernate.annotations.NaturalId;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.HashSet;

import static java.util.Objects.isNull;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserAccount implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull
    @Column(unique = true, updatable = false)
    @Size(min = 3, max = 50)
    private String username;
    @NaturalId
    @NotBlank
    @Size(max = 250)
    @Email
    private String email;
    @JsonIgnore
    @NotBlank
    @Size(min = 6, max = 250)
    private String password;

    private String firstName;

    private String lastName;

    private boolean isActive;

    @ManyToOne
    private UserGroup group;

    private String phoneNumber;

    @Transient
    @JsonIgnore
    private Collection<GrantedAuthority> authorities;

    public Collection<GrantedAuthority> getAuthorities() {
        if (isNull(authorities)) {
            authorities = new HashSet<>();
        }
        return authorities;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return isActive;
    }

    public UserAccount(String firstName, String username, String email, String password) {
        this.firstName = firstName;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public void addPermission(SimpleGrantedAuthority simpleGrantedAuthority) {
        this.getAuthorities().add(simpleGrantedAuthority);
    }

}
