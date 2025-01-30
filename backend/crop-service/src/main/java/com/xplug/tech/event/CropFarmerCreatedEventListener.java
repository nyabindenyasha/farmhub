package com.xplug.tech.event;

import com.xplug.tech.crop.CropSchedule;
import com.xplug.tech.cropbatch.CropBatchRequest;
import com.xplug.tech.cropbatch.CropBatchService;
import com.xplug.tech.enums.CropScheduleType;
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
        CropBatchRequest cropBatchRequest = new CropBatchRequest();
        cropBatchRequest.setCropFarmerId(cropFarmerCreatedEvent.getCropFarmer().getId());
        cropBatchRequest.setCropProgramId(1L);
        cropBatchService.create(cropBatchRequest);
    }

}
