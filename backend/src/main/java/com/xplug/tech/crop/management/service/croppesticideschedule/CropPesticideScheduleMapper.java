package com.xplug.tech.crop.management.service.croppesticideschedule;

import com.xplug.tech.crop.management.domain.CropPesticideSchedule;

public sealed interface CropPesticideScheduleMapper permits CropPesticideScheduleMapperImpl {

    CropPesticideSchedule cropPesticideScheduleFromCropPesticideScheduleRequest(CropPesticideScheduleRequest cropRequest);

    CropPesticideSchedule cropPesticideScheduleFromCropPesticideScheduleUpdateRequest(CropPesticideSchedule crop, CropPesticideScheduleUpdateRequest cropUpdateRequest);

    CropPesticideScheduleResponse cropPesticideScheduleResponseFromCropPesticideSchedule(CropPesticideSchedule crop);

}
