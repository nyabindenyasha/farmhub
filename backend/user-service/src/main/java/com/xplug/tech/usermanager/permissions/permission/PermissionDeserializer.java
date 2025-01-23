package com.xplug.tech.usermanager.permissions.permission;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.xplug.tech.usermanager.Permission;
import com.xplug.tech.utils.BeanUtil;
import lombok.val;

import java.io.IOException;

import static java.util.Objects.isNull;

public class PermissionDeserializer extends JsonDeserializer<Permission> {

    @Override
    public Permission deserialize(JsonParser p, DeserializationContext context) throws IOException {
        val id = p.readValueAs(Long.class);
        return isNull(id) ? null : BeanUtil.getBean(PermissionService.class).findById(id);
    }
}

