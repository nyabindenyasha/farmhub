package com.xplug.tech.cropfarmer;

import com.xplug.tech.crop.CropFarmer;

import java.util.List;

public sealed interface CropFarmerService permits CropFarmerServiceImpl {

    List<CropFarmer> getAll();

    CropFarmer getById(Long id);

    CropFarmer create(CropFarmerRequest cropFarmerRequest);

    CropFarmer update(CropFarmerUpdateRequest cropFarmerUpdateRequest);

    void delete(Long id);

}
