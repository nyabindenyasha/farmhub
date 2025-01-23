package com.xplug.tech.usermanager;

import com.xplug.tech.jpa.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface PermissionDao extends BaseRepository<Permission> {

    Optional<Permission> findByName(String name);

    Permission getByName(String name);

    boolean existsByName(String name);

}


