package com.xplug.tech.cropbatch;

import com.xplug.tech.crop.CropBatch;
import com.xplug.tech.crop.CropFarmer;

import java.util.List;

public sealed interface CropBatchService permits CropBatchServiceImpl {

    List<CropBatch> getAll();

    CropBatch getById(Long id);

    CropBatch create(CropFarmer cropFarmer);

    CropBatch update(CropBatchUpdateRequest cropBatchUpdateRequest);

    void delete(Long id);

}
