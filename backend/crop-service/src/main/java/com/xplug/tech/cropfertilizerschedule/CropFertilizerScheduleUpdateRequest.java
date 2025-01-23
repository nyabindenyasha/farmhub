package com.xplug.tech.cropfertilizerschedule;

import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Getter
@ToString
public class CropFertilizerScheduleUpdateRequest extends CropFertilizerScheduleRequest {

    @NotNull(message = "CropFertilizerSchedule id cannot be null!")
    private Long id;

}
