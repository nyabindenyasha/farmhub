package com.xplug.tech.crop;

import com.xplug.tech.enums.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public interface CropPesticideScheduleTaskDao extends JpaRepository<CropPesticideScheduleTask, Long> {

    List<CropPesticideScheduleTask> findByTaskStatusAndTaskDateIsBetween(TaskStatus taskStatus, LocalDateTime startDate, LocalDateTime endDate);
    Set<CropPesticideScheduleTask> findByCropBatchId(Long cropBatchId);

    List<CropPesticideScheduleTask> findByTaskStatus(TaskStatus taskStatus);

}

