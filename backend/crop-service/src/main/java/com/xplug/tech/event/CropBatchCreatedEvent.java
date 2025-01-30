package com.xplug.tech.event;

import com.xplug.tech.crop.CropBatch;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class CropBatchCreatedEvent extends ApplicationEvent {

    private final CropBatch cropBatch;

    public CropBatchCreatedEvent(Object source, CropBatch cropBatch) {
        super(source);
        this.cropBatch = cropBatch;
    }

}
