package com.xplug.tech.cropfertilizerschedule;

import com.xplug.tech.crop.CropFertilizerSchedule;

public sealed interface CropFertilizerScheduleMapper permits CropFertilizerScheduleMapperImpl {

    CropFertilizerSchedule cropFertilizerScheduleFromCropFertilizerScheduleRequest(CropFertilizerScheduleRequest cropRequest);

    CropFertilizerSchedule cropFertilizerScheduleFromCropFertilizerScheduleUpdateRequest(CropFertilizerSchedule crop, CropFertilizerScheduleUpdateRequest cropUpdateRequest);

    CropFertilizerScheduleResponse cropFertilizerScheduleResponseFromCropFertilizerSchedule(CropFertilizerSchedule crop);

}
