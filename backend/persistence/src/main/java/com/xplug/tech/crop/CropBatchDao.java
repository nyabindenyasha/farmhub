package com.xplug.tech.crop;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface CropBatchDao extends JpaRepository<CropBatch, Long> {

    List<CropBatch> findByUserAccountId(Long id);

    Optional<CropBatch> findByCropProgramIdAndUserAccountIdAndAndDateOfTransplant(Long cropProgramId, Long userAccountId, LocalDateTime dateOfTransplant);

}
