package com.xplug.tech.crop.management.service.cropstagesofgrowth;

import com.xplug.tech.crop.management.domain.CropStagesOfGrowth;

public sealed interface CropStagesOfGrowthMapper permits CropStagesOfGrowthMapperImpl {

    CropStagesOfGrowth cropStagesOfGrowthFromCropStagesOfGrowthRequest(CropStagesOfGrowthRequest cropStagesOfGrowthRequest);

    CropStagesOfGrowth cropStagesOfGrowthFromCropStagesOfGrowthUpdateRequest(CropStagesOfGrowth cropStagesOfGrowth, CropStagesOfGrowthUpdateRequest cropStagesOfGrowthUpdateRequest);

    CropStagesOfGrowthResponse cropStagesOfGrowthResponseFromCropStagesOfGrowth(CropStagesOfGrowth cropStagesOfGrowth);

}
