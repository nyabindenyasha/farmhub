package com.xplug.tech.crop.management.service.croppesticideschedule;

import com.xplug.tech.crop.management.enums.PesticideApplicationMethod;
import com.xplug.tech.crop.management.service.period.PeriodRequest;
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
