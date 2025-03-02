package com.xplug.tech.croppesticideschedule;

import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Getter
@ToString
public class CropPesticideScheduleUpdateRequest extends CropPesticideScheduleRequest {

    @NotNull(message = "CropPesticideSchedule id cannot be null!")
    private Long id;

}
