package com.xplug.tech.cropfarmer;

import com.xplug.tech.crop.CropFarmer;
import com.xplug.tech.crop.CropResponse;
import com.xplug.tech.cropprogram.CropProgramSummaryResponse;
import com.xplug.tech.usermanager.user.UserAccountResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@ToString
@Builder
public class CropFarmerSummaryResponse {

    private Long id;
    private CropResponse crop;
    private UserAccountResponse farmer; //farmer
    private CropProgramSummaryResponse cropScheduleResponse;
    private LocalDateTime dateOfTransplant; //to derive crop stages and day of maturity
    private String location;
    private String remarks;

    public static CropFarmerSummaryResponse of(CropFarmer cropFarmer) {
        Objects.requireNonNull(cropFarmer, "CropFarmer cannot be null!");
        return CropFarmerSummaryResponse.builder()
                .id(cropFarmer.getId())
                .crop(CropResponse.of(cropFarmer.getCrop()))
                .farmer(UserAccountResponse.of(cropFarmer.getUserAccount()))
                .cropScheduleResponse(CropProgramSummaryResponse.of(cropFarmer.getCropProgram()))
                .dateOfTransplant(cropFarmer.getDateOfTransplant())
                .location(cropFarmer.getLocation())
                .remarks(cropFarmer.getRemarks())
                .build();
    }

}
