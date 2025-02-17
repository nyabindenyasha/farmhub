package com.xplug.tech.cropprogram;

import com.xplug.tech.crop.*;
import com.xplug.tech.enums.CropScheduleType;
import com.xplug.tech.exception.ItemAlreadyExistsException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@Service
public non-sealed class CropProgramServiceImpl implements CropProgramService {

    private final CropProgramDao cropScheduleRepository;

    private final CropProgramMapper cropProgramMapper;

    private final CropFertilizerScheduleDao cropFertilizerScheduleService;

    private final CropPesticideScheduleDao cropPesticideScheduleService;

    public CropProgramServiceImpl(CropProgramDao cropScheduleRepository, CropProgramMapper cropProgramMapper, CropFertilizerScheduleDao cropFertilizerScheduleService, CropPesticideScheduleDao cropPesticideScheduleService) {
        this.cropScheduleRepository = cropScheduleRepository;
        this.cropProgramMapper = cropProgramMapper;
        this.cropFertilizerScheduleService = cropFertilizerScheduleService;
        this.cropPesticideScheduleService = cropPesticideScheduleService;
    }


    public List<CropProgram> getAll() {
        return cropScheduleRepository.findAll();
    }

    @Override
    public List<CropProgram> getByCrop(Long cropId) {
        var cropProgramList = cropScheduleRepository.findByCropId(cropId);
        cropProgramList.forEach(cropProgram -> {
            cropProgram.setFertilizerScheduleList(cropFertilizerScheduleService.findByCropProgramId(cropProgram.getId()));
            cropProgram.setPesticideScheduleList(cropPesticideScheduleService.findByCropProgramId(cropProgram.getId()));
        });
        return cropProgramList;
    }

    public CropProgram getById(Long id) {
        var cropSchedule = cropScheduleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CropSchedule not found with ID: " + id));
        cropSchedule.setFertilizerScheduleList(cropFertilizerScheduleService.findByCropProgramId(cropSchedule.getId()));
        cropSchedule.setPesticideScheduleList(cropPesticideScheduleService.findByCropProgramId(cropSchedule.getId()));
        return cropSchedule;
    }

    @Override
    public CropProgram getByCropIdAndCropScheduleType(Long cropId, CropScheduleType cropScheduleType) {
        return cropScheduleRepository.findByCropIdAndCropScheduleType(cropId, cropScheduleType)
                .orElseThrow(() -> new RuntimeException("CropSchedule not found with crop Id: " + cropId));
    }

    public CropProgram create(CropProgramRequest cropProgramRequest) {
        var optionalCropSchedule = cropScheduleRepository
                .findByCropIdAndCropScheduleType(cropProgramRequest.getCropId(), cropProgramRequest.getCropScheduleType());
        if (optionalCropSchedule.isPresent() && optionalCropSchedule.get().getCropScheduleType().equals(CropScheduleType.PRIMARY)) {
            throw new ItemAlreadyExistsException("CropSchedule for crop with Id: " + cropProgramRequest.getCropId() + " already exists");
        }
        var cropSchedule = cropProgramMapper
                .cropScheduleFromCropScheduleRequest(cropProgramRequest);
        var savedCropSchedule = cropScheduleRepository.save(cropSchedule);
        return savedCropSchedule;
    }

    public CropProgram create(CropProgramRequestV2 cropProgramRequest) {
        var optionalCropSchedule = cropScheduleRepository
                .findByCropIdAndCropScheduleType(cropProgramRequest.getCropId(), cropProgramRequest.getCropScheduleType());
        if (optionalCropSchedule.isPresent() && optionalCropSchedule.get().getCropScheduleType().equals(CropScheduleType.PRIMARY)) {
            throw new ItemAlreadyExistsException("CropSchedule for crop with Id: " + cropProgramRequest.getCropId() + " already exists");
        }

        Set<CropFertilizerSchedule> cropFertilizerSchedules = new HashSet<>();
//        cropProgramRequest.getFertilizerScheduleRequests().forEach(cropFertilizerScheduleRequest -> {
//            var savedCropFertilizerSchedule = cropFertilizerScheduleService.create(cropFertilizerScheduleRequest);
//            cropFertilizerSchedules.add(savedCropFertilizerSchedule);
//        });

        Set<CropPesticideSchedule> cropPesticideSchedules = new HashSet<>();
//        cropProgramRequest.getPesticideScheduleRequests().forEach(cropPesticideScheduleRequest -> {
//            var savedCropPesticideSchedule = cropPesticideScheduleService.create(cropPesticideScheduleRequest);
//            cropPesticideSchedules.add(savedCropPesticideSchedule);
//        });

        var cropSchedule = cropProgramMapper
                .cropScheduleFromCropScheduleRequest(cropProgramRequest, cropFertilizerSchedules, cropPesticideSchedules);
        var savedCropSchedule = cropScheduleRepository.save(cropSchedule);
        return savedCropSchedule;
    }

    @Override
    public CropProgram save(CropProgram cropProgram) {
        return cropScheduleRepository.save(cropProgram);
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
