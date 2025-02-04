package com.xplug.tech.jobs;

import com.xplug.tech.crop.CropScheduleTask;
import com.xplug.tech.cropscheduletask.CropScheduleTaskService;
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

    private final CropScheduleTaskService cropScheduleTaskService;

    private final ApplicationEventPublisher applicationEventPublisher;

    public TaskStatusUpdateService(CropScheduleTaskService cropScheduleTaskService, ApplicationEventPublisher applicationEventPublisher) {
        this.cropScheduleTaskService = cropScheduleTaskService;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Transactional
    public void updateTaskStatuses() {

        log.info("### TaskStatusUpdateService: Updating task statuses");

        List<CropScheduleTask> scheduleTasksPending = cropScheduleTaskService.findByPendingTasksDueTomorrow();

        log.info("### Schedule Tasks Pending : {}", scheduleTasksPending );

//        List<CropPesticideScheduleTask> pesticideScheduleTasksPending  = cropPesticideScheduleTaskService.findByPendingTasksDueTomorrow();

//        log.info("### Pesticide Schedule Tasks Pending : {}", pesticideScheduleTasksPending );

        for (CropScheduleTask cropScheduleTask : scheduleTasksPending ) {
            TaskStatus newStatus = TaskUtils.getTaskStatus(cropScheduleTask, cropScheduleTask.getTaskDate());
            cropScheduleTask.setTaskStatus(newStatus);
            applicationEventPublisher.publishEvent(new TaskReadyForExecutionEvent(this, cropScheduleTask));
        }

//        for (CropFertilizerScheduleTask fertilizerScheduleTask : fertilizerScheduleTasksPending ) {
//            TaskStatus newStatus = TaskUtils.getTaskStatus(fertilizerScheduleTask.getTaskDate());
//            fertilizerScheduleTask.setTaskStatus(newStatus);
//            applicationEventPublisher.publishEvent(new TaskReadyForExecutionEvent(this,fertilizerScheduleTask, null));
//        }
//
//        for (CropPesticideScheduleTask pesticideScheduleTask : pesticideScheduleTasksPending ) {
//            TaskStatus newStatus = TaskUtils.getTaskStatus(pesticideScheduleTask.getTaskDate());
//            pesticideScheduleTask.setTaskStatus(newStatus);
//            applicationEventPublisher.publishEvent(new TaskReadyForExecutionEvent(this, null, pesticideScheduleTask));
//        }

        cropScheduleTaskService.saveAll(scheduleTasksPending);

        List<CropScheduleTask> scheduleTasksInProgress = cropScheduleTaskService.findTasksInProgress();

        log.info("### Schedule Tasks In Progress: {}", scheduleTasksInProgress);

//        List<CropPesticideScheduleTask> pesticideScheduleTasksInProgress = cropPesticideScheduleTaskService.findByPendingTasksDueTomorrow();
//
//        log.info("### Pesticide Schedule Tasks In Progress: {}", pesticideScheduleTasksInProgress);

        for (CropScheduleTask cropScheduleTask : scheduleTasksInProgress ) {
            TaskStatus newStatus = TaskUtils.getTaskStatus(cropScheduleTask, cropScheduleTask.getTaskDate());
            cropScheduleTask.setTaskStatus(newStatus);
        }

//        for (CropFertilizerScheduleTask fertilizerScheduleTask : fertilizerScheduleTasksInProgress) {
//            TaskStatus newStatus = TaskUtils.getTaskStatus(fertilizerScheduleTask.getTaskDate());
//            fertilizerScheduleTask.setTaskStatus(newStatus);
//        }
//
//        for (CropPesticideScheduleTask pesticideScheduleTask : pesticideScheduleTasksInProgress ) {
//            TaskStatus newStatus = TaskUtils.getTaskStatus(pesticideScheduleTask.getTaskDate());
//            pesticideScheduleTask.setTaskStatus(newStatus);
//        }

        cropScheduleTaskService.saveAll(scheduleTasksInProgress);

    }

}

