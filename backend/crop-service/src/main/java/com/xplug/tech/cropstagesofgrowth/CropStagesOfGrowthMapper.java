package com.xplug.tech.cropstagesofgrowth;

import com.xplug.tech.crop.CropStagesOfGrowth;

public sealed interface CropStagesOfGrowthMapper permits CropStagesOfGrowthMapperImpl {

    CropStagesOfGrowth cropStagesOfGrowthFromCropStagesOfGrowthRequest(CropStagesOfGrowthRequest cropStagesOfGrowthRequest);

    CropStagesOfGrowth cropStagesOfGrowthFromCropStagesOfGrowthUpdateRequest(CropStagesOfGrowth cropStagesOfGrowth, CropStagesOfGrowthUpdateRequest cropStagesOfGrowthUpdateRequest);

    CropStagesOfGrowthResponse cropStagesOfGrowthResponseFromCropStagesOfGrowth(CropStagesOfGrowth cropStagesOfGrowth);

}
