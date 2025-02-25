package com.xplug.tech.croppesticideschedule;

import com.xplug.tech.crop.CropPesticideSchedule;
import com.xplug.tech.crop.CropPesticideScheduleDao;
import com.xplug.tech.crop.CropProgram;
import com.xplug.tech.crop.Pesticide;
import com.xplug.tech.enums.CropScheduleType;
import com.xplug.tech.exception.ItemAlreadyExistsException;
import com.xplug.tech.period.PeriodService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

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
    public Set<CropPesticideSchedule> getByCropScheduleId(Long cropScheduleId) {
        return cropPesticideScheduleRepository.findByCropProgramId(cropScheduleId);
    }

    @Override
    public List<CropPesticideSchedule> getByCropAndScheduleType(Long cropId, CropScheduleType cropScheduleType) {
        return cropPesticideScheduleRepository.findByCropProgramCropIdAndCropProgramCropScheduleType(cropId, cropScheduleType);
    }

    @Override
    public CropPesticideSchedule getByCropAndScheduleTypeAndStageOfGrowth(Long cropId, CropScheduleType cropScheduleType, Long stageOfGrowthId) {
        return cropPesticideScheduleRepository
                .findByCropProgramCropIdAndCropProgramCropScheduleTypeAndStageOfGrowthId(cropId, cropScheduleType, stageOfGrowthId)
                .orElseThrow(() -> new RuntimeException("CropPesticideSchedule not found"));
    }

    @Override
    public CropPesticideSchedule initialize(CropProgram cropProgram, Pesticide pesticide, CropPesticideScheduleRequest cropPesticideScheduleRequest) {
        var cropPesticideSchedule = CropPesticideSchedule.builder()
                .cropProgram(cropProgram)
                .pesticide(pesticide)
                .stageOfGrowth(periodService.findOrCreatePeriod(cropPesticideScheduleRequest.getStageOfGrowth()))
                .applicationInterval(periodService.findOrCreatePeriod(cropPesticideScheduleRequest.getApplicationInterval()))
                .applicationMethod(cropPesticideScheduleRequest.getApplicationMethod())
                .remarks(cropPesticideScheduleRequest.getRemarks())
                .build();
        return cropPesticideScheduleRepository.save(cropPesticideSchedule);
    }

    public CropPesticideSchedule create(CropPesticideScheduleRequest request) {
        var period = periodService.findOrCreatePeriod(request.getStageOfGrowth());
        var optionalCropPesticideSchedule = cropPesticideScheduleRepository
                .findByCropProgramIdAndPesticideIdAndStageOfGrowthId(request.getCropScheduleId(), request.getPesticideId(), period.getId());
        if (optionalCropPesticideSchedule.isPresent()) {
            throw new ItemAlreadyExistsException("CropPesticideSchedule already exists");
        }
        var cropPesticideSchedule = cropPesticideScheduleMapper
                .cropPesticideScheduleFromCropPesticideScheduleRequest(request);
        var savedCropPesticideSchedule = cropPesticideScheduleRepository.save(cropPesticideSchedule);
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

    @Override
    public Set<CropPesticideSchedule> findByCropProgramId(Long cropProgramId) {
        return cropPesticideScheduleRepository.findByCropProgramId(cropProgramId);
    }

}
