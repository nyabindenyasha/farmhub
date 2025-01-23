package com.xplug.tech.cropstagesofgrowth;

import com.xplug.tech.crop.CropStagesOfGrowth;

import java.util.List;

public sealed interface CropStagesOfGrowthService permits CropStagesOfGrowthServiceImpl {

    List<CropStagesOfGrowth> getAll();

    CropStagesOfGrowth getById(Long id);

    List<CropStagesOfGrowth> getByCropId(Long cropId);

    CropStagesOfGrowth create(CropStagesOfGrowthRequest cropStagesOfGrowthRequest);

    void initialize(CropStagesOfGrowthRequest cropStagesOfGrowthRequest);

    CropStagesOfGrowth update(CropStagesOfGrowthUpdateRequest cropStagesOfGrowthUpdateRequest);

    void delete(Long id);

}
