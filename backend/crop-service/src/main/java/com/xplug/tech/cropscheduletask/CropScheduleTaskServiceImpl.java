package com.xplug.tech.cropscheduletask;

import com.xplug.tech.crop.CropScheduleTask;
import com.xplug.tech.crop.CropScheduleTaskDao;
import com.xplug.tech.enums.TaskStatus;
import com.xplug.tech.utils.date.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Slf4j
@Service
public non-sealed class CropScheduleTaskServiceImpl implements CropScheduleTaskService {

    private final CropScheduleTaskDao cropScheduleTaskRepository;

    public CropScheduleTaskServiceImpl(CropScheduleTaskDao cropScheduleTaskRepository) {
        this.cropScheduleTaskRepository = cropScheduleTaskRepository;
    }

//    private final CropFertilizerScheduleTaskDao cropFertilizerScheduleTaskRepository;
//
//    private final CropPesticideScheduleTaskDao cropPesticideScheduleTaskRepository;
//
//    public CropScheduleTaskServiceImpl(CropScheduleTaskDao cropScheduleTaskRepository, CropFertilizerScheduleTaskDao cropFertilizerScheduleTaskRepository, CropPesticideScheduleTaskDao cropPesticideScheduleTaskRepository) {
//        this.cropScheduleTaskRepository = cropScheduleTaskRepository;
//        this.cropFertilizerScheduleTaskRepository = cropFertilizerScheduleTaskRepository;
//        this.cropPesticideScheduleTaskRepository = cropPesticideScheduleTaskRepository;
//    }


    @Override
    public List<CropScheduleTask> findByPendingTasksDueTomorrow() {
        LocalDateTime now = DateUtils.getCurrentTime();
//        LocalDateTime nextDay = now.plusDays(1);
        LocalDateTime nextDay = now.plusMinutes(5);
        return cropScheduleTaskRepository.findByTaskStatusAndTaskDateIsBetween(TaskStatus.PENDING, now, nextDay);
    }

    @Override
    public List<CropScheduleTask> findTasksInProgress() {
        return cropScheduleTaskRepository.findByTaskStatus(TaskStatus.IN_PROGRESS);
    }



//    @Override
//    public void saveAllCropFertilizerSchedule(List<CropFertilizerScheduleTask> fertilizerScheduleTasks) {
//        cropFertilizerScheduleTaskRepository.saveAll(fertilizerScheduleTasks);
//    }
//
//    @Override
//    public void saveAllCropPesticideSchedule(List<CropPesticideScheduleTask> pesticideScheduleTasks) {
//        cropPesticideScheduleTaskRepository.saveAll(pesticideScheduleTasks);
//    }

//    @Override
//    public void saveAll(List<CropScheduleTask> scheduleTasksPending) {
//        List<CropFertilizerScheduleTask> cropFertilizerScheduleTasks = scheduleTasksPending.stream()
//                .filter(CropFertilizerScheduleTask.class::isInstance)
//                .map(CropFertilizerScheduleTask.class::cast)
//                .toList();
//        if(!cropFertilizerScheduleTasks.isEmpty()) {
//            saveAllCropFertilizerSchedule(cropFertilizerScheduleTasks);
//        }
//        List<CropPesticideScheduleTask> cropPesticideScheduleTasks = scheduleTasksPending.stream()
//                .filter(CropPesticideScheduleTask.class::isInstance)
//                .map(CropPesticideScheduleTask.class::cast)
//                .toList();
//        if(!cropPesticideScheduleTasks.isEmpty()) {
//            saveAllCropPesticideSchedule(cropPesticideScheduleTasks);
//        }
//    }

    @Override
    public void saveAll(List<CropScheduleTask> scheduleTasksPending) {
        cropScheduleTaskRepository.saveAll(scheduleTasksPending);
    };

}
