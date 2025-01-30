package com.xplug.tech.cropprograms;

import com.xplug.tech.crop.CropFertilizerSchedule;
import com.xplug.tech.crop.CropPesticideSchedule;
import com.xplug.tech.crop.CropProgram;
import com.xplug.tech.crop.CropSchedule;

import java.util.List;

public sealed interface CropProgramService permits CropProgramServiceImpl {

    List<CropProgram> getAll();

    CropProgram getById(Long cropProgramId);

    CropProgram getByCropScheduleId(Long cropScheduleId);

    CropProgram create(CropSchedule cropSchedule);

    CropProgram updateFertilizerSchedule(CropFertilizerSchedule cropFertilizerSchedule);

    CropProgram updatePesticideSchedule(CropPesticideSchedule cropPesticideSchedule);

}
