package com.xplug.tech.crop.management.service.event;

import com.xplug.tech.crop.management.domain.Crop;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class CropCreatedEvent extends ApplicationEvent {

    private final Crop crop;

    public CropCreatedEvent(Object source, Crop crop) {
        super(source);
        this.crop = crop;
    }

}
