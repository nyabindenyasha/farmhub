package com.xplug.tech.crop.management.dao;

import com.xplug.tech.crop.management.domain.CropStagesOfGrowth;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CropStagesOfGrowthDao extends JpaRepository<CropStagesOfGrowth, Long> {

    Optional<CropStagesOfGrowth> findByCropIdAndStageStartDateIdAndStageEndDateId(Long cropId, Long stageStartDateId, Long stageEndDateId);

    List<CropStagesOfGrowth> findByCropId(Long cropId);

}
