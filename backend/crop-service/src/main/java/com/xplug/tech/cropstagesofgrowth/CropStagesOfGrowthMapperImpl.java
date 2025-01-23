package com.xplug.tech.cropstagesofgrowth;

import com.xplug.tech.crop.CropService;
import com.xplug.tech.crop.CropStagesOfGrowth;
import com.xplug.tech.period.PeriodService;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public non-sealed class CropStagesOfGrowthMapperImpl implements CropStagesOfGrowthMapper {

    private final CropService cropService;

    private final PeriodService periodService;

    public CropStagesOfGrowthMapperImpl(CropService cropService, PeriodService periodService) {
        this.cropService = cropService;
        this.periodService = periodService;
    }

    @Override
    public CropStagesOfGrowth cropStagesOfGrowthFromCropStagesOfGrowthRequest(CropStagesOfGrowthRequest cropStagesOfGrowthRequest) {
        Objects.requireNonNull(cropStagesOfGrowthRequest, "CropStagesOfGrowthRequest cannot be null!");
        var crop = cropService.getById(cropStagesOfGrowthRequest.getCropId());
        return CropStagesOfGrowth.builder()
                .crop(crop)
                .stageStartDate(periodService.findOrCreatePeriod(cropStagesOfGrowthRequest.getStageStartDate()))
                .stageEndDate(periodService.findOrCreatePeriod(cropStagesOfGrowthRequest.getStageEndDate()))
                .stageOfGrowth(cropStagesOfGrowthRequest.getStageOfGrowth())
                .build();
    }

    @Override
    public CropStagesOfGrowth cropStagesOfGrowthFromCropStagesOfGrowthUpdateRequest(CropStagesOfGrowth cropStagesOfGrowth, CropStagesOfGrowthUpdateRequest cropStagesOfGrowthUpdateRequest) {
        Objects.requireNonNull(cropStagesOfGrowth, "CropStagesOfGrowth cannot be null!");
        Objects.requireNonNull(cropStagesOfGrowthUpdateRequest, "CropStagesOfGrowthUpdateRequest cannot be null!");
        cropStagesOfGrowth.setStageStartDate(periodService.findOrCreatePeriod(cropStagesOfGrowthUpdateRequest.getStageStartDate()));
        cropStagesOfGrowth.setStageEndDate(periodService.findOrCreatePeriod(cropStagesOfGrowthUpdateRequest.getStageEndDate()));
        cropStagesOfGrowth.setStageOfGrowth(cropStagesOfGrowthUpdateRequest.getStageOfGrowth());
        return cropStagesOfGrowth;
    }

    @Override
    public CropStagesOfGrowthResponse cropStagesOfGrowthResponseFromCropStagesOfGrowth(CropStagesOfGrowth cropStagesOfGrowth) {
        return CropStagesOfGrowthResponse.of(cropStagesOfGrowth);
    }

}
