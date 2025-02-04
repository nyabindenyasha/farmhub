package com.xplug.tech.cropprogram;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class CropProgramUpdateRequest extends CropProgramRequest {

    @NotNull(message = "CropSchedule id cannot be null!")
    private Long id;

}
