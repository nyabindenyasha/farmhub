package com.xplug.tech.cropguide;

import com.xplug.tech.crop.data.FertilizerApplicationRate;

public sealed interface CropDataMapper permits CropDataMapperImpl {

    FertilizerApplicationRate fromFertilizerApplicationRateRequest(FertilizerApplicationRateRequest request);

}
