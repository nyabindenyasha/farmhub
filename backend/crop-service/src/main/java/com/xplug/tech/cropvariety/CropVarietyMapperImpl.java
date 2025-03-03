package com.xplug.tech.cropvariety;

import com.xplug.tech.crop.CropService;
import com.xplug.tech.crop.CropVariety;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public non-sealed class CropVarietyMapperImpl implements CropVarietyMapper {

    private final CropService cropService;

    public CropVarietyMapperImpl(CropService cropService) {
        this.cropService = cropService;
    }

    @Override
    public CropVariety cropVarietyFromCropVarietyRequest(CropVarietyRequest cropVarietyRequest) {
        Objects.requireNonNull(cropVarietyRequest, "CropVarietyRequest cannot be null!");
        var crop = cropService.getById(cropVarietyRequest.getCropId());
        return CropVariety.builder()
                .crop(crop)
                .variety(cropVarietyRequest.getVariety())
                .maturityStartDay(cropVarietyRequest.getMaturityStartDay())
                .maturityEndDay(cropVarietyRequest.getMaturityEndDay())
                .harvestDuration(cropVarietyRequest.getHarvestDuration())
                .remarks(cropVarietyRequest.getRemarks())
                .varietyType(cropVarietyRequest.getVarietyType())
                .build();
    }

    @Override
    public CropVariety cropVarietyFromCropVarietyUpdateRequest(CropVariety cropVariety, CropVarietyUpdateRequest cropVarietyUpdateRequest) {
        Objects.requireNonNull(cropVariety, "CropVariety cannot be null!");
        Objects.requireNonNull(cropVarietyUpdateRequest, "CropVarietyUpdateRequest cannot be null!");
        cropVariety.setVariety(cropVarietyUpdateRequest.getVariety());
        cropVariety.setMaturityStartDay(cropVarietyUpdateRequest.getMaturityStartDay());
        cropVariety.setMaturityEndDay(cropVarietyUpdateRequest.getMaturityEndDay());
        cropVariety.setHarvestDuration(cropVarietyUpdateRequest.getHarvestDuration());
        cropVariety.setRemarks(cropVarietyUpdateRequest.getRemarks());
        return cropVariety;
    }

    @Override
    public CropVarietyResponse cropVarietyResponseFromCropVariety(CropVariety cropVariety) {
        return CropVarietyResponse.of(cropVariety);
    }

}
