package com.xplug.tech.crop;

import com.xplug.tech.enums.PesticideApplicationMethod;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class CropPesticideSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pesticide_schedule_seq")
    @SequenceGenerator(name = "pesticide_schedule_seq", sequenceName = "pesticide_schedule_seq", allocationSize = 1)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "crop_schedule_id", nullable = false)
    private CropSchedule cropSchedule;

    @ManyToOne
    @JoinColumn(name = "pesticide_id", nullable = false)
    private Pesticide pesticide;

    @ManyToOne
    @JoinColumn(name = "period_id_stage_of_growth", nullable = false)
    private Period stageOfGrowth;

    @ManyToOne
    @JoinColumn(name = "period_id_application_interval", nullable = false)
    private Period applicationInterval;

    @Enumerated(EnumType.STRING)
    private PesticideApplicationMethod applicationMethod;

    @Column(length = 500)
    private String remarks;

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

        public CropPesticideScheduleBuilder cropSchedule(CropSchedule cropSchedule) {
            instance.setCropSchedule(cropSchedule);
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
