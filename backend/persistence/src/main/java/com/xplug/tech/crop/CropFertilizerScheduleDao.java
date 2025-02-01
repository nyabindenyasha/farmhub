package com.xplug.tech.crop;

import com.xplug.tech.enums.CropScheduleType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface CropFertilizerScheduleDao extends JpaRepository<CropFertilizerSchedule, Long> {

    Set<CropFertilizerSchedule> findByCropScheduleId(Long cropScheduleId);

    Optional<CropFertilizerSchedule> findByCropScheduleIdAndFertilizerIdAndStageOfGrowthId(Long cropScheduleId, Long fertilizerId, Long stageOfGrowthId);

    List<CropFertilizerSchedule> findByCropScheduleCropIdAndCropScheduleCropScheduleType(Long cropId, CropScheduleType cropScheduleType);

    Optional<CropFertilizerSchedule> findByCropScheduleCropIdAndCropScheduleCropScheduleTypeAndStageOfGrowthId(Long cropId, CropScheduleType cropScheduleType, Long stageOfGrowthId);

}
