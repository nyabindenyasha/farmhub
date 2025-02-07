package com.xplug.tech.cropbatch;

import com.xplug.tech.crop.CropBatch;
import com.xplug.tech.crop.CropBatchDao;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public non-sealed class CropBatchMapperImpl implements CropBatchMapper {

    private final CropBatchDao cropBatchRepository;

    public CropBatchMapperImpl(CropBatchDao cropBatchRepository) {
        this.cropBatchRepository = cropBatchRepository;
    }

    @Override
    public CropBatch cropBatchFromCropBatchRequest(CropBatchRequest cropBatchRequest) {
        Objects.requireNonNull(cropBatchRequest, "CropBatchRequest cannot be null!");
        CropBatch cropBatch = new CropBatch();
        return cropBatch;
    }

    @Override
    public CropBatch cropBatchFromCropBatchUpdateRequest(CropBatch cropBatch, CropBatchUpdateRequest cropBatchUpdateRequest) {
        Objects.requireNonNull(cropBatch, "CropBatch cannot be null!");
        Objects.requireNonNull(cropBatchUpdateRequest, "CropBatchUpdateRequest cannot be null!");
        return cropBatch;
    }

    @Override
    public CropBatchResponse cropBatchResponseFromCropBatch(CropBatch cropBatch) {
        return CropBatchResponse.of(cropBatch);
    }

}
