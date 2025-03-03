package com.xplug.tech.cropprogram;

import com.xplug.tech.crop.CropFertilizerSchedule;
import com.xplug.tech.crop.CropPesticideSchedule;
import com.xplug.tech.crop.CropProgram;
import com.xplug.tech.crop.CropService;
import com.xplug.tech.cropfertilizerschedule.CropFertilizerScheduleMapper;
import com.xplug.tech.cropfertilizerschedule.CropFertilizerScheduleRequest;
import com.xplug.tech.croppesticideschedule.CropPesticideScheduleMapper;
import com.xplug.tech.croppesticideschedule.CropPesticideScheduleRequest;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Component
public non-sealed class CropProgramMapperImpl implements CropProgramMapper {

    private final CropService cropService;

    private final CropFertilizerScheduleMapper cropFertilizerScheduleMapper;

    private final CropPesticideScheduleMapper cropPesticideScheduleMapper;

    public CropProgramMapperImpl(CropService cropService, CropFertilizerScheduleMapper cropFertilizerScheduleMapper, CropPesticideScheduleMapper cropPesticideScheduleMapper) {
        this.cropService = cropService;
        this.cropFertilizerScheduleMapper = cropFertilizerScheduleMapper;
        this.cropPesticideScheduleMapper = cropPesticideScheduleMapper;
    }

    @Override
    public CropProgram cropScheduleFromCropScheduleRequest(CropProgramRequest cropProgramRequest) {
        Objects.requireNonNull(cropProgramRequest, "CropScheduleRequest cannot be null!");
        var crop = cropService.getById(cropProgramRequest.getCropId());

        Set<CropFertilizerSchedule> cropFertilizerSchedules = getCropFertilizerSchedules(cropProgramRequest.getFertilizerScheduleRequests());

        Set<CropPesticideSchedule> cropPesticideSchedules = getCropPesticideSchedules(cropProgramRequest.getPesticideScheduleRequests());

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
    public Set<CropPesticideSchedule> getCropPesticideSchedules(List<CropPesticideScheduleRequest> cropPesticideScheduleRequests) {
        Set<CropPesticideSchedule> cropPesticideSchedules = new HashSet<>();
        cropPesticideScheduleRequests.forEach(cropPesticideScheduleRequest -> {
            CropPesticideSchedule cropPesticideSchedule = cropPesticideScheduleMapper.cropPesticideScheduleFromCropPesticideScheduleRequest(cropPesticideScheduleRequest);
            cropPesticideSchedules.add(cropPesticideSchedule);
        });
        return cropPesticideSchedules;
    }

    @Override
    public Set<CropFertilizerSchedule> getCropFertilizerSchedules(List<CropFertilizerScheduleRequest> cropFertilizerScheduleRequests) {
        Set<CropFertilizerSchedule> cropFertilizerSchedules = new HashSet<>();
        cropFertilizerScheduleRequests.forEach(cropFertilizerScheduleRequest -> {
            CropFertilizerSchedule cropFertilizerSchedule = cropFertilizerScheduleMapper.cropFertilizerScheduleFromCropFertilizerScheduleRequest(cropFertilizerScheduleRequest);
            cropFertilizerSchedules.add(cropFertilizerSchedule);
        });
        return cropFertilizerSchedules;
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
