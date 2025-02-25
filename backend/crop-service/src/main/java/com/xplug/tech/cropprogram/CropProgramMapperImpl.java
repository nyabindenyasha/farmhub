package com.xplug.tech.cropprogram;

import com.xplug.tech.crop.CropFertilizerSchedule;
import com.xplug.tech.crop.CropPesticideSchedule;
import com.xplug.tech.crop.CropProgram;
import com.xplug.tech.crop.CropService;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Set;

@Component
public non-sealed class CropProgramMapperImpl implements CropProgramMapper {

    private final CropService cropService;

    public CropProgramMapperImpl(CropService cropService) {
        this.cropService = cropService;
    }

    @Override
    public CropProgram cropScheduleFromCropScheduleRequest(CropProgramRequest cropProgramRequest) {
        Objects.requireNonNull(cropProgramRequest, "CropScheduleRequest cannot be null!");
        var crop = cropService.getById(cropProgramRequest.getCropId());
        return CropProgram.builder()
                .crop(crop)
                .name(cropProgramRequest.getName())
                .description(cropProgramRequest.getDescription())
                .source(cropProgramRequest.getSource())
                .remarks(cropProgramRequest.getRemarks())
                .cropScheduleType(cropProgramRequest.getCropScheduleType())
                .build();
    }

    @Override
    public CropProgram cropScheduleFromCropScheduleRequest(CropProgramRequestV2 cropProgramRequest, Set<CropFertilizerSchedule> cropFertilizerSchedules, Set<CropPesticideSchedule> cropPesticideSchedules) {
        Objects.requireNonNull(cropProgramRequest, "CropScheduleRequest cannot be null!");
        var crop = cropService.getById(cropProgramRequest.getCropId());
        return CropProgram.builder()
                .crop(crop)
                .name(cropProgramRequest.getName())
                .description(cropProgramRequest.getDescription())
                .source(cropProgramRequest.getSource())
                .remarks(cropProgramRequest.getRemarks())
                .cropScheduleType(cropProgramRequest.getCropScheduleType())
                .fertilizerScheduleList(cropFertilizerSchedules)
                .pesticideScheduleList(cropPesticideSchedules)
                .build();
    }

    @Override
    public CropProgram cropScheduleFromCropScheduleRequestV2(CropProgramRequestV2 cropProgramRequest) {
        Objects.requireNonNull(cropProgramRequest, "CropScheduleRequest cannot be null!");
        var crop = cropService.getById(cropProgramRequest.getCropId());
        return CropProgram.builder()
                .crop(crop)
                .name(cropProgramRequest.getName())
                .description(cropProgramRequest.getDescription())
                .source(cropProgramRequest.getSource())
                .remarks(cropProgramRequest.getRemarks())
                .cropScheduleType(cropProgramRequest.getCropScheduleType())
                .build();
    }

    @Override
    public CropProgram cropScheduleFromCropScheduleUpdateRequest(CropProgram cropProgram,
                                                                 CropProgramUpdateRequest cropScheduleUpdateRequest) {
        Objects.requireNonNull(cropProgram, "CropSchedule cannot be null!");
        Objects.requireNonNull(cropScheduleUpdateRequest, "CropScheduleUpdateRequest cannot be null!");
        cropProgram.setDescription(cropScheduleUpdateRequest.getDescription());
        cropProgram.setSource(cropScheduleUpdateRequest.getSource());
        cropProgram.setRemarks(cropScheduleUpdateRequest.getRemarks());
        cropProgram.setCropScheduleType(cropScheduleUpdateRequest.getCropScheduleType());
        return cropProgram;
    }

    @Override
    public CropProgramResponse cropScheduleResponseFromCropSchedule(CropProgram cropProgram) {
        return CropProgramResponse.of(cropProgram);
    }

}
