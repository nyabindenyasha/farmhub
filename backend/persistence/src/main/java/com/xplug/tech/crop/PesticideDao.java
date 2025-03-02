package com.xplug.tech.crop;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PesticideDao extends JpaRepository<Pesticide, Long> {

    Optional<Pesticide> findByNameContainsIgnoreCaseOrAliasContainsIgnoreCase(String name, String alias);

}
