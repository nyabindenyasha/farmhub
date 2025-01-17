package com.xplug.tech.crop.management.service.croppesticideschedule;

import com.xplug.tech.crop.management.dao.CropPesticideScheduleDao;
import com.xplug.tech.crop.management.domain.CropPesticideSchedule;
import com.xplug.tech.crop.management.enums.CropScheduleType;
import com.xplug.tech.crop.management.exceptions.ItemAlreadyExistsException;
import com.xplug.tech.crop.management.service.period.PeriodService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public non-sealed class CropPesticideScheduleServiceImpl implements CropPesticideScheduleService {

    private final CropPesticideScheduleDao cropPesticideScheduleRepository;

    private final CropPesticideScheduleMapper cropPesticideScheduleMapper;

    private final PeriodService periodService;

    public CropPesticideScheduleServiceImpl(CropPesticideScheduleDao cropPesticideScheduleRepository, CropPesticideScheduleMapper cropPesticideScheduleMapper, PeriodService periodService) {
        this.cropPesticideScheduleRepository = cropPesticideScheduleRepository;
        this.cropPesticideScheduleMapper = cropPesticideScheduleMapper;
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
        return cropPesticideScheduleRepository.save(cropPesticideSchedule);
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
