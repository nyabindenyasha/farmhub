package com.xplug.tech.cropfarmer;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class CropFarmerUpdateRequest extends CropFarmerRequest {

    @NotNull(message = "CropFarmer id cannot be null!")
    private Long id;

}
