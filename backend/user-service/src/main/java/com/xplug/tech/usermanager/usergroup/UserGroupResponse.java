package com.xplug.tech.usermanager.usergroup;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserGroupResponse {

    private long id;

    private String name;

    private String description;

}
