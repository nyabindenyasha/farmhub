package com.xplug.tech.crop.management.dao;

import com.xplug.tech.crop.management.domain.CropFarmer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface CropFarmerDao extends JpaRepository<CropFarmer, Long> {

    Optional<CropFarmer> findByCropIdAndUserAccountIdAndAndDateOfTransplant(Long cropId, Long userAccountId, LocalDate dateOfTransplant);

}
