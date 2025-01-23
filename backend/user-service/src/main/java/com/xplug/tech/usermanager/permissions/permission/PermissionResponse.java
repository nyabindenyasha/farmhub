package com.xplug.tech.usermanager.permissions.permission;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PermissionResponse {

    private long id;

    private String name;

    private String description;

}
