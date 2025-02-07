package com.xplug.tech.cropbatch;

import com.xplug.tech.crop.CropBatch;

import java.util.List;

public sealed interface CropBatchService permits CropBatchServiceImpl {

    List<CropBatch> getAll();

    List<CropBatch> getByFarmer(Long userAccountId);

    CropBatch getById(Long id);

    CropBatch create(CropBatchRequest cropBatchRequest);

    CropBatch update(CropBatchUpdateRequest cropBatchUpdateRequest);

    void delete(Long id);

}
