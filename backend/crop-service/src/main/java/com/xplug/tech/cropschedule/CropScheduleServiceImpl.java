package com.xplug.tech.cropschedule;

import com.xplug.tech.crop.CropSchedule;
import com.xplug.tech.crop.CropScheduleDao;
import com.xplug.tech.cropprograms.CropProgramService;
import com.xplug.tech.enums.CropScheduleType;
import com.xplug.tech.exception.ItemAlreadyExistsException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public non-sealed class CropScheduleServiceImpl implements CropScheduleService {

    private final CropScheduleDao cropScheduleRepository;

    private final CropScheduleMapper cropScheduleMapper;

    private final CropProgramService cropProgramService;

    public CropScheduleServiceImpl(CropScheduleDao cropScheduleRepository, CropScheduleMapper cropScheduleMapper, CropProgramService cropProgramService) {
        this.cropScheduleRepository = cropScheduleRepository;
        this.cropScheduleMapper = cropScheduleMapper;
        this.cropProgramService = cropProgramService;
    }


    public List<CropSchedule> getAll() {
        return cropScheduleRepository.findAll();
    }

    public CropSchedule getById(Long id) {
        return cropScheduleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CropSchedule not found with ID: " + id));
    }

    @Override
    public CropSchedule getByCropIdAndCropScheduleType(Long cropId, CropScheduleType cropScheduleType) {
        return cropScheduleRepository.findByCropIdAndCropScheduleType(cropId, cropScheduleType)
                .orElseThrow(() -> new RuntimeException("CropSchedule not found with crop Id: " + cropId));
    }

    public CropSchedule create(CropScheduleRequest cropScheduleRequest) {
        var optionalCropSchedule = cropScheduleRepository
                .findByCropIdAndCropScheduleType(cropScheduleRequest.getCropId(), cropScheduleRequest.getCropScheduleType());
        if (optionalCropSchedule.isPresent() && optionalCropSchedule.get().getCropScheduleType().equals(CropScheduleType.PRIMARY)) {
            throw new ItemAlreadyExistsException("CropSchedule for crop with Id: " + cropScheduleRequest.getCropId() + " already exists");
        }
        var cropSchedule = cropScheduleMapper
                .cropScheduleFromCropScheduleRequest(cropScheduleRequest);
        var savedCropSchedule = cropScheduleRepository.save(cropSchedule);
        cropProgramService.create(savedCropSchedule);
        return savedCropSchedule;
    }

    @Override
    public CropSchedule save(CropSchedule cropSchedule) {
        return cropScheduleRepository.save(cropSchedule);
    }

    public CropSchedule update(CropScheduleUpdateRequest cropScheduleUpdateRequest) {
        var cropSchedule = getById(cropScheduleUpdateRequest.getId());
        var updatedCropSchedule = cropScheduleMapper
                .cropScheduleFromCropScheduleUpdateRequest(cropSchedule, cropScheduleUpdateRequest);
        return cropScheduleRepository.save(updatedCropSchedule);
    }

    public void delete(Long id) {
        CropSchedule cropSchedule = getById(id);
        cropScheduleRepository.delete(cropSchedule);
    }

}
