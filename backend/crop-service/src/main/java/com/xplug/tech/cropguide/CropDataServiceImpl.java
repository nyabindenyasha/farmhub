package com.xplug.tech.cropguide;

import com.xplug.tech.crop.CropDataDao;
import com.xplug.tech.crop.CropService;
import com.xplug.tech.crop.data.CropData;
import com.xplug.tech.crop.data.CropFieldManagement;
import com.xplug.tech.crop.data.CropNurseryManagement;
import com.xplug.tech.crop.data.NurseryBedPreparation;
import com.xplug.tech.exception.ItemAlreadyExistsException;
import com.xplug.tech.exception.ItemNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public non-sealed class CropDataServiceImpl implements CropDataService {

    private final CropDataDao cropDataRepository;

    private final CropDataMapper cropDataMapper;

    private final CropService cropService;

    public CropDataServiceImpl(CropDataDao cropDataRepository, CropDataMapper cropDataMapper, CropService cropService) {
        this.cropDataRepository = cropDataRepository;
        this.cropDataMapper = cropDataMapper;
        this.cropService = cropService;
    }

    public CropData create(CropGuideRequest cropGuideRequest) {

        log.info("### CropGuideRequest: {}", cropGuideRequest);

        var optionalCropData = cropDataRepository.findByClassificationId(cropGuideRequest.getCropId());
        if (optionalCropData.isPresent()) {
            throw new ItemAlreadyExistsException("Crop Guide for crop with id: " + cropGuideRequest.getCropId() + " already exists");
        }

        NurseryBedPreparation nurseryBedPreparation = NurseryBedPreparation.builder()
                .fertilizerApplications(cropGuideRequest.getNurseryManagement().getNurseryBedPreparation().getFertilizerApplications().stream().map(fertilizerApplicationRateRequest -> cropDataMapper.fromFertilizerApplicationRateRequest(fertilizerApplicationRateRequest)).toList())
                .metadataList(cropGuideRequest.getNurseryManagement().getNurseryBedPreparation().getMetadataList())
                .build();

        log.info("### NurseryBedPreparation: {}", nurseryBedPreparation);

        CropNurseryManagement nurseryManagement = CropNurseryManagement.builder()
                .seedRate(cropGuideRequest.getNurseryManagement().getSeedRate())
                .nurseryPeriodicTimes(cropGuideRequest.getNurseryManagement().getNurseryPeriodicTimes())
                .nurserySpacing(cropGuideRequest.getNurseryManagement().getNurserySpacing())
                .soilTemperatureForGermination(cropGuideRequest.getNurseryManagement().getSoilTemperatureForGermination())
                .nurseryBedPreparation(nurseryBedPreparation)
                .build();

        log.info("### CropNurseryManagement: {}", nurseryManagement);

        CropFieldManagement fieldManagement = CropFieldManagement.builder()
                .soilRequirements(cropGuideRequest.getFieldManagement().getSoilRequirements())
                .pH(cropGuideRequest.getFieldManagement().getPH())
                .temperature(cropGuideRequest.getFieldManagement().getTemperature())
                .waterRequirement(cropGuideRequest.getFieldManagement().getWaterRequirement())
                .easeOfCare(cropGuideRequest.getFieldManagement().getEaseOfCare())
                .inRowSpacing(cropGuideRequest.getFieldManagement().getInRowSpacing())
                .interRowSpacing(cropGuideRequest.getFieldManagement().getInterRowSpacing())
                .plantPopulation(cropGuideRequest.getFieldManagement().getPlantPopulation())
                .build();

        log.info("### CropFieldManagement: {}", fieldManagement);

        CropData cropData = CropData.builder()
                .classification(cropService.getById(cropGuideRequest.getCropId()))
                .nurseryManagement(nurseryManagement)
                .fieldManagement(fieldManagement)
                .build();

        log.info("### CropData: {}", cropData);

        return cropDataRepository.save(cropData);
    }

    public List<CropData> getAll() {
        return cropDataRepository.findAll();
    }

    @Override
    public CropData getByCropId(Long cropId) {
        return cropDataRepository.findByClassificationId(cropId)
                .orElseThrow(() -> new ItemNotFoundException("Crop Guide not found for crop with id: " + cropId));
    }

}
