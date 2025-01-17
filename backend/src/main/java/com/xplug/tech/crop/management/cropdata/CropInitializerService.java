package com.xplug.tech.crop.management.cropdata;

import com.xplug.tech.crop.management.dao.CropDao;
import com.xplug.tech.crop.management.enums.CropScheduleType;
import com.xplug.tech.crop.management.service.crop.CropService;
import com.xplug.tech.crop.management.service.cropfertilizerschedule.CropFertilizerScheduleService;
import com.xplug.tech.crop.management.service.croppesticideschedule.CropPesticideScheduleService;
import com.xplug.tech.crop.management.service.cropschedule.CropScheduleService;
import com.xplug.tech.crop.management.service.cropstagesofgrowth.CropStagesOfGrowthService;
import com.xplug.tech.crop.management.service.fertilizer.FertilizerService;
import com.xplug.tech.crop.management.service.pesticide.PesticideService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Component
public class CropInitializerService {

    private final CropDao cropDao;

    private final CropService cropService;

    private final FertilizerService fertilizerService;

    private final PesticideService pesticideService;

    private final CropScheduleService cropScheduleService;

    private final CropStagesOfGrowthService cropStagesOfGrowthService;

    private final CropFertilizerScheduleService cropFertilizerScheduleService;

    private final CropPesticideScheduleService cropPesticideScheduleService;

    public CropInitializerService(CropDao cropDao,
                                  CropService cropService, FertilizerService fertilizerService,
                                  PesticideService pesticideService,
                                  CropScheduleService cropScheduleService,
                                  CropStagesOfGrowthService cropStagesOfGrowthService,
                                  CropFertilizerScheduleService cropFertilizerScheduleService,
                                  CropPesticideScheduleService cropPesticideScheduleService) {
        this.cropDao = cropDao;
        this.cropService = cropService;
        this.fertilizerService = fertilizerService;
        this.pesticideService = pesticideService;
        this.cropScheduleService = cropScheduleService;
        this.cropStagesOfGrowthService = cropStagesOfGrowthService;
        this.cropFertilizerScheduleService = cropFertilizerScheduleService;
        this.cropPesticideScheduleService = cropPesticideScheduleService;
    }

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
                cropDataFertilizerScheduleRequest.setCropScheduleId(cropSchedule.getId());
                cropDataFertilizerScheduleRequest.setFertilizerId(fertilizer.getId());
                cropFertilizerScheduleService.create(cropDataFertilizerScheduleRequest);
            });

            cropDataPesticideScheduleRequests.forEach(cropDataPesticideScheduleRequest -> {
                var pesticide = pesticideService.getByName(cropDataPesticideScheduleRequest.getPesticideName());
                cropDataPesticideScheduleRequest.setCropScheduleId(cropSchedule.getId());
                cropDataPesticideScheduleRequest.setPesticideId(pesticide.getId());
                cropPesticideScheduleService.create(cropDataPesticideScheduleRequest);
            });

            cropDataCropStagesOfGrowthRequests.forEach(cropDataCropStagesOfGrowthRequest -> {
                cropDataCropStagesOfGrowthRequest.setCropId(savedCrop.getId());
                cropStagesOfGrowthService.initialize(cropDataCropStagesOfGrowthRequest);
            });

        });
    }

}
