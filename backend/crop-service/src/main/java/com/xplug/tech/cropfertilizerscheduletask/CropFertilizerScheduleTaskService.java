package com.xplug.tech.cropfertilizerscheduletask;

import com.xplug.tech.crop.CropFertilizerScheduleTask;

import java.util.List;

public sealed interface CropFertilizerScheduleTaskService permits CropFertilizerScheduleTaskServiceImpl {

    List<CropFertilizerScheduleTask> findByPendingTasksDueTomorrow();

    List<CropFertilizerScheduleTask> findTasksInProgress();

    void saveAll(List<CropFertilizerScheduleTask> fertilizerScheduleTasks);

}

