package com.xplug.tech.crop.management.service.cropschedule;

import com.xplug.tech.crop.management.enums.CropScheduleType;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Getter
@ToString
public class CropScheduleRequest {

    @NotNull(message = "Crop Id cannot be null!")
    private Long cropId;

    @NotNull(message = "Crop Schedule name cannot be null!")
    private String name;

    private String description;

    private String source; //agronomist/company

    private String remarks;

    private CropScheduleType cropScheduleType;

}
