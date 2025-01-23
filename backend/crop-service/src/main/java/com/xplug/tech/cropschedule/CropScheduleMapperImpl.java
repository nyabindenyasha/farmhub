package com.xplug.tech.cropschedule;

import com.xplug.tech.crop.CropSchedule;
import com.xplug.tech.crop.CropService;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public non-sealed class CropScheduleMapperImpl implements CropScheduleMapper {

    private final CropService cropService;

    public CropScheduleMapperImpl(CropService cropService) {
        this.cropService = cropService;
    }

    @Override
    public CropSchedule cropScheduleFromCropScheduleRequest(CropScheduleRequest cropScheduleRequest) {
        Objects.requireNonNull(cropScheduleRequest, "CropScheduleRequest cannot be null!");
        var crop = cropService.getById(cropScheduleRequest.getCropId());
        return CropSchedule.builder()
                .crop(crop)
                .name(cropScheduleRequest.getName())
                .description(cropScheduleRequest.getDescription())
                .source(cropScheduleRequest.getSource())
                .remarks(cropScheduleRequest.getRemarks())
                .cropScheduleType(cropScheduleRequest.getCropScheduleType())
                .build();
    }

    @Override
    public CropSchedule cropScheduleFromCropScheduleUpdateRequest(CropSchedule cropSchedule,
                                                                  CropScheduleUpdateRequest cropScheduleUpdateRequest) {
        Objects.requireNonNull(cropSchedule, "CropSchedule cannot be null!");
        Objects.requireNonNull(cropScheduleUpdateRequest, "CropScheduleUpdateRequest cannot be null!");
        cropSchedule.setDescription(cropScheduleUpdateRequest.getDescription());
        cropSchedule.setSource(cropScheduleUpdateRequest.getSource());
        cropSchedule.setRemarks(cropScheduleUpdateRequest.getRemarks());
        cropSchedule.setCropScheduleType(cropScheduleUpdateRequest.getCropScheduleType());
        return cropSchedule;
    }

    @Override
    public CropScheduleResponse cropScheduleResponseFromCropSchedule(CropSchedule cropSchedule) {
        return CropScheduleResponse.of(cropSchedule);
    }

}
