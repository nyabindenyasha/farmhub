package com.xplug.tech.usermanager;


import com.xplug.tech.jpa.BaseEntity;
import com.xplug.tech.usermanager.Permission;
import com.xplug.tech.usermanager.UserGroup;
import lombok.*;

import javax.persistence.*;


@Setter
@Getter
@AllArgsConstructor
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GroupPermission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(optional = false)
    @JoinColumn(foreignKey = @ForeignKey(name = "Fk_group_permissions_groups"))
    private UserGroup userGroup;

    @ManyToOne(optional = false)
    @JoinColumn(foreignKey = @ForeignKey(name = "Fk_group_permissions_permissions"))
    private Permission permission;

    public GroupPermission(UserGroup userGroup, Permission permission) {
        this.userGroup = userGroup;
        this.permission = permission;
    }

}
