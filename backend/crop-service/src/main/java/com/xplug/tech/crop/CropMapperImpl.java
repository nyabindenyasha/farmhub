package com.xplug.tech.crop;

import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public non-sealed class CropMapperImpl implements CropMapper {

    @Override
    public Crop cropFromCropRequest(CropRequest cropRequest) {
        Objects.requireNonNull(cropRequest, "CropRequest cannot be null!");
        return Crop.builder()
                .name(cropRequest.getName())
                .family(cropRequest.getFamily())
                .genus(cropRequest.getGenus())
                .species(cropRequest.getSpecies())
                .subSpecies(cropRequest.getSubSpecies())
                .build();
    }

    @Override
    public Crop cropFromCropUpdateRequest(Crop crop, CropUpdateRequest cropUpdateRequest) {
        Objects.requireNonNull(crop, "Crop cannot be null!");
        Objects.requireNonNull(cropUpdateRequest, "CropUpdateRequest cannot be null!");
        crop.setName(cropUpdateRequest.getName());
        crop.setFamily(cropUpdateRequest.getFamily());
        crop.setGenus(cropUpdateRequest.getGenus());
        crop.setSpecies(cropUpdateRequest.getSpecies());
        crop.setSubSpecies(cropUpdateRequest.getSubSpecies());
        return crop;
    }

    @Override
    public CropResponse cropResponseFromCrop(Crop crop) {
        return CropResponse.of(crop);
    }

}
