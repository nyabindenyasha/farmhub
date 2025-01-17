package com.xplug.tech.crop.management.service.crop;

import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Getter
@ToString
public class CropRequest {

    @NotNull(message = "Crop name cannot be null!")
    private String name;

    private String family;
    private String genus;
    private String species;
    private String subSpecies;

}
