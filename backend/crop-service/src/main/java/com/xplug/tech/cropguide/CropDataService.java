package com.xplug.tech.cropguide;

import com.xplug.tech.crop.data.CropData;

import java.util.List;

public sealed interface CropDataService permits CropDataServiceImpl {

    CropData create(CropGuideRequest cropData);

    List<CropData> getAll();

    CropData getByCropId(Long cropId);

}
