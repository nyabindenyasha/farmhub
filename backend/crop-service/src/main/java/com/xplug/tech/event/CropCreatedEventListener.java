package com.xplug.tech.event;

import com.xplug.tech.crop.CropProgram;
import com.xplug.tech.cropfertilizerschedule.CropFertilizerScheduleService;
import com.xplug.tech.croppesticideschedule.CropPesticideScheduleService;
import com.xplug.tech.cropprogram.CropProgramService;
import com.xplug.tech.enums.CropScheduleType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CropCreatedEventListener implements ApplicationListener<CropCreatedEvent> {

    private final CropProgramService cropProgramService;
    private final CropFertilizerScheduleService cropFertilizerScheduleService;

    private final CropPesticideScheduleService cropPesticideScheduleService;

    public CropCreatedEventListener(CropProgramService cropProgramService, CropFertilizerScheduleService cropFertilizerScheduleService, CropPesticideScheduleService cropPesticideScheduleService) {
        this.cropProgramService = cropProgramService;
        this.cropFertilizerScheduleService = cropFertilizerScheduleService;
        this.cropPesticideScheduleService = cropPesticideScheduleService;
    }

    @Override
    public void onApplicationEvent(CropCreatedEvent cropCreatedEvent) {

        log.info("### CropCreatedEvent");

        //todo get values from a preloaded file

        var cropProgram = CropProgram.builder()
                .crop(cropCreatedEvent.getCrop())
                .name(cropCreatedEvent.getCrop().getName().concat("_default"))
                .description(cropCreatedEvent.getCrop().getName().concat("_default"))
                .source("system")
                .remarks("")
                .cropScheduleType(CropScheduleType.PRIMARY)
                .build();

        var savedCropSchedule = cropProgramService.save(cropProgram);

        log.info("### Saved Crop Schedule, {}", savedCropSchedule);

        //todo get values from a preloaded file

        for (int i = 0; i < 12; i++) {

//            var cropFertilizerSchedule = CropFertilizerSchedule.builder()
//                    .cropProgram(savedCropSchedule)
//                    .fertilizer(fertilizer)
//                    .stageOfGrowth(periodService.findOrCreatePeriod(cropFertilizerScheduleRequest.getStageOfGrowth()))
//                    .applicationInterval(periodService.findOrCreatePeriod(cropFertilizerScheduleRequest.getApplicationInterval()))
//                    .applicationMethod(cropFertilizerScheduleRequest.getApplicationMethod())
//                    .remarks(cropFertilizerScheduleRequest.getRemarks())
//                    .build();
//
//            var cropPesticideSchedule = CropPesticideSchedule.builder()
//                    .cropProgram(savedCropSchedule)
//                    .pesticide(fertilizer)
//                    .stageOfGrowth(periodService.findOrCreatePeriod(cropPesticideScheduleRequest.getStageOfGrowth()))
//                    .applicationInterval(periodService.findOrCreatePeriod(cropPesticideScheduleRequest.getApplicationInterval()))
//                    .applicationMethod(cropPesticideScheduleRequest.getApplicationMethod())
//                    .remarks(cropPesticideScheduleRequest.getRemarks())
//                    .build();

        }

    }
}
