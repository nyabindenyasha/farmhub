package com.xplug.tech.croppesticideschedule;

import com.xplug.tech.crop.CropPesticideSchedule;

public sealed interface CropPesticideScheduleMapper permits CropPesticideScheduleMapperImpl {

    CropPesticideSchedule cropPesticideScheduleFromCropPesticideScheduleRequest(CropPesticideScheduleRequest cropRequest);

    CropPesticideSchedule cropPesticideScheduleFromCropPesticideScheduleUpdateRequest(CropPesticideSchedule crop, CropPesticideScheduleUpdateRequest cropUpdateRequest);

    CropPesticideScheduleResponse cropPesticideScheduleResponseFromCropPesticideSchedule(CropPesticideSchedule crop);

}
