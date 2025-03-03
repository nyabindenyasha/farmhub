package com.xplug.tech.crop;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FertilizerDao extends JpaRepository<Fertilizer, Long> {

    Optional<Fertilizer> findByNameIgnoreCaseOrAliasIgnoreCase(String name, String alias);

}