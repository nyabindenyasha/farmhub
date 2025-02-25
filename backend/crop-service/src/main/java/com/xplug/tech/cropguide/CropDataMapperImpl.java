package com.xplug.tech.cropguide;

import com.xplug.tech.crop.Fertilizer;
import com.xplug.tech.crop.data.FertilizerApplicationRate;
import com.xplug.tech.fertilizer.FertilizerService;
import org.springframework.stereotype.Component;

@Component
public non-sealed class CropDataMapperImpl implements CropDataMapper {

    private final FertilizerService fertilizerService;

    public CropDataMapperImpl(FertilizerService fertilizerService) {
        this.fertilizerService = fertilizerService;
    }

    @Override
    public FertilizerApplicationRate fromFertilizerApplicationRateRequest(FertilizerApplicationRateRequest request) {
        Fertilizer fertilizer = fertilizerService.getById(request.getFertilizerId());
        FertilizerApplicationRate fertilizerApplicationRate = FertilizerApplicationRate.builder()
                .fertilizer(fertilizer)
                .quantity(request.getQuantity())
                .unit(request.getUnit())
                .perUnit(request.getPerUnit())
                .build();
        return fertilizerApplicationRate;
    }

}
