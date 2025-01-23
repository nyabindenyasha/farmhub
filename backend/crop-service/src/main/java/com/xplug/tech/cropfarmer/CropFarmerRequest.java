package com.xplug.tech.cropfarmer;

import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@ToString
public class CropFarmerRequest {

    @NotNull(message = "Crop id cannot be null!")
    private Long cropId;

    @NotNull(message = "Farmer id cannot be null!")
    private Long farmerId;

    @NotNull(message = "Date Of Transplant cannot be null!")
    private LocalDate dateOfTransplant;

    @NotNull(message = "Location cannot be null!")
    private String location;

    private String remarks;

}
