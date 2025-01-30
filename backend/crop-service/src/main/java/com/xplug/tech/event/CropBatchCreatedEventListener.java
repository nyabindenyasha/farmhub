package com.xplug.tech.event;

import com.xplug.tech.crop.CropSchedule;
import com.xplug.tech.cropfertilizerschedule.CropFertilizerScheduleService;
import com.xplug.tech.croppesticideschedule.CropPesticideScheduleService;
import com.xplug.tech.cropschedule.CropScheduleService;
import com.xplug.tech.enums.CropScheduleType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CropBatchCreatedEventListener implements ApplicationListener<CropBatchCreatedEvent> {

    private final CropScheduleService cropScheduleService;
    private final CropFertilizerScheduleService cropFertilizerScheduleService;

    private final CropPesticideScheduleService cropPesticideScheduleService;

    public CropBatchCreatedEventListener(CropScheduleService cropScheduleService, CropFertilizerScheduleService cropFertilizerScheduleService, CropPesticideScheduleService cropPesticideScheduleService) {
        this.cropScheduleService = cropScheduleService;
        this.cropFertilizerScheduleService = cropFertilizerScheduleService;
        this.cropPesticideScheduleService = cropPesticideScheduleService;
    }

    @Override
    public void onApplicationEvent(CropBatchCreatedEvent cropBatchCreatedEvent) {

        log.info("### CropBatchCreatedEvent");




    }
}
