package com.xplug.tech.crop.management.service.cropfarmer;

import com.xplug.tech.crop.management.domain.CropFarmer;

public sealed interface CropFarmerMapper permits CropFarmerMapperImpl {

    CropFarmer cropFarmerFromCropFarmerRequest(CropFarmerRequest cropFarmerRequest);

    CropFarmer cropFarmerFromCropFarmerUpdateRequest(CropFarmer cropFarmer, CropFarmerUpdateRequest cropFarmerUpdateRequest);

    CropFarmerResponse cropFarmerResponseFromCropFarmer(CropFarmer cropFarmer);

}
