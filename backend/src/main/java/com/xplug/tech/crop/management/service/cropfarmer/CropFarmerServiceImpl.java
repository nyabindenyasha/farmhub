package com.xplug.tech.crop.management.service.cropfarmer;

import com.xplug.tech.crop.management.dao.CropFarmerDao;
import com.xplug.tech.crop.management.domain.CropFarmer;
import com.xplug.tech.crop.management.exceptions.ItemAlreadyExistsException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public non-sealed class CropFarmerServiceImpl implements CropFarmerService {

    private final CropFarmerDao cropFarmerRepository;

    private final CropFarmerMapper cropFarmerMapper;

    public CropFarmerServiceImpl(CropFarmerDao cropFarmerRepository, CropFarmerMapper cropFarmerMapper) {
        this.cropFarmerRepository = cropFarmerRepository;
        this.cropFarmerMapper = cropFarmerMapper;
    }


    public List<CropFarmer> getAll() {
        return cropFarmerRepository.findAll();
    }

    public CropFarmer getById(Long id) {
        return cropFarmerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CropFarmer not found with ID: " + id));
    }

    public CropFarmer create(CropFarmerRequest cropFarmerRequest) {
        var optionalCrop = cropFarmerRepository
                .findByCropIdAndUserAccountIdAndAndDateOfTransplant(cropFarmerRequest.getCropId(), cropFarmerRequest.getFarmerId(), cropFarmerRequest.getDateOfTransplant());
        if (optionalCrop.isPresent()) {
            throw new ItemAlreadyExistsException("CropFarmer with same farmer and crop already exists");
        }
        var cropFarmer = cropFarmerMapper.cropFarmerFromCropFarmerRequest(cropFarmerRequest);
        return cropFarmerRepository.save(cropFarmer);
    }

    public CropFarmer update(CropFarmerUpdateRequest cropFarmerUpdateRequest) {
        var cropFarmer = getById(cropFarmerUpdateRequest.getId());
        var updatedCropFarmer = cropFarmerMapper.cropFarmerFromCropFarmerUpdateRequest(cropFarmer, cropFarmerUpdateRequest);
        return cropFarmerRepository.save(updatedCropFarmer);
    }

    public void delete(Long id) {
        CropFarmer crop = getById(id);
        cropFarmerRepository.delete(crop);
    }

}
