package com.xplug.tech.crop;

import com.xplug.tech.enums.CropScheduleType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface CropFertilizerScheduleDao extends JpaRepository<CropFertilizerSchedule, Long> {

    Set<CropFertilizerSchedule> findByCropProgramId(Long cropProgramId);

    Optional<CropFertilizerSchedule> findByCropProgramIdAndFertilizerIdAndStageOfGrowthId(Long cropProgramId, Long fertilizerId, Long stageOfGrowthId);

    List<CropFertilizerSchedule> findByCropProgramCropIdAndCropProgramCropScheduleType(Long cropId, CropScheduleType cropScheduleType);

    Optional<CropFertilizerSchedule> findByCropProgramCropIdAndCropProgramCropScheduleTypeAndStageOfGrowthId(Long cropId, CropScheduleType cropScheduleType, Long stageOfGrowthId);

}
