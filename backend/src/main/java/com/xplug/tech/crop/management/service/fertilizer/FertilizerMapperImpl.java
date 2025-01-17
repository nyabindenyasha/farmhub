package com.xplug.tech.crop.management.service.fertilizer;

import com.xplug.tech.crop.management.domain.Fertilizer;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public non-sealed class FertilizerMapperImpl implements FertilizerMapper {

    @Override
    public Fertilizer fertilizerFromFertilizerRequest(FertilizerRequest fertilizerRequest) {
        Objects.requireNonNull(fertilizerRequest, "FertilizerRequest cannot be null!");
        return Fertilizer.builder()
                .name(fertilizerRequest.getName())
                .alias(fertilizerRequest.getAlias())
                .composition(fertilizerRequest.getComposition())
                .remarks(fertilizerRequest.getRemarks())
                .build();
    }

    @Override
    public Fertilizer fertilizerFromFertilizerUpdateRequest(Fertilizer fertilizer, FertilizerUpdateRequest fertilizerUpdateRequest) {
        Objects.requireNonNull(fertilizer, "Fertilizer cannot be null!");
        Objects.requireNonNull(fertilizerUpdateRequest, "FertilizerUpdateRequest cannot be null!");
        fertilizer.setName(fertilizerUpdateRequest.getName());
        fertilizer.setAlias(fertilizerUpdateRequest.getAlias());
        fertilizer.setComposition(fertilizerUpdateRequest.getComposition());
        fertilizer.setRemarks(fertilizerUpdateRequest.getRemarks());
        return fertilizer;
    }

    @Override
    public FertilizerResponse fertilizerResponseFromFertilizer(Fertilizer fertilizer) {
        return FertilizerResponse.of(fertilizer);
    }

}
