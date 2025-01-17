package com.xplug.tech.crop.management.service.crop;

import com.xplug.tech.crop.management.domain.Crop;
import lombok.Builder;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

@Setter
@ToString
@Builder
public class CropResponse {

    private Long id;
    private String name;
    private String family;
    private String genus;
    private String species;
    private String subSpecies;

    public static CropResponse of(Crop crop) {
        Objects.requireNonNull(crop, "Crop cannot be null!");
        return CropResponse.builder()
                .id(crop.getId())
                .name(crop.getName())
                .family(crop.getFamily())
                .genus(crop.getGenus())
                .species(crop.getSpecies())
                .subSpecies(crop.getSubSpecies())
                .build();
    }

}
