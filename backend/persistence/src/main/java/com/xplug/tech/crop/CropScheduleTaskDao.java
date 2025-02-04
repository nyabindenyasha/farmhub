package com.xplug.tech.crop;

import com.xplug.tech.enums.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public interface CropScheduleTaskDao extends JpaRepository<CropScheduleTask, Long> {

    List<CropScheduleTask> findByTaskStatusAndTaskDateIsBetween(TaskStatus taskStatus, LocalDateTime startDate, LocalDateTime endDate);

    Set<CropScheduleTask> findByCropBatchId(Long cropBatchId);

    List<CropScheduleTask> findByTaskStatus(TaskStatus taskStatus);

}
