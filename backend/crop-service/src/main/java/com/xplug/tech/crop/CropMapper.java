package com.xplug.tech.crop;

public sealed interface CropMapper permits CropMapperImpl {

    Crop cropFromCropRequest(CropRequest cropRequest);

    Crop cropFromCropUpdateRequest(Crop crop, CropUpdateRequest cropUpdateRequest);

    CropResponse cropResponseFromCrop(Crop crop);

}
