package com.xplug.tech.cropprogram;

import com.xplug.tech.crop.CropProgram;
import com.xplug.tech.cropfertilizerschedule.CropFertilizerScheduleRequest;
import com.xplug.tech.croppesticideschedule.CropPesticideScheduleRequest;
import com.xplug.tech.enums.CropScheduleType;

import java.util.List;

public sealed interface CropProgramService permits CropProgramServiceImpl {

    List<CropProgram> getAll();

    List<CropProgram> getByCrop(Long cropId);

    CropProgram getById(Long id);

    CropProgram getByCropIdAndCropScheduleType(Long cropId, CropScheduleType cropScheduleType);

    CropProgram create(CropProgramRequest cropProgramRequest);

    CropProgram save(CropProgram cropProgram);

    CropProgram update(CropProgramUpdateRequest cropScheduleUpdateRequest);

    void delete(Long id);

}
