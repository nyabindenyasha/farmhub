package com.xplug.tech.crop;

import com.xplug.tech.enums.CropScheduleType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CropPesticideScheduleTaskDao extends JpaRepository<CropPesticideScheduleTask, Long> {

//    Optional<CropPesticideSchedule> findByCropScheduleIdAndPesticideIdAndStageOfGrowthId(Long cropScheduleId, Long pesticideId, Long stageOfGrowthId);
//
//    List<CropPesticideSchedule> findByCropScheduleCropIdAndCropScheduleCropScheduleType(Long cropId, CropScheduleType cropScheduleType);
//
//    Optional<CropPesticideSchedule> findByCropScheduleCropIdAndCropScheduleCropScheduleTypeAndStageOfGrowthId(Long cropId, CropScheduleType cropScheduleType, Long stageOfGrowthId);

}

