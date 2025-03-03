package com.xplug.tech.cropbatch;

import com.xplug.tech.crop.CropScheduleTask;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class CropBatchTaskUpdateRequest {

    @NotNull(message = "Batch id cannot be null!")
    private Long batchId;

    @NotNull(message = "CropScheduleTask cannot be null!")
    private CropScheduleTask cropScheduleTask;

}
