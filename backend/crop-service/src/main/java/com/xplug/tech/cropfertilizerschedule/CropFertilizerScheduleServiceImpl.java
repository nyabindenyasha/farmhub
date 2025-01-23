package com.xplug.tech.cropfertilizerschedule;

import com.xplug.tech.crop.CropFertilizerSchedule;
import com.xplug.tech.crop.CropFertilizerScheduleDao;
import com.xplug.tech.enums.CropScheduleType;
import com.xplug.tech.period.PeriodService;
import com.xplug.tech.exception.ItemAlreadyExistsException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public non-sealed class CropFertilizerScheduleServiceImpl implements CropFertilizerScheduleService {

    private final CropFertilizerScheduleDao cropFertilizerScheduleRepository;

    private final CropFertilizerScheduleMapper cropFertilizerScheduleMapper;

    private final PeriodService periodService;

    public CropFertilizerScheduleServiceImpl(CropFertilizerScheduleDao cropFertilizerScheduleRepository, CropFertilizerScheduleMapper cropFertilizerScheduleMapper, PeriodService periodService) {
        this.cropFertilizerScheduleRepository = cropFertilizerScheduleRepository;
        this.cropFertilizerScheduleMapper = cropFertilizerScheduleMapper;
        this.periodService = periodService;
    }


    public List<CropFertilizerSchedule> getAll() {
        return cropFertilizerScheduleRepository.findAll();
    }

    public CropFertilizerSchedule getById(Long id) {
        return cropFertilizerScheduleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CropFertilizerSchedule not found with ID: " + id));
    }

    @Override
    public List<CropFertilizerSchedule> getByCropAndScheduleType(Long cropId, CropScheduleType cropScheduleType) {
        return cropFertilizerScheduleRepository.findByCropScheduleCropIdAndCropScheduleCropScheduleType(cropId, cropScheduleType);
    }

    @Override
    public CropFertilizerSchedule getByCropAndScheduleTypeAndStageOfGrowth(Long cropId, CropScheduleType cropScheduleType, Long stageOfGrowthId) {
        return cropFertilizerScheduleRepository
                .findByCropScheduleCropIdAndCropScheduleCropScheduleTypeAndStageOfGrowthId(cropId, cropScheduleType, stageOfGrowthId)
                .orElseThrow(() -> new RuntimeException("CropFertilizerSchedule not found"));
    }

    public CropFertilizerSchedule create(CropFertilizerScheduleRequest request) {
        var period = periodService.findOrCreatePeriod(request.getStageOfGrowth());
        var optionalCropFertilizerSchedule = cropFertilizerScheduleRepository
                .findByCropScheduleIdAndFertilizerIdAndStageOfGrowthId(request.getCropScheduleId(), request.getFertilizerId(), period.getId());
        if (optionalCropFertilizerSchedule.isPresent()) {
            throw new ItemAlreadyExistsException("CropFertilizerSchedule already exists");
        }
        var cropFertilizerSchedule = cropFertilizerScheduleMapper
                .cropFertilizerScheduleFromCropFertilizerScheduleRequest(request);
        return cropFertilizerScheduleRepository.save(cropFertilizerSchedule);
    }

    public CropFertilizerSchedule update(CropFertilizerScheduleUpdateRequest cropFertilizerScheduleUpdateRequest) {
        var cropFertilizerSchedule = getById(cropFertilizerScheduleUpdateRequest.getId());
        var updatedCropFertilizerSchedule = cropFertilizerScheduleMapper
                .cropFertilizerScheduleFromCropFertilizerScheduleUpdateRequest(cropFertilizerSchedule, cropFertilizerScheduleUpdateRequest);
        return cropFertilizerScheduleRepository.save(updatedCropFertilizerSchedule);
    }

    public void delete(Long id) {
        CropFertilizerSchedule cropFertilizerSchedule = getById(id);
        cropFertilizerScheduleRepository.delete(cropFertilizerSchedule);
    }

}
