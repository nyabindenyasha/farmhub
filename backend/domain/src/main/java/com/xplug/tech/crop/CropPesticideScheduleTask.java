package com.xplug.tech.crop;

import com.xplug.tech.enums.TaskStatus;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@ToString(exclude = {"cropBatch"})
@NoArgsConstructor
@AllArgsConstructor
public class CropPesticideScheduleTask extends CropScheduleTask {

    @ManyToOne
    @JoinColumn(name = "crop_pesticide_schedule_id", nullable = false)
    private CropPesticideSchedule cropPesticideSchedule;

    public String getTaskName() {
        return getCropBatch().getCropFarmer().getCrop().getName() + " " + cropPesticideSchedule.getPesticide().getName() + " " + cropPesticideSchedule.getPesticide().getApplicationRate();
    }

    public static CropPesticideScheduleTask.CropPesticideScheduleTaskBuilder builder() {
        return new CropPesticideScheduleTask.CropPesticideScheduleTaskBuilder();
    }

    public static class CropPesticideScheduleTaskBuilder {
        protected CropPesticideScheduleTask instance;

        protected CropPesticideScheduleTaskBuilder() {
            instance = new CropPesticideScheduleTask();
        }

        public CropPesticideScheduleTask.CropPesticideScheduleTaskBuilder id(Long id) {
            instance.setId(id);
            return this;
        }

        public CropPesticideScheduleTask.CropPesticideScheduleTaskBuilder cropBatch(CropBatch cropBatch) {
            instance.setCropBatch(cropBatch);
            return this;
        }

        public CropPesticideScheduleTask.CropPesticideScheduleTaskBuilder cropPesticideSchedule(CropPesticideSchedule cropPesticideSchedule) {
            instance.setCropPesticideSchedule(cropPesticideSchedule);
            return this;
        }

        public CropPesticideScheduleTask.CropPesticideScheduleTaskBuilder isCompleted(Boolean isCompleted) {
            instance.setIsCompleted(isCompleted);
            return this;
        }

        public CropPesticideScheduleTask.CropPesticideScheduleTaskBuilder completionDate(LocalDate completionDate) {
            instance.setCompletionDate(completionDate);
            return this;
        }

        public CropPesticideScheduleTask.CropPesticideScheduleTaskBuilder taskRemark(String taskRemarks) {
            instance.setTaskRemarks(taskRemarks);
            return this;
        }

        public CropPesticideScheduleTask.CropPesticideScheduleTaskBuilder taskStatus(TaskStatus taskStatus) {
            instance.setTaskStatus(taskStatus);
            return this;
        }

        public CropPesticideScheduleTask.CropPesticideScheduleTaskBuilder taskDate(LocalDateTime taskDate) {
            instance.setTaskDate(taskDate);
            return this;
        }

        public CropPesticideScheduleTask build() {
            return instance;
        }
    }

}
