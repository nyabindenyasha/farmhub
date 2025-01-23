package com.xplug.tech.cropfarmer;

import com.xplug.tech.crop.CropFarmer;
import com.xplug.tech.crop.CropService;
import com.xplug.tech.usermanager.user.UserAccountService;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public non-sealed class CropFarmerMapperImpl implements CropFarmerMapper {

    private final CropService cropService;

    private final UserAccountService userAccountService;

    public CropFarmerMapperImpl(CropService cropService, UserAccountService userAccountService) {
        this.cropService = cropService;
        this.userAccountService = userAccountService;
    }

    @Override
    public CropFarmer cropFarmerFromCropFarmerRequest(CropFarmerRequest cropFarmerRequest) {
        Objects.requireNonNull(cropFarmerRequest, "CropFarmerRequest cannot be null!");
        var crop = cropService.getById(cropFarmerRequest.getCropId());
        var farmer = userAccountService.findById(cropFarmerRequest.getFarmerId());
        return CropFarmer.builder()
                .crop(crop)
                .userAccount(farmer)
                .dateOfTransplant(cropFarmerRequest.getDateOfTransplant())
                .location(cropFarmerRequest.getLocation())
                .remarks(cropFarmerRequest.getRemarks())
                .build();
    }

    @Override
    public CropFarmer cropFarmerFromCropFarmerUpdateRequest(CropFarmer cropFarmer, CropFarmerUpdateRequest cropFarmerUpdateRequest) {
        Objects.requireNonNull(cropFarmer, "CropFarmer cannot be null!");
        Objects.requireNonNull(cropFarmerUpdateRequest, "CropFarmerUpdateRequest cannot be null!");
        cropFarmer.setDateOfTransplant(cropFarmerUpdateRequest.getDateOfTransplant());
        cropFarmer.setLocation(cropFarmerUpdateRequest.getLocation());
        cropFarmer.setRemarks(cropFarmerUpdateRequest.getRemarks());
        return cropFarmer;
    }

    @Override
    public CropFarmerResponse cropFarmerResponseFromCropFarmer(CropFarmer cropFarmer) {
        return CropFarmerResponse.of(cropFarmer);
    }

}
