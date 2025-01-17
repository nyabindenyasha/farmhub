package com.xplug.tech.crop.management.service.cropstagesofgrowth;

import com.xplug.tech.crop.management.enums.StageOfGrowth;
import com.xplug.tech.crop.management.service.period.PeriodRequest;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class CropStagesOfGrowthRequest {

    @NotNull(message = "Crop Id name cannot be null!")
    private Long cropId;

    private PeriodRequest stageStartDate;

    private PeriodRequest stageEndDate;

    private StageOfGrowth stageOfGrowth;

}
