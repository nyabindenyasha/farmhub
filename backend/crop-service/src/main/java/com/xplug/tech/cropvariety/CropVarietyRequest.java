package com.xplug.tech.cropvariety;

import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Getter
@ToString
public class CropVarietyRequest {

    @NotNull(message = "Crop Id cannot be null!")
    private Long cropId;

    @NotNull(message = "CropVariety name cannot be null!")
    private String variety;

    private Integer maturityStartDay;

    private Integer maturityEndDay;

    private Integer harvestDuration;

    private String remarks;

}
