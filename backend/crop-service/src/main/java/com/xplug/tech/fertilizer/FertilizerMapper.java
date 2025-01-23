package com.xplug.tech.fertilizer;

import com.xplug.tech.crop.Fertilizer;

public sealed interface FertilizerMapper permits FertilizerMapperImpl {

    Fertilizer fertilizerFromFertilizerRequest(FertilizerRequest fertilizerRequest);

    Fertilizer fertilizerFromFertilizerUpdateRequest(Fertilizer fertilizer, FertilizerUpdateRequest fertilizerUpdateRequest);

    FertilizerResponse fertilizerResponseFromFertilizer(Fertilizer fertilizer);

}
