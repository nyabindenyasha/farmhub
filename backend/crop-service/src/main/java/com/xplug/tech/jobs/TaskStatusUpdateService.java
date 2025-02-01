package com.xplug.tech.jobs;

import com.xplug.tech.crop.CropFertilizerScheduleTask;
import com.xplug.tech.crop.CropPesticideScheduleTask;
import com.xplug.tech.cropfertilizerscheduletask.CropFertilizerScheduleTaskService;
import com.xplug.tech.croppesticidescheduletask.CropPesticideScheduleTaskService;
import com.xplug.tech.enums.TaskStatus;
import com.xplug.tech.event.TaskReadyForExecutionEvent;
import com.xplug.tech.utils.TaskUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class TaskStatusUpdateService {

    private final CropFertilizerScheduleTaskService cropFertilizerScheduleTaskService;

    private final CropPesticideScheduleTaskService cropPesticideScheduleTaskService;

    private final ApplicationEventPublisher applicationEventPublisher;

    public TaskStatusUpdateService(CropFertilizerScheduleTaskService cropFertilizerScheduleTaskService, CropPesticideScheduleTaskService cropPesticideScheduleTaskService, ApplicationEventPublisher applicationEventPublisher) {
        this.cropFertilizerScheduleTaskService = cropFertilizerScheduleTaskService;
        this.cropPesticideScheduleTaskService = cropPesticideScheduleTaskService;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Transactional
    public void updateTaskStatuses() {

        log.info("### TaskStatusUpdateService: Updating task statuses");

        List<CropFertilizerScheduleTask> fertilizerScheduleTasksPending = cropFertilizerScheduleTaskService.findByPendingTasksDueTomorrow();

        log.info("### Fertilizer Schedule Tasks Pending : {}", fertilizerScheduleTasksPending );

        List<CropPesticideScheduleTask> pesticideScheduleTasksPending  = cropPesticideScheduleTaskService.findByPendingTasksDueTomorrow();

        log.info("### Pesticide Schedule Tasks Pending : {}", pesticideScheduleTasksPending );

        for (CropFertilizerScheduleTask fertilizerScheduleTask : fertilizerScheduleTasksPending ) {
            TaskStatus newStatus = TaskUtils.getTaskStatus(fertilizerScheduleTask.getTaskDate());
            fertilizerScheduleTask.setTaskStatus(newStatus);
            applicationEventPublisher.publishEvent(new TaskReadyForExecutionEvent(this,fertilizerScheduleTask, null));
        }

        for (CropPesticideScheduleTask pesticideScheduleTask : pesticideScheduleTasksPending ) {
            TaskStatus newStatus = TaskUtils.getTaskStatus(pesticideScheduleTask.getTaskDate());
            pesticideScheduleTask.setTaskStatus(newStatus);
            applicationEventPublisher.publishEvent(new TaskReadyForExecutionEvent(this, null, pesticideScheduleTask));
        }

        cropFertilizerScheduleTaskService.saveAll(fertilizerScheduleTasksPending);
        cropPesticideScheduleTaskService.saveAll(pesticideScheduleTasksPending);

        List<CropFertilizerScheduleTask> fertilizerScheduleTasksInProgress = cropFertilizerScheduleTaskService.findTasksInProgress();

        log.info("### Fertilizer Schedule Tasks In Progress: {}", fertilizerScheduleTasksInProgress);

        List<CropPesticideScheduleTask> pesticideScheduleTasksInProgress = cropPesticideScheduleTaskService.findByPendingTasksDueTomorrow();

        log.info("### Pesticide Schedule Tasks In Progress: {}", pesticideScheduleTasksInProgress);

        for (CropFertilizerScheduleTask fertilizerScheduleTask : fertilizerScheduleTasksInProgress) {
            TaskStatus newStatus = TaskUtils.getTaskStatus(fertilizerScheduleTask.getTaskDate());
            fertilizerScheduleTask.setTaskStatus(newStatus);
        }

        for (CropPesticideScheduleTask pesticideScheduleTask : pesticideScheduleTasksInProgress ) {
            TaskStatus newStatus = TaskUtils.getTaskStatus(pesticideScheduleTask.getTaskDate());
            pesticideScheduleTask.setTaskStatus(newStatus);
        }

        cropFertilizerScheduleTaskService.saveAll(fertilizerScheduleTasksInProgress);
        cropPesticideScheduleTaskService.saveAll(pesticideScheduleTasksInProgress);

    }
}

