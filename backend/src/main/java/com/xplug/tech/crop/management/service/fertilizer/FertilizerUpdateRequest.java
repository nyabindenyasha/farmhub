package com.xplug.tech.crop.management.service.fertilizer;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class FertilizerUpdateRequest extends FertilizerRequest {

    @NotNull(message = "Fertilizer id cannot be null!")
    private Long id;

}
