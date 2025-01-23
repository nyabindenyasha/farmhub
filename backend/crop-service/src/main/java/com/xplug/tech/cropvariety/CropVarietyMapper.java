package com.xplug.tech.cropvariety;

import com.xplug.tech.crop.CropVariety;

public sealed interface CropVarietyMapper permits CropVarietyMapperImpl {

    CropVariety cropVarietyFromCropVarietyRequest(CropVarietyRequest cropVarietyRequest);

    CropVariety cropVarietyFromCropVarietyUpdateRequest(CropVariety cropVariety, CropVarietyUpdateRequest cropVarietyUpdateRequest);

    CropVarietyResponse cropVarietyResponseFromCropVariety(CropVariety cropVariety);

}
