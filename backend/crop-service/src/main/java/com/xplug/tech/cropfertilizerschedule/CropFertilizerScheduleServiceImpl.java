package com.xplug.tech.cropfertilizerschedule;

import com.xplug.tech.crop.CropFertilizerSchedule;
import com.xplug.tech.crop.CropFertilizerScheduleDao;
import com.xplug.tech.crop.CropSchedule;
import com.xplug.tech.crop.Fertilizer;
import com.xplug.tech.enums.CropScheduleType;
import com.xplug.tech.exception.ItemAlreadyExistsException;
import com.xplug.tech.period.PeriodService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

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
    public Set<CropFertilizerSchedule> getByCropScheduleId(Long cropScheduleId) {
        return cropFertilizerScheduleRepository.findByCropScheduleId(cropScheduleId);
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
        var savedCropFertilizerSchedule = cropFertilizerScheduleRepository.save(cropFertilizerSchedule);
        return savedCropFertilizerSchedule;
    }

    @Override
    public CropFertilizerSchedule initialize(CropSchedule cropSchedule, Fertilizer fertilizer, CropFertilizerScheduleRequest cropFertilizerScheduleRequest) {
        var cropFertilizerSchedule = CropFertilizerSchedule.builder()
                .cropSchedule(cropSchedule)
                .fertilizer(fertilizer)
                .stageOfGrowth(periodService.findOrCreatePeriod(cropFertilizerScheduleRequest.getStageOfGrowth()))
                .applicationInterval(periodService.findOrCreatePeriod(cropFertilizerScheduleRequest.getApplicationInterval()))
                .rate(cropFertilizerScheduleRequest.getRate())
                .applicationMethod(cropFertilizerScheduleRequest.getApplicationMethod())
                .remarks(cropFertilizerScheduleRequest.getRemarks())
                .build();
        return cropFertilizerScheduleRepository.save(cropFertilizerSchedule);
    }

    //todo update crop program as well
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
