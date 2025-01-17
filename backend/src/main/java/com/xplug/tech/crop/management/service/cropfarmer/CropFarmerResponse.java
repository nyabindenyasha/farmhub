package com.xplug.tech.crop.management.service.cropfarmer;

import com.xplug.tech.crop.management.domain.CropFarmer;
import com.xplug.tech.crop.management.service.crop.CropResponse;
import com.xplug.tech.crop.management.service.usermanager.UserAccountResponse;
import lombok.Builder;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Objects;

@Setter
@ToString
@Builder
public class CropFarmerResponse {

    private Long id;
    private CropResponse crop;
    private UserAccountResponse farmer; //farmer
    private LocalDate dateOfTransplant; //to derive crop stages and day of maturity
    private String location;
    private String remarks;

    public static CropFarmerResponse of(CropFarmer cropFarmer) {
        Objects.requireNonNull(cropFarmer, "CropFarmer cannot be null!");
        return CropFarmerResponse.builder()
                .id(cropFarmer.getId())
                .crop(CropResponse.of(cropFarmer.getCrop()))
                .farmer(UserAccountResponse.of(cropFarmer.getUserAccount()))
                .dateOfTransplant(cropFarmer.getDateOfTransplant())
                .location(cropFarmer.getLocation())
                .remarks(cropFarmer.getRemarks())
                .build();
    }

}
