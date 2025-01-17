package com.xplug.tech.crop.management.service.crop;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class CropUpdateRequest extends CropRequest {

    @NotNull(message = "Crop id cannot be null!")
    private Long id;

}
