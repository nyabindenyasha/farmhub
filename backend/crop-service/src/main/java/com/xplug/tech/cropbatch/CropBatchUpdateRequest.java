package com.xplug.tech.cropbatch;

import com.xplug.tech.crop.CropRequest;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class CropBatchUpdateRequest extends CropRequest {

    @NotNull(message = "Crop id cannot be null!")
    private Long id;

}
