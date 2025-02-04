package com.xplug.tech.crop;

import com.xplug.tech.enums.CropScheduleType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CropProgramDao extends JpaRepository<CropProgram, Long> {

    Optional<CropProgram> findByCropIdAndCropScheduleType(Long cropId, CropScheduleType cropScheduleType);

}
