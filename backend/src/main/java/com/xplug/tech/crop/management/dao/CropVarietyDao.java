package com.xplug.tech.crop.management.dao;

import com.xplug.tech.crop.management.domain.CropVariety;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CropVarietyDao extends JpaRepository<CropVariety, Long> {

    Optional<CropVariety> findByCropIdAndVariety(Long cropId, String variety);

}
