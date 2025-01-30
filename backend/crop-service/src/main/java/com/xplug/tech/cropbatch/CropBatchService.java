package com.xplug.tech.cropbatch;

import com.xplug.tech.crop.Crop;
import com.xplug.tech.crop.CropBatch;
import com.xplug.tech.crop.CropRequest;
import com.xplug.tech.crop.CropUpdateRequest;

import java.util.List;

public sealed interface CropBatchService permits CropBatchServiceImpl {

    List<CropBatch> getAll();

    CropBatch getById(Long id);

    CropBatch create(CropBatchRequest cropBatchRequest);

    CropBatch update(CropBatchUpdateRequest cropBatchUpdateRequest);

    void delete(Long id);

}
