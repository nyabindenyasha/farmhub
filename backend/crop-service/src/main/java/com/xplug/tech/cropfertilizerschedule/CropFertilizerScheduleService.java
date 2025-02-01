package com.xplug.tech.cropfertilizerschedule;

import com.xplug.tech.crop.CropFertilizerSchedule;
import com.xplug.tech.crop.CropSchedule;
import com.xplug.tech.crop.Fertilizer;
import com.xplug.tech.enums.CropScheduleType;

import java.util.List;
import java.util.Set;

public sealed interface CropFertilizerScheduleService permits CropFertilizerScheduleServiceImpl {

    List<CropFertilizerSchedule> getAll();

    CropFertilizerSchedule getById(Long id);

    Set<CropFertilizerSchedule> getByCropScheduleId(Long cropScheduleId);

   List<CropFertilizerSchedule> getByCropAndScheduleType(Long cropId, CropScheduleType cropScheduleType);

    CropFertilizerSchedule getByCropAndScheduleTypeAndStageOfGrowth(Long cropId, CropScheduleType cropScheduleType, Long stageOfGrowthId);

    CropFertilizerSchedule create(CropFertilizerScheduleRequest cropFertilizerScheduleRequest);


    CropFertilizerSchedule initialize(CropSchedule cropSchedule, Fertilizer fertilizer, CropFertilizerScheduleRequest cropFertilizerScheduleRequest);

    CropFertilizerSchedule update(CropFertilizerScheduleUpdateRequest cropFertilizerScheduleUpdateRequest);

    void delete(Long id);

}
