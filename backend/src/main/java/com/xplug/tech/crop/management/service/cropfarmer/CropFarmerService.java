package com.xplug.tech.crop.management.service.cropfarmer;

import com.xplug.tech.crop.management.domain.CropFarmer;

import java.util.List;

public sealed interface CropFarmerService permits CropFarmerServiceImpl {

    List<CropFarmer> getAll();

    CropFarmer getById(Long id);

    CropFarmer create(CropFarmerRequest cropFarmerRequest);

    CropFarmer update(CropFarmerUpdateRequest cropFarmerUpdateRequest);

    void delete(Long id);

}
