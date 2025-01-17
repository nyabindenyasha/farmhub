package com.xplug.tech.crop.management.service.croppesticideschedule;

import com.xplug.tech.crop.management.domain.CropPesticideSchedule;
import com.xplug.tech.crop.management.service.cropschedule.CropScheduleService;
import com.xplug.tech.crop.management.service.period.PeriodService;
import com.xplug.tech.crop.management.service.pesticide.PesticideService;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public non-sealed class CropPesticideScheduleMapperImpl implements CropPesticideScheduleMapper {

    private final CropScheduleService cropScheduleService;

    private final PesticideService pesticideService;

    private final PeriodService periodService;

    public CropPesticideScheduleMapperImpl(CropScheduleService cropScheduleService, PesticideService pesticideService, PeriodService periodService) {
        this.cropScheduleService = cropScheduleService;
        this.pesticideService = pesticideService;
        this.periodService = periodService;
    }

    @Override
    public CropPesticideSchedule cropPesticideScheduleFromCropPesticideScheduleRequest(CropPesticideScheduleRequest cropPesticideScheduleRequest) {
        Objects.requireNonNull(cropPesticideScheduleRequest, "CropPesticideScheduleRequest cannot be null!");
        var cropSchedule = cropScheduleService.getById(cropPesticideScheduleRequest.getCropScheduleId());
        var fertilizer = pesticideService.getById(cropPesticideScheduleRequest.getPesticideId());
        return CropPesticideSchedule.builder()
                .cropSchedule(cropSchedule)
                .pesticide(fertilizer)
                .stageOfGrowth(periodService.findOrCreatePeriod(cropPesticideScheduleRequest.getStageOfGrowth()))
                .applicationInterval(periodService.findOrCreatePeriod(cropPesticideScheduleRequest.getApplicationInterval()))
                .applicationMethod(cropPesticideScheduleRequest.getApplicationMethod())
                .remarks(cropPesticideScheduleRequest.getRemarks())
                .build();
    }

    @Override
    public CropPesticideSchedule cropPesticideScheduleFromCropPesticideScheduleUpdateRequest(CropPesticideSchedule cropPesticideSchedule,
                                                                                             CropPesticideScheduleUpdateRequest cropPesticideScheduleUpdateRequest) {
        Objects.requireNonNull(cropPesticideSchedule, "CropPesticideSchedule cannot be null!");
        Objects.requireNonNull(cropPesticideScheduleUpdateRequest, "CropPesticideScheduleUpdateRequest cannot be null!");
        cropPesticideSchedule.setStageOfGrowth(periodService.findOrCreatePeriod(cropPesticideScheduleUpdateRequest.getStageOfGrowth()));
        cropPesticideSchedule.setApplicationInterval(periodService.findOrCreatePeriod(cropPesticideScheduleUpdateRequest.getStageOfGrowth()));
        cropPesticideSchedule.setApplicationMethod(cropPesticideScheduleUpdateRequest.getApplicationMethod());
        cropPesticideSchedule.setRemarks(cropPesticideScheduleUpdateRequest.getRemarks());
        return cropPesticideSchedule;
    }

    @Override
    public CropPesticideScheduleResponse cropPesticideScheduleResponseFromCropPesticideSchedule(CropPesticideSchedule crop) {
        return CropPesticideScheduleResponse.of(crop);
    }

}
