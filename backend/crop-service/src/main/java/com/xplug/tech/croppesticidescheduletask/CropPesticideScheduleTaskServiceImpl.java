package com.xplug.tech.croppesticidescheduletask;

import com.xplug.tech.crop.CropPesticideScheduleTask;
import com.xplug.tech.crop.CropPesticideScheduleTaskDao;
import com.xplug.tech.enums.TaskStatus;
import com.xplug.tech.utils.date.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public non-sealed class CropPesticideScheduleTaskServiceImpl implements CropPesticideScheduleTaskService {

    private final CropPesticideScheduleTaskDao cropPesticideScheduleTaskRepository;


    public CropPesticideScheduleTaskServiceImpl(CropPesticideScheduleTaskDao cropPesticideScheduleTaskRepository) {
        this.cropPesticideScheduleTaskRepository = cropPesticideScheduleTaskRepository;
    }

    @Override
    public List<CropPesticideScheduleTask> findByPendingTasksDueTomorrow() {
        LocalDateTime now = DateUtils.getCurrentTime();
//        LocalDateTime nextDay = now.plusDays(1);
        LocalDateTime nextDay = now.plusMinutes(5);
        return cropPesticideScheduleTaskRepository.findByTaskStatusAndTaskDateIsBetween(TaskStatus.PENDING, now, nextDay);
    }

    @Override
    public List<CropPesticideScheduleTask> findTasksInProgress() {
        return cropPesticideScheduleTaskRepository.findByTaskStatus(TaskStatus.IN_PROGRESS);
    }

    @Override
    public void saveAll(List<CropPesticideScheduleTask> pesticideScheduleTasks) {
        cropPesticideScheduleTaskRepository.saveAll(pesticideScheduleTasks);
    }

}
