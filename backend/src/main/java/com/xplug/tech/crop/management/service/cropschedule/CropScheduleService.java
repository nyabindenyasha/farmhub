package com.xplug.tech.crop.management.service.cropschedule;

import com.xplug.tech.crop.management.domain.CropSchedule;
import com.xplug.tech.crop.management.enums.CropScheduleType;

import java.util.List;

public sealed interface CropScheduleService permits CropScheduleServiceImpl {

    List<CropSchedule> getAll();

    CropSchedule getById(Long id);

    CropSchedule getByCropIdAndCropScheduleType(Long cropId, CropScheduleType cropScheduleType);

    CropSchedule create(CropScheduleRequest cropScheduleRequest);

    CropSchedule save(CropSchedule cropSchedule);

    CropSchedule update(CropScheduleUpdateRequest cropScheduleUpdateRequest);

    void delete(Long id);

}
