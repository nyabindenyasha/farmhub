package com.xplug.tech.crop;

import com.xplug.tech.enums.CropScheduleType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CropScheduleDao extends JpaRepository<CropSchedule, Long> {

    Optional<CropSchedule> findByCropIdAndCropScheduleType(Long cropId, CropScheduleType cropScheduleType);

}
