package com.xplug.tech.croppesticideschedule;

import com.xplug.tech.crop.*;
import com.xplug.tech.enums.CropScheduleType;

import java.util.List;
import java.util.Set;

public sealed interface CropPesticideScheduleService permits CropPesticideScheduleServiceImpl {

    List<CropPesticideSchedule> getAll();

    CropPesticideSchedule getById(Long id);

    Set<CropPesticideSchedule> getByCropScheduleId(Long cropScheduleId);

    List<CropPesticideSchedule> getByCropAndScheduleType(Long cropId, CropScheduleType cropScheduleType);

    CropPesticideSchedule getByCropAndScheduleTypeAndStageOfGrowth(Long cropId, CropScheduleType cropScheduleType, Long stageOfGrowthId);

    CropPesticideSchedule initialize(CropProgram cropProgram, Pesticide pesticide, CropPesticideScheduleRequest cropPesticideScheduleRequest);

    CropPesticideSchedule create(CropPesticideScheduleRequest cropPesticideScheduleRequest);

    CropPesticideSchedule update(CropPesticideScheduleUpdateRequest cropPesticideScheduleUpdateRequest);

    void delete(Long id);

    Set<CropPesticideSchedule> findByCropProgramId(Long cropProgramId);

}
