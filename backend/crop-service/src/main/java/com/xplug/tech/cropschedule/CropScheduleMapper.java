package com.xplug.tech.cropschedule;

import com.xplug.tech.crop.CropSchedule;

public sealed interface CropScheduleMapper permits CropScheduleMapperImpl {

    CropSchedule cropScheduleFromCropScheduleRequest(CropScheduleRequest cropRequest);

    CropSchedule cropScheduleFromCropScheduleUpdateRequest(CropSchedule crop, CropScheduleUpdateRequest cropUpdateRequest);

    CropScheduleResponse cropScheduleResponseFromCropSchedule(CropSchedule crop);

}
