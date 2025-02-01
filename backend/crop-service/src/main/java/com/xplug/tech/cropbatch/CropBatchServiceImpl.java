package com.xplug.tech.cropbatch;

import com.xplug.tech.crop.*;
import com.xplug.tech.enums.TaskStatus;
import com.xplug.tech.event.CropBatchCreatedEvent;
import com.xplug.tech.exception.ItemNotFoundException;
import com.xplug.tech.utils.PeriodUtils;
import com.xplug.tech.utils.TaskUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Slf4j
@Service
public non-sealed class CropBatchServiceImpl implements CropBatchService {

    private final CropBatchDao cropBatchRepository;

    private final CropFertilizerScheduleTaskDao cropFertilizerScheduleTaskRepository;

    private final CropPesticideScheduleTaskDao cropPesticideScheduleTaskRepository;

    private final ApplicationEventPublisher applicationEventPublisher;

    public CropBatchServiceImpl(CropBatchDao cropBatchRepository, CropFertilizerScheduleTaskDao cropFertilizerScheduleTaskRepository, CropPesticideScheduleTaskDao cropPesticideScheduleTaskRepository, ApplicationEventPublisher applicationEventPublisher) {
        this.cropBatchRepository = cropBatchRepository;
        this.cropFertilizerScheduleTaskRepository = cropFertilizerScheduleTaskRepository;
        this.cropPesticideScheduleTaskRepository = cropPesticideScheduleTaskRepository;
        this.applicationEventPublisher = applicationEventPublisher;
    }


    public List<CropBatch> getAll() {
        return cropBatchRepository.findAll();
    }

    public CropBatch getById(Long id) {
        var cropBatch = cropBatchRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException("Crop not found with Id: " + id));
        cropBatch.setFertilizerScheduleTasks(cropFertilizerScheduleTaskRepository.findByCropBatchId(id));
        cropBatch.setPesticideScheduleTasks(cropPesticideScheduleTaskRepository.findByCropBatchId(id));
        return cropBatch;
    }

    public CropBatch create(CropFarmer cropFarmer) {

        // Step 2: Create CropBatch
        CropBatch cropBatch = CropBatch.builder()
                .cropFarmer(cropFarmer)
                .build();

        // Step 4: Save CropBatch (Cascade will save Tasks)
        CropBatch savedCropBatch = cropBatchRepository.save(cropBatch);

        // Step 3: Create Tasks from CropPesticideSchedule
        Set<CropPesticideSchedule> pesticideSchedules = cropFarmer.getCropSchedule().getPesticideScheduleList();

        for (CropPesticideSchedule pesticideSchedule : pesticideSchedules) {
            LocalDateTime taskDate = PeriodUtils.addPeriod(savedCropBatch.getCropFarmer().getDateOfTransplant(), pesticideSchedule.getStageOfGrowth());
            CropPesticideScheduleTask pesticideScheduleTask = CropPesticideScheduleTask.builder()
                    .cropPesticideSchedule(pesticideSchedule)
                    .cropBatch(savedCropBatch)
                    .isCompleted(false)
                    .taskStatus(TaskUtils.getTaskStatus(taskDate))
                    .taskDate(taskDate)
                    .build();
            cropPesticideScheduleTaskRepository.save(pesticideScheduleTask);
        }

        Set<CropFertilizerSchedule> fertilizerSchedules = cropFarmer.getCropSchedule().getFertilizerScheduleList();

        for (CropFertilizerSchedule fertilizerSchedule : fertilizerSchedules) {
            LocalDateTime taskDate = PeriodUtils.addPeriod(savedCropBatch.getCropFarmer().getDateOfTransplant(), fertilizerSchedule.getStageOfGrowth());
            CropFertilizerScheduleTask fertilizerScheduleTask = CropFertilizerScheduleTask.builder()
                    .cropFertilizerSchedule(fertilizerSchedule)
                    .cropBatch(savedCropBatch)
                    .isCompleted(false)
                    .taskStatus(TaskUtils.getTaskStatus(taskDate))
                    .taskDate(taskDate)
                    .build();
            cropFertilizerScheduleTaskRepository.save(fertilizerScheduleTask);
        }
        applicationEventPublisher.publishEvent(new CropBatchCreatedEvent(this, savedCropBatch));
        return savedCropBatch;
    }

    public CropBatch update(CropBatchUpdateRequest cropBatchUpdateRequest) {
        var cropBatch = getById(cropBatchUpdateRequest.getId());
        return cropBatchRepository.save(cropBatch);
    }

    public void delete(Long id) {
        CropBatch cropBatch = getById(id);
        cropBatchRepository.delete(cropBatch);
    }

}
