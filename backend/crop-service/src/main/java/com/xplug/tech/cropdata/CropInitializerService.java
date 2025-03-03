package com.xplug.tech.cropdata;

import com.xplug.tech.crop.CropDao;
import com.xplug.tech.crop.CropService;
import com.xplug.tech.cropfertilizerschedule.CropFertilizerScheduleService;
import com.xplug.tech.cropguide.CropDataService;
import com.xplug.tech.croppesticideschedule.CropPesticideScheduleService;
import com.xplug.tech.cropprogram.CropProgramService;
import com.xplug.tech.cropstagesofgrowth.CropStagesOfGrowthService;
import com.xplug.tech.cropvariety.CropVarietyService;
import com.xplug.tech.enums.CropScheduleType;
import com.xplug.tech.event.SystemConfiguredEvent;
import com.xplug.tech.fertilizer.FertilizerService;
import com.xplug.tech.pesticide.PesticideService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

import static java.util.Objects.isNull;

@Slf4j
@Component
@RequiredArgsConstructor
public class CropInitializerService {

    private final CropDao cropDao;

    private final CropService cropService;

    private final FertilizerService fertilizerService;

    private final PesticideService pesticideService;

    private final CropProgramService cropProgramService;

    private final CropStagesOfGrowthService cropStagesOfGrowthService;

    private final CropFertilizerScheduleService cropFertilizerScheduleService;

    private final CropPesticideScheduleService cropPesticideScheduleService;

    private final CropVarietyService cropVarietyService;

    private final ApplicationEventPublisher applicationEventPublisher;

    private final CropDataService cropDataService;


    @Transactional
    public void initializeCrops(List<CropData> cropDataList) {
        log.info("cropDataList: {}", cropDataList);
        cropDataList.forEach(cropData -> {

            var cropRequest = cropData.getCrop();

            var optionalCrop = cropDao.findByNameContainsIgnoreCase(cropRequest.getName());

            if (optionalCrop.isPresent()) {
                return;
            }

            var savedCrop = cropService.initialize(cropRequest);


            var cropProgram = cropProgramService.getByCropIdAndCropScheduleType(savedCrop.getId(), CropScheduleType.PRIMARY);

            var cropDataFertilizerScheduleRequests = cropData.getFertilizerSchedule();

            var cropDataPesticideScheduleRequests = cropData.getPesticideSchedule();

            var cropStagesOfGrowthRequests = cropData.getStagesOfGrowth();

            cropStagesOfGrowthRequests.forEach(cropStagesOfGrowthRequest -> {
                cropStagesOfGrowthService.initialize(cropStagesOfGrowthRequest, savedCrop.getId());
            });

            cropDataFertilizerScheduleRequests.forEach(cropDataFertilizerScheduleRequest -> {
                var fertilizer = fertilizerService.getByName(cropDataFertilizerScheduleRequest.getFertilizerName());
                var savedCropFertilizerSchedule = cropFertilizerScheduleService.initialize(cropProgram, fertilizer, cropDataFertilizerScheduleRequest);
                cropProgram.getFertilizerScheduleList().add(savedCropFertilizerSchedule);
            });

            cropDataPesticideScheduleRequests.forEach(cropDataPesticideScheduleRequest -> {
                var pesticide = pesticideService.getByName(cropDataPesticideScheduleRequest.getPesticideName());
                var savedCropPesticideSchedule = cropPesticideScheduleService.initialize(cropProgram, pesticide, cropDataPesticideScheduleRequest);
                cropProgram.getPesticideScheduleList().add(savedCropPesticideSchedule);
            });

            cropProgramService.save(cropProgram);

//            cropProgramService.save(cropProgram, cropDataFertilizerScheduleRequests, cropDataPesticideScheduleRequests);

            var cropVarietyRequests = cropData.getVarieties();

            cropVarietyRequests.forEach(cropVarietyRequest -> {
                cropVarietyRequest.setCropId(savedCrop.getId());
                cropVarietyService.create(cropVarietyRequest);
            });

            log.info("### CropData: {}", cropData.getCropData());
            if (isNull(cropData.getCropData().getNurseryManagement()) || isNull(cropData.getCropData().getFieldManagement())) {
                return;
            }
            var savedCropData = cropDataService.create(cropData.getCropData());
            log.info("### Saved CropData: {}", savedCropData);

        });
//todo load users first
        applicationEventPublisher.publishEvent(new SystemConfiguredEvent(this));
    }

}
