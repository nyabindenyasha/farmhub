package com.xplug.tech.cropfertilizerschedule;

import com.xplug.tech.crop.CropFertilizerSchedule;
import com.xplug.tech.cropprogram.CropProgramService;
import com.xplug.tech.fertilizer.FertilizerService;
import com.xplug.tech.period.PeriodService;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public non-sealed class CropFertilizerScheduleMapperImpl implements CropFertilizerScheduleMapper {

    private final CropProgramService cropProgramService;

    private final FertilizerService fertilizerService;

    private final PeriodService periodService;

    public CropFertilizerScheduleMapperImpl(CropProgramService cropProgramService, FertilizerService fertilizerService, PeriodService periodService) {
        this.cropProgramService = cropProgramService;
        this.fertilizerService = fertilizerService;
        this.periodService = periodService;
    }

    @Override
    public CropFertilizerSchedule cropFertilizerScheduleFromCropFertilizerScheduleRequest(CropFertilizerScheduleRequest cropFertilizerScheduleRequest) {
        Objects.requireNonNull(cropFertilizerScheduleRequest, "CropFertilizerScheduleRequest cannot be null!");
        var cropProgram = cropProgramService.getById(cropFertilizerScheduleRequest.getCropScheduleId());
        var fertilizer = fertilizerService.getById(cropFertilizerScheduleRequest.getFertilizerId());
        return CropFertilizerSchedule.builder()
                .cropProgram(cropProgram)
                .fertilizer(fertilizer)
                .stageOfGrowth(periodService.findOrCreatePeriod(cropFertilizerScheduleRequest.getStageOfGrowth()))
                .applicationInterval(periodService.findOrCreatePeriod(cropFertilizerScheduleRequest.getApplicationInterval()))
                .rate(cropFertilizerScheduleRequest.getRate())
                .applicationMethod(cropFertilizerScheduleRequest.getApplicationMethod())
                .remarks(cropFertilizerScheduleRequest.getRemarks())
                .build();
    }

    @Override
    public CropFertilizerSchedule cropFertilizerScheduleFromCropFertilizerScheduleUpdateRequest(CropFertilizerSchedule cropFertilizerSchedule,
                                                                                                CropFertilizerScheduleUpdateRequest cropFertilizerScheduleUpdateRequest) {
        Objects.requireNonNull(cropFertilizerSchedule, "CropFertilizerSchedule cannot be null!");
        Objects.requireNonNull(cropFertilizerScheduleUpdateRequest, "CropFertilizerScheduleUpdateRequest cannot be null!");
        cropFertilizerSchedule.setStageOfGrowth(periodService.findOrCreatePeriod(cropFertilizerScheduleUpdateRequest.getStageOfGrowth()));
        cropFertilizerSchedule.setApplicationInterval(periodService.findOrCreatePeriod(cropFertilizerScheduleUpdateRequest.getStageOfGrowth()));
        cropFertilizerSchedule.setRate(cropFertilizerScheduleUpdateRequest.getRate());
        cropFertilizerSchedule.setApplicationMethod(cropFertilizerScheduleUpdateRequest.getApplicationMethod());
        cropFertilizerSchedule.setRemarks(cropFertilizerScheduleUpdateRequest.getRemarks());
        return cropFertilizerSchedule;
    }

    @Override
    public CropFertilizerScheduleResponse cropFertilizerScheduleResponseFromCropFertilizerSchedule(CropFertilizerSchedule crop) {
        return CropFertilizerScheduleResponse.of(crop);
    }

}
