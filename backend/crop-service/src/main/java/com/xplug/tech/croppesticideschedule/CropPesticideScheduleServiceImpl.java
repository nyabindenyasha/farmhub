package com.xplug.tech.croppesticideschedule;

import com.xplug.tech.crop.CropPesticideSchedule;
import com.xplug.tech.crop.CropPesticideScheduleDao;
import com.xplug.tech.cropprograms.CropProgramService;
import com.xplug.tech.enums.CropScheduleType;
import com.xplug.tech.exception.ItemAlreadyExistsException;
import com.xplug.tech.period.PeriodService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public non-sealed class CropPesticideScheduleServiceImpl implements CropPesticideScheduleService {

    private final CropPesticideScheduleDao cropPesticideScheduleRepository;

    private final CropPesticideScheduleMapper cropPesticideScheduleMapper;

    private final CropProgramService cropProgramService;

    private final PeriodService periodService;

    public CropPesticideScheduleServiceImpl(CropPesticideScheduleDao cropPesticideScheduleRepository, CropPesticideScheduleMapper cropPesticideScheduleMapper, CropProgramService cropProgramService, PeriodService periodService) {
        this.cropPesticideScheduleRepository = cropPesticideScheduleRepository;
        this.cropPesticideScheduleMapper = cropPesticideScheduleMapper;
        this.cropProgramService = cropProgramService;
        this.periodService = periodService;
    }


    public List<CropPesticideSchedule> getAll() {
        return cropPesticideScheduleRepository.findAll();
    }

    public CropPesticideSchedule getById(Long id) {
        return cropPesticideScheduleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CropPesticideSchedule not found with ID: " + id));
    }

    @Override
    public List<CropPesticideSchedule> getByCropAndScheduleType(Long cropId, CropScheduleType cropScheduleType) {
        return cropPesticideScheduleRepository.findByCropScheduleCropIdAndCropScheduleCropScheduleType(cropId, cropScheduleType);
    }

    @Override
    public CropPesticideSchedule getByCropAndScheduleTypeAndStageOfGrowth(Long cropId, CropScheduleType cropScheduleType, Long stageOfGrowthId) {
        return cropPesticideScheduleRepository
                .findByCropScheduleCropIdAndCropScheduleCropScheduleTypeAndStageOfGrowthId(cropId, cropScheduleType, stageOfGrowthId)
                .orElseThrow(() -> new RuntimeException("CropPesticideSchedule not found"));
    }

    public CropPesticideSchedule create(CropPesticideScheduleRequest request) {
        var period = periodService.findOrCreatePeriod(request.getStageOfGrowth());
        var optionalCropPesticideSchedule = cropPesticideScheduleRepository
                .findByCropScheduleIdAndPesticideIdAndStageOfGrowthId(request.getCropScheduleId(), request.getPesticideId(), period.getId());
        if (optionalCropPesticideSchedule.isPresent()) {
            throw new ItemAlreadyExistsException("CropPesticideSchedule already exists");
        }
        var cropPesticideSchedule = cropPesticideScheduleMapper
                .cropPesticideScheduleFromCropPesticideScheduleRequest(request);
        var savedCropPesticideSchedule = cropPesticideScheduleRepository.save(cropPesticideSchedule);
        cropProgramService.updatePesticideSchedule(savedCropPesticideSchedule);
        return savedCropPesticideSchedule;
    }

    public CropPesticideSchedule update(CropPesticideScheduleUpdateRequest cropPesticideScheduleUpdateRequest) {
        var cropPesticideSchedule = getById(cropPesticideScheduleUpdateRequest.getId());
        var updatedCropPesticideSchedule = cropPesticideScheduleMapper
                .cropPesticideScheduleFromCropPesticideScheduleUpdateRequest(cropPesticideSchedule, cropPesticideScheduleUpdateRequest);
        return cropPesticideScheduleRepository.save(updatedCropPesticideSchedule);
    }

    public void delete(Long id) {
        CropPesticideSchedule cropPesticideSchedule = getById(id);
        cropPesticideScheduleRepository.delete(cropPesticideSchedule);
    }

}
