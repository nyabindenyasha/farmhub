package com.xplug.tech.cropbatch;

import com.xplug.tech.crop.CropBatch;
import com.xplug.tech.cropfarmer.CropFarmerService;

public sealed interface CropBatchMapper permits CropBatchMapperImpl {

    CropBatch cropBatchFromCropBatchRequest(CropBatchRequest cropBatchRequest);

    CropBatch cropBatchFromCropBatchUpdateRequest(CropBatch cropBatch, CropBatchUpdateRequest cropBatchUpdateRequest);

    CropBatchResponse cropBatchResponseFromCropBatch(CropBatch cropBatch);

}
