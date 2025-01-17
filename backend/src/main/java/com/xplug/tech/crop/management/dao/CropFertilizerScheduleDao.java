package com.xplug.tech.crop.management.dao;

import com.xplug.tech.crop.management.domain.CropFertilizerSchedule;
import com.xplug.tech.crop.management.domain.CropPesticideSchedule;
import com.xplug.tech.crop.management.enums.CropScheduleType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CropFertilizerScheduleDao extends JpaRepository<CropFertilizerSchedule, Long> {

    Optional<CropFertilizerSchedule> findByCropScheduleIdAndFertilizerIdAndStageOfGrowthId(Long cropScheduleId, Long fertilizerId, Long stageOfGrowthId);

    List<CropFertilizerSchedule> findByCropScheduleCropIdAndCropScheduleCropScheduleType(Long cropId, CropScheduleType cropScheduleType);

    Optional<CropFertilizerSchedule> findByCropScheduleCropIdAndCropScheduleCropScheduleTypeAndStageOfGrowthId(Long cropId, CropScheduleType cropScheduleType, Long stageOfGrowthId);

}
