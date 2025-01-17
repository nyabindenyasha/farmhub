package com.xplug.tech.crop.management.service.cropvariety;

import com.xplug.tech.crop.management.domain.CropVariety;

public sealed interface CropVarietyMapper permits CropVarietyMapperImpl {

    CropVariety cropVarietyFromCropVarietyRequest(CropVarietyRequest cropVarietyRequest);

    CropVariety cropVarietyFromCropVarietyUpdateRequest(CropVariety cropVariety, CropVarietyUpdateRequest cropVarietyUpdateRequest);

    CropVarietyResponse cropVarietyResponseFromCropVariety(CropVariety cropVariety);

}
