package com.xplug.tech.cropvariety;

import com.xplug.tech.enums.VarietyType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Getter
@Setter
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

    private VarietyType varietyType;

}
