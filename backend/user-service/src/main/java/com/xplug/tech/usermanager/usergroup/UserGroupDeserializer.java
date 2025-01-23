package com.xplug.tech.usermanager.usergroup;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.xplug.tech.usermanager.UserGroup;
import com.xplug.tech.utils.BeanUtil;
import lombok.val;

import java.io.IOException;

import static java.util.Objects.isNull;

public class UserGroupDeserializer extends JsonDeserializer<UserGroup> {

    @Override
    public UserGroup deserialize(JsonParser p, DeserializationContext context) throws IOException {
        val id = p.readValueAs(Long.class);
        return isNull(id) ? null : BeanUtil.getBean(UserGroupService.class).findById(id);
    }
}
