package com.xplug.tech.crop.management.service.cropschedule;

import com.xplug.tech.crop.management.domain.CropSchedule;

public sealed interface CropScheduleMapper permits CropScheduleMapperImpl {

    CropSchedule cropScheduleFromCropScheduleRequest(CropScheduleRequest cropRequest);

    CropSchedule cropScheduleFromCropScheduleUpdateRequest(CropSchedule crop, CropScheduleUpdateRequest cropUpdateRequest);

    CropScheduleResponse cropScheduleResponseFromCropSchedule(CropSchedule crop);

}
