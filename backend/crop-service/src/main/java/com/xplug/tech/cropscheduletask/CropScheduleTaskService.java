package com.xplug.tech.cropscheduletask;

import com.xplug.tech.crop.CropScheduleTask;

import java.util.List;

public sealed interface CropScheduleTaskService permits CropScheduleTaskServiceImpl {

    List<CropScheduleTask> findByPendingTasksDueTomorrow();

    List<CropScheduleTask> findTasksInProgress();

//    void saveAllCropFertilizerSchedule(List<CropFertilizerScheduleTask> fertilizerScheduleTasks);
//
//    void saveAllCropPesticideSchedule(List<CropPesticideScheduleTask> pesticideScheduleTasks);

    void saveAll(List<CropScheduleTask> scheduleTasksPending);

}

