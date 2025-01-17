package com.xplug.tech.crop.management.service.event;

import com.xplug.tech.crop.management.domain.CropSchedule;
import com.xplug.tech.crop.management.enums.CropScheduleType;
import com.xplug.tech.crop.management.service.cropfertilizerschedule.CropFertilizerScheduleService;
import com.xplug.tech.crop.management.service.croppesticideschedule.CropPesticideScheduleService;
import com.xplug.tech.crop.management.service.cropschedule.CropScheduleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CropCreatedEventListener implements ApplicationListener<CropCreatedEvent> {

    private final CropScheduleService cropScheduleService;
    private final CropFertilizerScheduleService cropFertilizerScheduleService;

    private final CropPesticideScheduleService cropPesticideScheduleService;

    public CropCreatedEventListener(CropScheduleService cropScheduleService, CropFertilizerScheduleService cropFertilizerScheduleService, CropPesticideScheduleService cropPesticideScheduleService) {
        this.cropScheduleService = cropScheduleService;
        this.cropFertilizerScheduleService = cropFertilizerScheduleService;
        this.cropPesticideScheduleService = cropPesticideScheduleService;
    }

    @Override
    public void onApplicationEvent(CropCreatedEvent cropCreatedEvent) {

        log.info("### CropCreatedEvent");

        //todo get values from a preloaded file

        var cropSchedule = CropSchedule.builder()
                .crop(cropCreatedEvent.getCrop())
                .name(cropCreatedEvent.getCrop().getName().concat("_default"))
                .description(cropCreatedEvent.getCrop().getName().concat("_default"))
                .source("system")
                .remarks("")
                .cropScheduleType(CropScheduleType.PRIMARY)
                .build();

        var savedCropSchedule = cropScheduleService.save(cropSchedule);

        log.info("### Saved Crop Schedule, {}", savedCropSchedule);

        //todo get values from a preloaded file

        for (int i = 0; i < 12; i++) {

//            var cropFertilizerSchedule = CropFertilizerSchedule.builder()
//                    .cropSchedule(savedCropSchedule)
//                    .fertilizer(fertilizer)
//                    .stageOfGrowth(periodService.findOrCreatePeriod(cropFertilizerScheduleRequest.getStageOfGrowth()))
//                    .applicationInterval(periodService.findOrCreatePeriod(cropFertilizerScheduleRequest.getApplicationInterval()))
//                    .applicationMethod(cropFertilizerScheduleRequest.getApplicationMethod())
//                    .remarks(cropFertilizerScheduleRequest.getRemarks())
//                    .build();
//
//            var cropPesticideSchedule = CropPesticideSchedule.builder()
//                    .cropSchedule(savedCropSchedule)
//                    .pesticide(fertilizer)
//                    .stageOfGrowth(periodService.findOrCreatePeriod(cropPesticideScheduleRequest.getStageOfGrowth()))
//                    .applicationInterval(periodService.findOrCreatePeriod(cropPesticideScheduleRequest.getApplicationInterval()))
//                    .applicationMethod(cropPesticideScheduleRequest.getApplicationMethod())
//                    .remarks(cropPesticideScheduleRequest.getRemarks())
//                    .build();

        }

    }
}
