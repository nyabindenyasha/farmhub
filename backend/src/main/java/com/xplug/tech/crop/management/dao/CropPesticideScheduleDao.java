package com.xplug.tech.crop.management.dao;

import com.xplug.tech.crop.management.domain.CropPesticideSchedule;
import com.xplug.tech.crop.management.enums.CropScheduleType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CropPesticideScheduleDao extends JpaRepository<CropPesticideSchedule, Long> {

    Optional<CropPesticideSchedule> findByCropScheduleIdAndPesticideIdAndStageOfGrowthId(Long cropScheduleId, Long pesticideId, Long stageOfGrowthId);

    List<CropPesticideSchedule> findByCropScheduleCropIdAndCropScheduleCropScheduleType(Long cropId, CropScheduleType cropScheduleType);

    Optional<CropPesticideSchedule> findByCropScheduleCropIdAndCropScheduleCropScheduleTypeAndStageOfGrowthId(Long cropId, CropScheduleType cropScheduleType, Long stageOfGrowthId);

}

