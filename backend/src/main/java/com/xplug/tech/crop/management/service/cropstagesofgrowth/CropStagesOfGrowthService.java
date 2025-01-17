package com.xplug.tech.crop.management.service.cropstagesofgrowth;

import com.xplug.tech.crop.management.domain.Crop;
import com.xplug.tech.crop.management.domain.CropStagesOfGrowth;
import com.xplug.tech.crop.management.service.crop.CropRequest;

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
