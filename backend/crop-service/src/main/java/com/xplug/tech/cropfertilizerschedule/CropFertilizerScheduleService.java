package com.xplug.tech.cropfertilizerschedule;

import com.xplug.tech.crop.CropFertilizerSchedule;
import com.xplug.tech.enums.CropScheduleType;

import java.util.List;

public sealed interface CropFertilizerScheduleService permits CropFertilizerScheduleServiceImpl {

    List<CropFertilizerSchedule> getAll();

    CropFertilizerSchedule getById(Long id);

   List<CropFertilizerSchedule> getByCropAndScheduleType(Long cropId, CropScheduleType cropScheduleType);

    CropFertilizerSchedule getByCropAndScheduleTypeAndStageOfGrowth(Long cropId, CropScheduleType cropScheduleType, Long stageOfGrowthId);

    CropFertilizerSchedule create(CropFertilizerScheduleRequest cropFertilizerScheduleRequest);

    CropFertilizerSchedule update(CropFertilizerScheduleUpdateRequest cropFertilizerScheduleUpdateRequest);

    void delete(Long id);

}
