package com.xplug.tech.crop;

import com.xplug.tech.enums.FertilizerApplicationMethod;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString(exclude = {"cropProgram"})
@NoArgsConstructor
@AllArgsConstructor
public class CropFertilizerSchedule extends CropSchedule {

    @ManyToOne
    @JoinColumn(name = "fertilizer_id", nullable = false)
    private Fertilizer fertilizer;

    // create emmbedded class for rate, for unit
    private Integer rate; //grams per plant

    @Enumerated(EnumType.STRING)
    private FertilizerApplicationMethod applicationMethod;

    public static CropFertilizerScheduleBuilder builder() {
        return new CropFertilizerScheduleBuilder();
    }

    public static class CropFertilizerScheduleBuilder {
        protected CropFertilizerSchedule instance;

        protected CropFertilizerScheduleBuilder() {
            instance = new CropFertilizerSchedule();
        }

        public CropFertilizerScheduleBuilder id(Long id) {
            instance.setId(id);
            return this;
        }

        public CropFertilizerScheduleBuilder cropProgram(CropProgram cropProgram) {
            instance.setCropProgram(cropProgram);
            return this;
        }

        public CropFertilizerScheduleBuilder fertilizer(Fertilizer fertilizer) {
            instance.setFertilizer(fertilizer);
            return this;
        }

        public CropFertilizerScheduleBuilder stageOfGrowth(Period stageOfGrowth) {
            instance.setStageOfGrowth(stageOfGrowth);
            return this;
        }

        public CropFertilizerScheduleBuilder applicationInterval(Period applicationInterval) {
            instance.setApplicationInterval(applicationInterval);
            return this;
        }

        public CropFertilizerScheduleBuilder rate(Integer rate) {
            instance.setRate(rate);
            return this;
        }

        public CropFertilizerScheduleBuilder applicationMethod(FertilizerApplicationMethod applicationMethod) {
            instance.setApplicationMethod(applicationMethod);
            return this;
        }

        public CropFertilizerScheduleBuilder remarks(String remarks) {
            instance.setRemarks(remarks);
            return this;
        }

        public CropFertilizerSchedule build() {
            return instance;
        }
    }

}
