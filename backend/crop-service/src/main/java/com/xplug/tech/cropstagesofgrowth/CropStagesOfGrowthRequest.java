package com.xplug.tech.cropstagesofgrowth;

import com.xplug.tech.enums.StageOfGrowth;
import com.xplug.tech.period.PeriodRequest;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CropStagesOfGrowthRequest {

    private PeriodRequest stageStartDate;

    private PeriodRequest stageEndDate;

    private StageOfGrowth stageOfGrowth;

}
