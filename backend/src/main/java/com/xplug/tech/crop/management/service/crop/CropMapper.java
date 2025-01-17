package com.xplug.tech.crop.management.service.crop;

import com.xplug.tech.crop.management.domain.Crop;

public sealed interface CropMapper permits CropMapperImpl {

    Crop cropFromCropRequest(CropRequest cropRequest);

    Crop cropFromCropUpdateRequest(Crop crop, CropUpdateRequest cropUpdateRequest);

    CropResponse cropResponseFromCrop(Crop crop);

}
