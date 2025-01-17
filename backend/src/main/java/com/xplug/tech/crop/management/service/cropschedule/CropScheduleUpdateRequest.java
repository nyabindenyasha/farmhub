package com.xplug.tech.crop.management.service.cropschedule;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class CropScheduleUpdateRequest extends CropScheduleRequest {

    @NotNull(message = "CropSchedule id cannot be null!")
    private Long id;

}
