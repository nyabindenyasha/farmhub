package com.xplug.tech.cropprogram;

import com.xplug.tech.crop.CropProgram;
import com.xplug.tech.enums.CropScheduleType;

import java.util.List;

public sealed interface CropProgramService permits CropProgramServiceImpl {

    List<CropProgram> getAll();

    CropProgram getById(Long id);

    CropProgram getByCropIdAndCropScheduleType(Long cropId, CropScheduleType cropScheduleType);

    CropProgram create(CropProgramRequest cropProgramRequest);

    CropProgram create(CropProgramRequestV2 cropProgramRequest);

    CropProgram save(CropProgram cropProgram);

    CropProgram update(CropProgramUpdateRequest cropScheduleUpdateRequest);

    void delete(Long id);

}
