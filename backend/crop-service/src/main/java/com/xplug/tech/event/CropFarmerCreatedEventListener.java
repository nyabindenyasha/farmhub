package com.xplug.tech.event;

import com.xplug.tech.cropbatch.CropBatchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CropFarmerCreatedEventListener implements ApplicationListener<CropFarmerCreatedEvent> {

    private final CropBatchService cropBatchService;

    public CropFarmerCreatedEventListener(CropBatchService cropBatchService) {
        this.cropBatchService = cropBatchService;
    }


    @Override
    public void onApplicationEvent(CropFarmerCreatedEvent cropFarmerCreatedEvent) {

        log.info("### CropFarmerCreatedEvent");

        //todo request not necessary --- check redundancy
//        CropBatchCreateContext cropBatchCreateContext = new CropBatchCreateContext();
//        cropBatchCreateContext.setCropFarmer(cropFarmerCreatedEvent.getCropFarmer());
//        cropBatchCreateContext.setCropProgram(cropFarmerCreatedEvent.getCropProgram());
        cropBatchService.create(cropFarmerCreatedEvent.getCropFarmer());
    }

}
