package com.xplug.tech.crop.management.service.cropfertilizerschedule;

import com.xplug.tech.crop.management.domain.CropFertilizerSchedule;

public sealed interface CropFertilizerScheduleMapper permits CropFertilizerScheduleMapperImpl {

    CropFertilizerSchedule cropFertilizerScheduleFromCropFertilizerScheduleRequest(CropFertilizerScheduleRequest cropRequest);

    CropFertilizerSchedule cropFertilizerScheduleFromCropFertilizerScheduleUpdateRequest(CropFertilizerSchedule crop, CropFertilizerScheduleUpdateRequest cropUpdateRequest);

    CropFertilizerScheduleResponse cropFertilizerScheduleResponseFromCropFertilizerSchedule(CropFertilizerSchedule crop);

}
