package com.xplug.tech.cropprogram;

import com.xplug.tech.crop.CropProgram;
import com.xplug.tech.crop.CropProgramDao;
import com.xplug.tech.enums.CropScheduleType;
import com.xplug.tech.exception.ItemAlreadyExistsException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public non-sealed class CropProgramServiceImpl implements CropProgramService {

    private final CropProgramDao cropScheduleRepository;

    private final CropProgramMapper cropProgramMapper;

    public CropProgramServiceImpl(CropProgramDao cropScheduleRepository, CropProgramMapper cropProgramMapper) {
        this.cropScheduleRepository = cropScheduleRepository;
        this.cropProgramMapper = cropProgramMapper;
    }

    public List<CropProgram> getAll() {
        return cropScheduleRepository.findAll();
    }

    @Override
    public List<CropProgram> getByCrop(Long cropId) {
        return cropScheduleRepository.findByCropId(cropId);
    }

    public CropProgram getById(Long id) {
        return cropScheduleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CropSchedule not found with ID: " + id));
    }

    @Override
    public CropProgram getByCropIdAndCropScheduleType(Long cropId, CropScheduleType cropScheduleType) {
        return cropScheduleRepository.findByCropIdAndCropScheduleType(cropId, cropScheduleType)
                .orElseThrow(() -> new RuntimeException("CropSchedule not found with crop Id: " + cropId));
    }

    @Override
    public CropProgram create(CropProgramRequest cropProgramRequest) {
        var optionalCropSchedule = cropScheduleRepository
                .findByCropIdAndCropScheduleType(cropProgramRequest.getCropId(), cropProgramRequest.getCropScheduleType());
        if (optionalCropSchedule.isPresent() && optionalCropSchedule.get().getCropScheduleType().equals(CropScheduleType.PRIMARY)) {
            throw new ItemAlreadyExistsException("CropSchedule for crop with Id: " + cropProgramRequest.getCropId() + " already exists");
        }

        var cropProgram = cropProgramMapper
                .cropScheduleFromCropScheduleRequest(cropProgramRequest);
        var savedCropProgram = cropScheduleRepository.save(cropProgram);
        log.info("### savedCropProgram: {}", savedCropProgram);
        return savedCropProgram;
    }

    @Override
    public CropProgram save(CropProgram cropProgram) {
        CropProgram savedCropProgram = cropScheduleRepository.save(cropProgram);
        log.info("### savedCropProgram {}", savedCropProgram);
        return savedCropProgram;
    }

    public CropProgram update(CropProgramUpdateRequest cropScheduleUpdateRequest) {
        var cropSchedule = getById(cropScheduleUpdateRequest.getId());
        var updatedCropSchedule = cropProgramMapper
                .cropScheduleFromCropScheduleUpdateRequest(cropSchedule, cropScheduleUpdateRequest);
        return cropScheduleRepository.save(updatedCropSchedule);
    }

    public void delete(Long id) {
        CropProgram cropProgram = getById(id);
        cropScheduleRepository.delete(cropProgram);
    }

}
