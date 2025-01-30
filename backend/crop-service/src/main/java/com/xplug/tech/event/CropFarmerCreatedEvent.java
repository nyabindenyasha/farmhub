package com.xplug.tech.event;

import com.xplug.tech.crop.CropFarmer;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class CropFarmerCreatedEvent extends ApplicationEvent {

    private final CropFarmer cropFarmer;

    private final Long cropProgramId;

    public CropFarmerCreatedEvent(Object source, CropFarmer cropFarmer, Long cropProgramId) {
        super(source);
        this.cropFarmer = cropFarmer;
        this.cropProgramId = cropProgramId;
    }

}
