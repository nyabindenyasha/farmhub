package com.xplug.tech.crop.management.service.fertilizer;

import com.xplug.tech.crop.management.domain.Fertilizer;

public sealed interface FertilizerMapper permits FertilizerMapperImpl {

    Fertilizer fertilizerFromFertilizerRequest(FertilizerRequest fertilizerRequest);

    Fertilizer fertilizerFromFertilizerUpdateRequest(Fertilizer fertilizer, FertilizerUpdateRequest fertilizerUpdateRequest);

    FertilizerResponse fertilizerResponseFromFertilizer(Fertilizer fertilizer);

}
