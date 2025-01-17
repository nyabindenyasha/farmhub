package com.xplug.tech.crop.management.service.cropvariety;

import com.xplug.tech.crop.management.dao.CropVarietyDao;
import com.xplug.tech.crop.management.domain.CropVariety;
import com.xplug.tech.crop.management.exceptions.ItemAlreadyExistsException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public non-sealed class CropVarietyServiceImpl implements CropVarietyService {

    private final CropVarietyDao cropVarietyRepository;

    private final CropVarietyMapper cropVarietyMapper;

    public CropVarietyServiceImpl(CropVarietyDao cropVarietyRepository, CropVarietyMapper cropVarietyMapper) {
        this.cropVarietyRepository = cropVarietyRepository;
        this.cropVarietyMapper = cropVarietyMapper;
    }


    public List<CropVariety> getAll() {
        return cropVarietyRepository.findAll();
    }

    public CropVariety getById(Long id) {
        return cropVarietyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CropVariety not found with ID: " + id));
    }

    public CropVariety create(CropVarietyRequest cropVarietyRequest) {
        var optionalCropVariety = cropVarietyRepository.findByCropIdAndVariety(cropVarietyRequest.getCropId(), cropVarietyRequest.getVariety());
        if (optionalCropVariety.isPresent()) {
            throw new ItemAlreadyExistsException("CropVariety with name: " + cropVarietyRequest.getVariety() + " already exists");
        }
        var cropVariety = cropVarietyMapper.cropVarietyFromCropVarietyRequest(cropVarietyRequest);
        return cropVarietyRepository.save(cropVariety);
    }

    public CropVariety update(CropVarietyUpdateRequest cropVarietyUpdateRequest) {
        var cropVariety = getById(cropVarietyUpdateRequest.getId());
        var updatedCropVariety = cropVarietyMapper
                .cropVarietyFromCropVarietyUpdateRequest(cropVariety, cropVarietyUpdateRequest);
        return cropVarietyRepository.save(updatedCropVariety);
    }

    public void delete(Long id) {
        CropVariety cropVariety = getById(id);
        cropVarietyRepository.delete(cropVariety);
    }

}
