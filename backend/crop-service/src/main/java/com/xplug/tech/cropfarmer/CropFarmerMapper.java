package com.xplug.tech.cropfarmer;

import com.xplug.tech.crop.CropFarmer;

public sealed interface CropFarmerMapper permits CropFarmerMapperImpl {

    CropFarmer cropFarmerFromCropFarmerRequest(CropFarmerRequest cropFarmerRequest);

    CropFarmer cropFarmerFromCropFarmerUpdateRequest(CropFarmer cropFarmer, CropFarmerUpdateRequest cropFarmerUpdateRequest);

    CropFarmerResponse cropFarmerResponseFromCropFarmer(CropFarmer cropFarmer);

}
