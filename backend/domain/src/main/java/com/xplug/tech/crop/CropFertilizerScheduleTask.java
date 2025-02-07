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
public class CropFertilizerScheduleTask extends CropScheduleTask {

    @ManyToOne
    @JoinColumn(name = "crop_fertilizer_schedule_id", nullable = false)
    private CropFertilizerSchedule cropFertilizerSchedule;

    public String getTaskName() {
        return getCropBatch().getCropProgram().getCrop().getName() + " " + cropFertilizerSchedule.getFertilizer().getName() + " " + cropFertilizerSchedule.getRate();
    }

    public static CropFertilizerScheduleTask.CropFertilizerScheduleTaskBuilder builder() {
        return new CropFertilizerScheduleTask.CropFertilizerScheduleTaskBuilder();
    }

    public static class CropFertilizerScheduleTaskBuilder {
        protected CropFertilizerScheduleTask instance;

        protected CropFertilizerScheduleTaskBuilder() {
            instance = new CropFertilizerScheduleTask();
        }

        public CropFertilizerScheduleTask.CropFertilizerScheduleTaskBuilder id(Long id) {
            instance.setId(id);
            return this;
        }

        public CropFertilizerScheduleTask.CropFertilizerScheduleTaskBuilder cropBatch(CropBatch cropBatch) {
            instance.setCropBatch(cropBatch);
            return this;
        }

        public CropFertilizerScheduleTask.CropFertilizerScheduleTaskBuilder cropFertilizerSchedule(CropFertilizerSchedule cropFertilizerSchedule) {
            instance.setCropFertilizerSchedule(cropFertilizerSchedule);
            return this;
        }

        public CropFertilizerScheduleTask.CropFertilizerScheduleTaskBuilder isCompleted(Boolean isCompleted) {
            instance.setIsCompleted(isCompleted);
            return this;
        }

        public CropFertilizerScheduleTask.CropFertilizerScheduleTaskBuilder completionDate(LocalDate completionDate) {
            instance.setCompletionDate(completionDate);
            return this;
        }

        public CropFertilizerScheduleTask.CropFertilizerScheduleTaskBuilder taskRemark(String taskRemarks) {
            instance.setTaskRemarks(taskRemarks);
            return this;
        }

        public CropFertilizerScheduleTask.CropFertilizerScheduleTaskBuilder taskStatus(TaskStatus taskStatus) {
            instance.setTaskStatus(taskStatus);
            return this;
        }

        public CropFertilizerScheduleTask.CropFertilizerScheduleTaskBuilder taskDate(LocalDateTime taskDate) {
            instance.setTaskDate(taskDate);
            return this;
        }

        public CropFertilizerScheduleTask build() {
            return instance;
        }
    }

}
