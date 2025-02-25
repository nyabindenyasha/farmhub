package com.xplug.tech.crop;

import com.xplug.tech.enums.PesticideApplicationMethod;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString(exclude = {"cropProgram"})
@NoArgsConstructor
@AllArgsConstructor
public class CropPesticideSchedule extends CropSchedule {

    @ManyToOne
    @JoinColumn(name = "pesticide_id", nullable = false)
    private Pesticide pesticide;

    @Enumerated(EnumType.STRING)
    private PesticideApplicationMethod applicationMethod;

    public static CropPesticideScheduleBuilder builder() {
        return new CropPesticideScheduleBuilder();
    }

    public static class CropPesticideScheduleBuilder {
        protected CropPesticideSchedule instance;

        protected CropPesticideScheduleBuilder() {
            instance = new CropPesticideSchedule();
        }

        public CropPesticideScheduleBuilder id(Long id) {
            instance.setId(id);
            return this;
        }

        public CropPesticideScheduleBuilder cropProgram(CropProgram cropProgram) {
            instance.setCropProgram(cropProgram);
            return this;
        }

        public CropPesticideScheduleBuilder pesticide(Pesticide pesticide) {
            instance.setPesticide(pesticide);
            return this;
        }

        public CropPesticideScheduleBuilder stageOfGrowth(Period stageOfGrowth) {
            instance.setStageOfGrowth(stageOfGrowth);
            return this;
        }

        public CropPesticideScheduleBuilder applicationInterval(Period applicationInterval) {
            instance.setApplicationInterval(applicationInterval);
            return this;
        }

        public CropPesticideScheduleBuilder applicationMethod(PesticideApplicationMethod applicationMethod) {
            instance.setApplicationMethod(applicationMethod);
            return this;
        }

        public CropPesticideScheduleBuilder remarks(String remarks) {
            instance.setRemarks(remarks);
            return this;
        }

        public CropPesticideSchedule build() {
            return instance;
        }
    }

}
