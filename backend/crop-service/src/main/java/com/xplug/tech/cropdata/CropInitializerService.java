package com.xplug.tech.cropdata;

import com.xplug.tech.crop.CropDao;
import com.xplug.tech.crop.CropService;
import com.xplug.tech.cropfertilizerschedule.CropFertilizerScheduleService;
import com.xplug.tech.croppesticideschedule.CropPesticideScheduleService;
import com.xplug.tech.cropschedule.CropScheduleService;
import com.xplug.tech.cropstagesofgrowth.CropStagesOfGrowthService;
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

@Slf4j
@Component
@RequiredArgsConstructor
public class CropInitializerService {

    private final CropDao cropDao;

    private final CropService cropService;

    private final FertilizerService fertilizerService;

    private final PesticideService pesticideService;

    private final CropScheduleService cropScheduleService;

    private final CropStagesOfGrowthService cropStagesOfGrowthService;

    private final CropFertilizerScheduleService cropFertilizerScheduleService;

    private final CropPesticideScheduleService cropPesticideScheduleService;

    private final ApplicationEventPublisher applicationEventPublisher;


    @Transactional
    public void initializeCrops(List<CropData> cropDataList) {
        cropDataList.forEach(cropData -> {

            var cropRequest = cropData.getCrop();

            var optionalCrop = cropDao.findByNameContainsIgnoreCase(cropRequest.getName());

            if (optionalCrop.isPresent()) {
                return;
            }

            var savedCrop = cropService.initialize(cropRequest);

            var cropSchedule = cropScheduleService.getByCropIdAndCropScheduleType(savedCrop.getId(), CropScheduleType.PRIMARY);

            var cropDataFertilizerScheduleRequests = cropData.getFertilizerSchedule();

            var cropDataPesticideScheduleRequests = cropData.getPesticideSchedule();

            var cropDataCropStagesOfGrowthRequests = cropData.getStagesOfGrowth();

            cropDataFertilizerScheduleRequests.forEach(cropDataFertilizerScheduleRequest -> {
                var fertilizer = fertilizerService.getByName(cropDataFertilizerScheduleRequest.getFertilizerName());
//                cropDataFertilizerScheduleRequest.setCropScheduleId(cropSchedule.getId());
//                cropDataFertilizerScheduleRequest.setFertilizerId(fertilizer.getId());
                cropFertilizerScheduleService.initialize(cropSchedule, fertilizer, cropDataFertilizerScheduleRequest);
            });

            cropDataPesticideScheduleRequests.forEach(cropDataPesticideScheduleRequest -> {
                var pesticide = pesticideService.getByName(cropDataPesticideScheduleRequest.getPesticideName());
//                cropDataPesticideScheduleRequest.setCropScheduleId(cropSchedule.getId());
//                cropDataPesticideScheduleRequest.setPesticideId(pesticide.getId());
                cropPesticideScheduleService.initialize(cropSchedule, pesticide, cropDataPesticideScheduleRequest);
            });

            cropDataCropStagesOfGrowthRequests.forEach(cropDataCropStagesOfGrowthRequest -> {
                cropDataCropStagesOfGrowthRequest.setCropId(savedCrop.getId());
                cropStagesOfGrowthService.initialize(cropDataCropStagesOfGrowthRequest);
            });

        });
//todo load users first
        applicationEventPublisher.publishEvent(new SystemConfiguredEvent(this));
    }

}
