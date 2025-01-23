package com.xplug.tech.usermanager;

import com.xplug.tech.jpa.BaseEntity;
import com.xplug.tech.usermanager.UserAccount;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Token extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String value;

    @ManyToOne(optional = false)
    @JoinColumn(foreignKey = @ForeignKey(name = "Fk_tokens_users"))
    private UserAccount userAccount;

    private boolean used;

    private boolean expired;

    private LocalDateTime expiryDate;

    public Token(UserAccount userAccount) {
        this.userAccount = userAccount;
        this.expiryDate = LocalDateTime.now().plusHours(24);
    }

    @PostLoad
    @PostUpdate
    public void checkExpiry() {
        this.expired = this.expiryDate.isBefore(LocalDateTime.now());
    }

}
