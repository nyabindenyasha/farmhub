package com.xplug.tech.croppesticideschedule;

import com.xplug.tech.enums.PesticideApplicationMethod;
import com.xplug.tech.period.PeriodRequest;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class CropPesticideScheduleRequest {

    @NotNull(message = "Crop Id cannot be null!")
    private Long cropScheduleId;

    @NotNull(message = "Pesticide Id cannot be null!")
    private Long pesticideId;

    private PeriodRequest stageOfGrowth;

    private PeriodRequest applicationInterval;

    private PesticideApplicationMethod applicationMethod; //enum

    private String remarks;

}
