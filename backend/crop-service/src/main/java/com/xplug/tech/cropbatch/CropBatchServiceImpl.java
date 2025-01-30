package com.xplug.tech.cropbatch;

import com.xplug.tech.crop.CropBatch;
import com.xplug.tech.crop.CropBatchDao;
import com.xplug.tech.event.CropBatchCreatedEvent;
import com.xplug.tech.exception.ItemNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public non-sealed class CropBatchServiceImpl implements CropBatchService {

    private final CropBatchDao cropBatchRepository;

    private final CropBatchMapper cropBatchMapper;

    private final ApplicationEventPublisher applicationEventPublisher;

    public CropBatchServiceImpl(CropBatchDao cropBatchRepository, CropBatchMapper cropBatchMapper, ApplicationEventPublisher applicationEventPublisher) {
        this.cropBatchRepository = cropBatchRepository;
        this.cropBatchMapper = cropBatchMapper;
        this.applicationEventPublisher = applicationEventPublisher;
    }


    public List<CropBatch> getAll() {
        return cropBatchRepository.findAll();
    }

    public CropBatch getById(Long id) {
        return cropBatchRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException("Crop not found with Id: " + id));
    }

    public CropBatch create(CropBatchRequest cropBatchRequest) {
//        var cropBatch = cropBatchMapper.cropBatchFromCropBatchRequest(cropBatchRequest);
//        log.info("before saving ### CropBatch: {}", cropBatch);
//        var savedCropBatch = cropBatchRepository.save(cropBatch);



        var savedCropBatch = cropBatchMapper.cropBatchFromCropBatchRequest(cropBatchRequest);
        log.info("after saving ### SavedCropBatch: {}", savedCropBatch);
        applicationEventPublisher.publishEvent(new CropBatchCreatedEvent(this, savedCropBatch));
        return savedCropBatch;
    }

    public CropBatch update(CropBatchUpdateRequest cropBatchUpdateRequest) {
        var cropBatch = getById(cropBatchUpdateRequest.getId());
        var updatedCropBatch = cropBatchMapper.cropBatchFromCropBatchUpdateRequest(cropBatch, cropBatchUpdateRequest);
        return cropBatchRepository.save(updatedCropBatch);
    }

    public void delete(Long id) {
        CropBatch cropBatch = getById(id);
        cropBatchRepository.delete(cropBatch);
    }

}
