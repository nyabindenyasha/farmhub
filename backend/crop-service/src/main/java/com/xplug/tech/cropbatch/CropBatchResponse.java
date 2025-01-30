package com.xplug.tech.cropbatch;

import com.xplug.tech.crop.CropBatch;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

@Getter
@Setter
@ToString
@Builder
public class CropBatchResponse {

    private Long id;


    public static CropBatchResponse of(CropBatch cropBatch) {
        Objects.requireNonNull(cropBatch, "Crop Batch cannot be null!");
        return CropBatchResponse.builder()
                .id(cropBatch.getId())
                .build();
    }

}
