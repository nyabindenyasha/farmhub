package com.xplug.tech.crop;

import com.xplug.tech.enums.FertilizerApplicationMethod;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class CropFertilizerScheduleTask extends CropFertilizerSchedule {

    private Boolean isCompleted;

    private LocalDate completionDate;

    private String taskRemarks;

    @ManyToOne
    @JoinColumn(name = "crop_batch_id")
    private CropBatch cropBatch;

    public static CropFertilizerScheduleTaskBuilder builder() {
        return new CropFertilizerScheduleTaskBuilder();
    }

    public static class CropFertilizerScheduleTaskBuilder extends CropFertilizerScheduleBuilder {
        private CropFertilizerScheduleTask taskInstance;

        protected CropFertilizerScheduleTaskBuilder() {
            super();
            taskInstance = new CropFertilizerScheduleTask();
            instance = taskInstance;
        }

        public CropFertilizerScheduleTaskBuilder isCompleted(Boolean isCompleted) {
            taskInstance.setIsCompleted(isCompleted);
            return this;
        }

        public CropFertilizerScheduleTaskBuilder completionDate(LocalDate completionDate) {
            taskInstance.setCompletionDate(completionDate);
            return this;
        }

        public CropFertilizerScheduleTaskBuilder taskRemarks(String taskRemarks) {
            taskInstance.setTaskRemarks(taskRemarks);
            return this;
        }

        public CropFertilizerScheduleTaskBuilder cropBatch(CropBatch cropBatch) {
            taskInstance.setCropBatch(cropBatch);
            return this;
        }

        @Override
        public CropFertilizerScheduleTaskBuilder id(Long id) {
            super.id(id);
            return this;
        }

        @Override
        public CropFertilizerScheduleTaskBuilder cropSchedule(CropSchedule cropSchedule) {
            super.cropSchedule(cropSchedule);
            return this;
        }

        @Override
        public CropFertilizerScheduleTaskBuilder fertilizer(Fertilizer fertilizer) {
            super.fertilizer(fertilizer);
            return this;
        }

        @Override
        public CropFertilizerScheduleTaskBuilder stageOfGrowth(Period stageOfGrowth) {
            super.stageOfGrowth(stageOfGrowth);
            return this;
        }

        @Override
        public CropFertilizerScheduleTaskBuilder applicationInterval(Period applicationInterval) {
            super.applicationInterval(applicationInterval);
            return this;
        }

        @Override
        public CropFertilizerScheduleTaskBuilder rate(Integer rate) {
            super.rate(rate);
            return this;
        }

        @Override
        public CropFertilizerScheduleTaskBuilder applicationMethod(FertilizerApplicationMethod applicationMethod) {
            super.applicationMethod(applicationMethod);
            return this;
        }

        @Override
        public CropFertilizerScheduleTaskBuilder remarks(String remarks) {
            super.remarks(remarks);
            return this;
        }

        @Override
        public CropFertilizerScheduleTask build() {
            return taskInstance;
        }
    }
}
