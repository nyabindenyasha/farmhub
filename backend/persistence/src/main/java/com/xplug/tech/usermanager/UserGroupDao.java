package com.xplug.tech.usermanager;

import com.xplug.tech.jpa.BaseRepository;

import java.util.Optional;

public interface UserGroupDao extends BaseRepository<UserGroup> {

    Optional<UserGroup> findByName(String name);

    UserGroup getByName(String name);

    boolean existsByName(String name);
}
