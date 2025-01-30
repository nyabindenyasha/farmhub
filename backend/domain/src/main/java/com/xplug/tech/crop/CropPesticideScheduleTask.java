package com.xplug.tech.crop;

import com.xplug.tech.enums.PesticideApplicationMethod;
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
public class CropPesticideScheduleTask extends CropPesticideSchedule {

    private Boolean isCompleted;

    private LocalDate completionDate;

    private String taskRemarks;

    @ManyToOne
    @JoinColumn(name = "crop_batch_id")
    private CropBatch cropBatch;

    public static CropPesticideScheduleTaskBuilder builder() {
        return new CropPesticideScheduleTaskBuilder();
    }

    public static class CropPesticideScheduleTaskBuilder extends CropPesticideScheduleBuilder {
        private CropPesticideScheduleTask taskInstance;

        protected CropPesticideScheduleTaskBuilder() {
            super();
            taskInstance = new CropPesticideScheduleTask();
            instance = taskInstance;
        }

        public CropPesticideScheduleTaskBuilder isCompleted(Boolean isCompleted) {
            taskInstance.setIsCompleted(isCompleted);
            return this;
        }

        public CropPesticideScheduleTaskBuilder completionDate(LocalDate completionDate) {
            taskInstance.setCompletionDate(completionDate);
            return this;
        }

        public CropPesticideScheduleTaskBuilder taskRemarks(String taskRemarks) {
            taskInstance.setTaskRemarks(taskRemarks);
            return this;
        }

        public CropPesticideScheduleTaskBuilder cropBatch(CropBatch cropBatch) {
            taskInstance.setCropBatch(cropBatch);
            return this;
        }

        @Override
        public CropPesticideScheduleTaskBuilder id(Long id) {
            super.id(id);
            return this;
        }

        @Override
        public CropPesticideScheduleTaskBuilder cropSchedule(CropSchedule cropSchedule) {
            super.cropSchedule(cropSchedule);
            return this;
        }

        @Override
        public CropPesticideScheduleTaskBuilder pesticide(Pesticide pesticide) {
            super.pesticide(pesticide);
            return this;
        }

        @Override
        public CropPesticideScheduleTaskBuilder stageOfGrowth(Period stageOfGrowth) {
            super.stageOfGrowth(stageOfGrowth);
            return this;
        }

        @Override
        public CropPesticideScheduleTaskBuilder applicationInterval(Period applicationInterval) {
            super.applicationInterval(applicationInterval);
            return this;
        }

        @Override
        public CropPesticideScheduleTaskBuilder applicationMethod(PesticideApplicationMethod applicationMethod) {
            super.applicationMethod(applicationMethod);
            return this;
        }

        @Override
        public CropPesticideScheduleTaskBuilder remarks(String remarks) {
            super.remarks(remarks);
            return this;
        }

        @Override
        public CropPesticideScheduleTask build() {
            return taskInstance;
        }
    }
}
