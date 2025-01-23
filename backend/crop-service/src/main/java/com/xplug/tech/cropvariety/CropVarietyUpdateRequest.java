package com.xplug.tech.cropvariety;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class CropVarietyUpdateRequest extends CropVarietyRequest {

    @NotNull(message = "CropVariety id cannot be null!")
    private Long id;

}
