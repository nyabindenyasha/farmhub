package com.xplug.tech.cropfertilizerscheduletask;

//import com.xplug.tech.crop.CropFertilizerScheduleTask;
//import com.xplug.tech.crop.CropFertilizerScheduleTaskDao;
//import com.xplug.tech.enums.TaskStatus;
//import com.xplug.tech.utils.date.DateUtils;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//
//@Slf4j
//@Service
//public non-sealed class CropFertilizerScheduleTaskServiceImpl implements CropFertilizerScheduleTaskService {
//
//    private final CropFertilizerScheduleTaskDao cropFertilizerScheduleTaskRepository;
//
//    public CropFertilizerScheduleTaskServiceImpl(CropFertilizerScheduleTaskDao cropFertilizerScheduleTaskRepository) {
//        this.cropFertilizerScheduleTaskRepository = cropFertilizerScheduleTaskRepository;
//    }
//
//
//    @Override
//    public List<CropFertilizerScheduleTask> findByPendingTasksDueTomorrow() {
//        LocalDateTime now = DateUtils.getCurrentTime();
////        LocalDateTime nextDay = now.plusDays(1);
//        LocalDateTime nextDay = now.plusMinutes(5);
//        return cropFertilizerScheduleTaskRepository.findByTaskStatusAndTaskDateIsBetween(TaskStatus.PENDING, now, nextDay);
//    }
//
//    @Override
//    public List<CropFertilizerScheduleTask> findTasksInProgress() {
//        return cropFertilizerScheduleTaskRepository.findByTaskStatus(TaskStatus.IN_PROGRESS);
//    }
//
//    @Override
//    public void saveAll(List<CropFertilizerScheduleTask> fertilizerScheduleTasks) {
//        cropFertilizerScheduleTaskRepository.saveAll(fertilizerScheduleTasks);
//    }
//
//}
