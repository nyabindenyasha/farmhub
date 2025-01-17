package com.xplug.tech.crop.management.service.cropfertilizerschedule;

import com.xplug.tech.crop.management.enums.FertilizerApplicationMethod;
import com.xplug.tech.crop.management.service.period.PeriodRequest;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class CropFertilizerScheduleRequest {

    @NotNull(message = "Crop Id cannot be null!")
    private Long cropScheduleId;

    @NotNull(message = "Fertilizer Id cannot be null!")
    private Long fertilizerId;

    private PeriodRequest stageOfGrowth;

    private PeriodRequest applicationInterval;

    private Integer rate;

    private FertilizerApplicationMethod applicationMethod; //enum

    private String remarks;

}
