package com.xplug.tech.usermanager;

import com.xplug.tech.jpa.BaseEntity;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "user_permissions")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserPermission extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(optional = false)
    @JoinColumn(foreignKey = @ForeignKey(name = "Fk_user_permissions_users"))
    private UserAccount user;

    @ManyToOne(optional = false)
    @JoinColumn(foreignKey = @ForeignKey(name = "Fk_user_permissions_permissions"))
    private Permission permission;

    public UserPermission(UserAccount user, Permission permission) {
        this.user = user;
        this.permission = permission;
    }

}
