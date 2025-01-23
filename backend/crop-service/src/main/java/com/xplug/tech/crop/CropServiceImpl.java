package com.xplug.tech.crop;

import com.xplug.tech.event.CropCreatedEvent;
import com.xplug.tech.exception.ItemAlreadyExistsException;
import com.xplug.tech.exception.ItemNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public non-sealed class CropServiceImpl implements CropService {

    private final CropDao cropRepository;

    private final CropMapper cropMapper;

    private final ApplicationEventPublisher applicationEventPublisher;

    public CropServiceImpl(CropDao cropRepository, CropMapper cropMapper, ApplicationEventPublisher applicationEventPublisher) {
        this.cropRepository = cropRepository;
        this.cropMapper = cropMapper;
        this.applicationEventPublisher = applicationEventPublisher;
    }


    public List<Crop> getAll() {
        return cropRepository.findAll();
    }

    public Crop getById(Long id) {
        return cropRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException("Crop not found with Id: " + id));
    }

    @Override
    public Crop getByName(String name) {
        return cropRepository.findByNameContainsIgnoreCase(name)
                .orElseThrow(() -> new ItemNotFoundException("Crop not found with Name: " + name));
    }

    public Crop create(CropRequest cropRequest) {
        var optionalCrop = cropRepository.findByNameContainsIgnoreCase(cropRequest.getName());
        if (optionalCrop.isPresent()) {
            throw new ItemAlreadyExistsException("Crop with name: " + cropRequest.getName() + " already exists");
        }
        return saveCrop(cropRequest);
    }

    private Crop saveCrop(CropRequest cropRequest) {
        var crop = cropMapper.cropFromCropRequest(cropRequest);
        var savedCrop = cropRepository.save(crop);
        applicationEventPublisher.publishEvent(new CropCreatedEvent(this, savedCrop));
        return savedCrop;
    }

    @Override
    public Crop initialize(CropRequest cropRequest) {
        var optionalCrop = cropRepository.findByNameContainsIgnoreCase(cropRequest.getName());
        if (optionalCrop.isPresent()) {
            log.info("### Crop Found {}", optionalCrop.get());
            return optionalCrop.get();
        }
        return saveCrop(cropRequest);
    }

    public Crop update(CropUpdateRequest cropUpdateRequest) {
        var crop = getById(cropUpdateRequest.getId());
        var updatedCrop = cropMapper.cropFromCropUpdateRequest(crop, cropUpdateRequest);
        return cropRepository.save(updatedCrop);
    }

    public void delete(Long id) {
        Crop crop = getById(id);
        cropRepository.delete(crop);
    }

}
