package com.xplug.tech.crop;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CropDao extends JpaRepository<Crop, Long> {

    Optional<Crop> findByNameContainsIgnoreCase(String name);

}
