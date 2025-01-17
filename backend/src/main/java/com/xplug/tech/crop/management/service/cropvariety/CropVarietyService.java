package com.xplug.tech.crop.management.service.cropvariety;

import com.xplug.tech.crop.management.domain.CropVariety;

import java.util.List;

public sealed interface CropVarietyService permits CropVarietyServiceImpl {

    List<CropVariety> getAll();

    CropVariety getById(Long id);

    CropVariety create(CropVarietyRequest cropVarietyRequest);

    CropVariety update(CropVarietyUpdateRequest cropVarietyUpdateRequest);

    void delete(Long id);

}
