package com.xplug.tech.crop;

import com.xplug.tech.enums.CropScheduleType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface CropPesticideScheduleDao extends JpaRepository<CropPesticideSchedule, Long> {

    Set<CropPesticideSchedule> findByCropProgramId(Long cropProgramId);

    Optional<CropPesticideSchedule> findByCropProgramIdAndPesticideIdAndStageOfGrowthId(Long cropProgramId, Long pesticideId, Long stageOfGrowthId);

    List<CropPesticideSchedule> findByCropProgramCropIdAndCropProgramCropScheduleType(Long cropId, CropScheduleType cropScheduleType);

    Optional<CropPesticideSchedule> findByCropProgramCropIdAndCropProgramCropScheduleTypeAndStageOfGrowthId(Long cropId, CropScheduleType cropScheduleType, Long stageOfGrowthId);

}

