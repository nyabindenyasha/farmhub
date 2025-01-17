package com.xplug.tech.crop.management.dao;

import com.xplug.tech.crop.management.domain.Fertilizer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FertilizerDao extends JpaRepository<Fertilizer, Long> {

    Optional<Fertilizer> findByNameOrAliasIgnoreCase(String name, String alias);

}