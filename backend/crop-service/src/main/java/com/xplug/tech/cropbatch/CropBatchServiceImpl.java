package com.xplug.tech.cropbatch;

import com.xplug.tech.crop.CropBatch;
import com.xplug.tech.crop.CropBatchDao;
import com.xplug.tech.crop.CropScheduleTask;
import com.xplug.tech.enums.TaskStatus;
import com.xplug.tech.event.CropBatchCreatedEvent;
import com.xplug.tech.exception.ItemAlreadyExistsException;
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

    @Override
    public List<CropBatch> getByFarmer(Long userAccountId) {
        return cropBatchRepository.findByUserAccountId(userAccountId);
    }

    public CropBatch getById(Long id) {
        return cropBatchRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException("Crop not found with Id: " + id));
    }

    public CropBatch create(CropBatchRequest cropBatchRequest) {

        var optionalCropFarmer = cropBatchRepository
                .findByCropProgramIdAndUserAccountIdAndAndDateOfTransplant(cropBatchRequest.getCropProgramId(), cropBatchRequest.getFarmerId(), cropBatchRequest.getDateOfTransplant());
        if (optionalCropFarmer.isPresent()) {
            throw new ItemAlreadyExistsException("CropBatch with same farmer and crop already exists");
        }

        CropBatch cropBatch = cropBatchMapper.cropBatchFromCropBatchRequest(cropBatchRequest);
        CropBatch savedCropBatch = cropBatchRepository.save(cropBatch);
        savedCropBatch.getFertilizerScheduleTasks().forEach(task -> task.setCropBatch(savedCropBatch));
        savedCropBatch.getPesticideScheduleTasks().forEach(task -> task.setCropBatch(savedCropBatch));
//        savedCropBatch.getIrrigationScheduleTasks().forEach(task -> task.setCropBatch(savedCropBatch));
//        savedCropBatch.getWeedingScheduleTasks().forEach(task -> task.setCropBatch(savedCropBatch));
        cropBatchRepository.save(savedCropBatch);
        applicationEventPublisher.publishEvent(new CropBatchCreatedEvent(this, savedCropBatch));
        return savedCropBatch;
    }

    public CropBatch update(CropBatchUpdateRequest cropBatchUpdateRequest) {
        var cropBatch = getById(cropBatchUpdateRequest.getId());
        return cropBatchRepository.save(cropBatch);
    }

    @Override
    public CropBatch updateTask(CropBatchTaskUpdateRequest cropBatchTaskUpdateRequest) {
        CropBatch cropBatch = getById(cropBatchTaskUpdateRequest.getBatchId());

        CropScheduleTask cropScheduleTask = cropBatch.getCropScheduleTasks().stream()
                .filter(schedule -> schedule.getId().equals(cropBatchTaskUpdateRequest.getCropScheduleTask().getId()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Schedule not found"));

        if (cropScheduleTask.getTaskStatus().equals(TaskStatus.PENDING) || cropScheduleTask.getTaskStatus().equals(TaskStatus.OVERDUE) || cropScheduleTask.getTaskStatus().equals(TaskStatus.COMPLETED)) {
            cropScheduleTask.setTaskRemarks(cropBatchTaskUpdateRequest.getCropScheduleTask().getTaskRemarks());
        }

//        if (cropScheduleTask.getTaskStatus().equals(TaskStatus.COMPLETED)) {
//            cropScheduleTask.setTaskRemarks(cropBatchTaskUpdateRequest.getCropScheduleTask().getTaskRemarks());
//        }

        else if (cropScheduleTask.getTaskStatus().equals(TaskStatus.IN_PROGRESS)) {
            cropScheduleTask.setIsCompleted(cropBatchTaskUpdateRequest.getCropScheduleTask().getIsCompleted());
            cropScheduleTask.setCompletionDate(cropBatchTaskUpdateRequest.getCropScheduleTask().getCompletionDate());
            cropScheduleTask.setTaskRemarks(cropBatchTaskUpdateRequest.getCropScheduleTask().getTaskRemarks());
            if (Boolean.TRUE.equals(cropBatchTaskUpdateRequest.getCropScheduleTask().getIsCompleted()))
                cropScheduleTask.setTaskStatus(TaskStatus.COMPLETED);
        }

        return cropBatchRepository.save(cropBatch);
    }

    public void delete(Long id) {
        CropBatch cropBatch = getById(id);
        cropBatchRepository.delete(cropBatch);
    }

}
