package com.xplug.tech.crop;

import com.xplug.tech.enums.CropScheduleType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CropFertilizerScheduleTaskDao extends JpaRepository<CropFertilizerScheduleTask, Long> {

//    Optional<CropFertilizerScheduleTask> findByCropScheduleIdAndFertilizerIdAndStageOfGrowthId(Long cropScheduleId, Long fertilizerId, Long stageOfGrowthId);
//
//    List<CropFertilizerScheduleTask> findByCropScheduleCropIdAndCropScheduleCropScheduleType(Long cropId, CropScheduleType cropScheduleType);
//
//    Optional<CropFertilizerScheduleTask> findByCropScheduleCropIdAndCropScheduleCropScheduleTypeAndStageOfGrowthId(Long cropId, CropScheduleType cropScheduleType, Long stageOfGrowthId);

}
