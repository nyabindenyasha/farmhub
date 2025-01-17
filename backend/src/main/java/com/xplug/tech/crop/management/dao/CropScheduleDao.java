package com.xplug.tech.crop.management.dao;

import com.xplug.tech.crop.management.domain.CropSchedule;
import com.xplug.tech.crop.management.enums.CropScheduleType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CropScheduleDao extends JpaRepository<CropSchedule, Long> {

    Optional<CropSchedule> findByCropIdAndCropScheduleType(Long cropId, CropScheduleType cropScheduleType);

}
