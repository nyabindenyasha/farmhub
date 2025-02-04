package com.xplug.tech.cropprogram;

import com.xplug.tech.crop.CropFertilizerSchedule;
import com.xplug.tech.crop.CropPesticideSchedule;
import com.xplug.tech.crop.CropProgram;

import java.util.List;
import java.util.Set;

public sealed interface CropProgramMapper permits CropProgramMapperImpl {

    CropProgram cropScheduleFromCropScheduleRequest(CropProgramRequest cropRequest);

    CropProgram cropScheduleFromCropScheduleRequest(CropProgramRequestV2 cropRequest, Set<CropFertilizerSchedule> cropFertilizerSchedules, Set<CropPesticideSchedule> cropPesticideSchedules);

    CropProgram cropScheduleFromCropScheduleUpdateRequest(CropProgram crop, CropProgramUpdateRequest cropUpdateRequest);

    CropProgramResponse cropScheduleResponseFromCropSchedule(CropProgram crop);

}
