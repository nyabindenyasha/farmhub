package com.xplug.tech.crop.management.dao;

import com.xplug.tech.crop.management.domain.Pesticide;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PesticideDao extends JpaRepository<Pesticide, Long> {

    Optional<Pesticide> findByNameOrAliasIgnoreCase(String name, String alias);

}
