package com.xplug.tech.crop;

import com.xplug.tech.enums.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public interface CropFertilizerScheduleTaskDao extends JpaRepository<CropFertilizerScheduleTask, Long> {

    List<CropFertilizerScheduleTask> findByTaskStatusAndTaskDateIsBetween(TaskStatus taskStatus, LocalDateTime startDate, LocalDateTime endDate);

    Set<CropFertilizerScheduleTask> findByCropBatchId(Long cropBatchId);

    List<CropFertilizerScheduleTask> findByTaskStatus(TaskStatus taskStatus);

}
