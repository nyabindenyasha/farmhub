package com.xplug.tech.cropprogram;

import com.xplug.tech.crop.CropFertilizerSchedule;
import com.xplug.tech.crop.CropPesticideSchedule;
import com.xplug.tech.crop.CropProgram;
import com.xplug.tech.cropfertilizerschedule.CropFertilizerScheduleRequest;
import com.xplug.tech.croppesticideschedule.CropPesticideScheduleRequest;

import java.util.List;
import java.util.Set;

public sealed interface CropProgramMapper permits CropProgramMapperImpl {

    CropProgram cropScheduleFromCropScheduleRequest(CropProgramRequest cropRequest);

    CropProgram cropScheduleFromCropScheduleUpdateRequest(CropProgram crop, CropProgramUpdateRequest cropUpdateRequest);

    CropProgramResponse cropScheduleResponseFromCropSchedule(CropProgram crop);

    Set<CropPesticideSchedule> getCropPesticideSchedules(List<CropPesticideScheduleRequest> cropPesticideScheduleRequests);

    Set<CropFertilizerSchedule> getCropFertilizerSchedules(List<CropFertilizerScheduleRequest> cropFertilizerScheduleRequests);

}
