package com.xplug.tech.event;

import com.xplug.tech.crop.CropFarmer;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class CropFarmerCreatedEvent extends ApplicationEvent {

    private final CropFarmer cropFarmer;

    public CropFarmerCreatedEvent(Object source, CropFarmer cropFarmer) {
        super(source);
        this.cropFarmer = cropFarmer;
    }

}
