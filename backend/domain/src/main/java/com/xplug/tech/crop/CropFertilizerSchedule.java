package com.xplug.tech.crop;

import com.xplug.tech.enums.FertilizerApplicationMethod;
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
public class CropFertilizerSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "fertilizer_schedule_seq")
    @SequenceGenerator(name = "fertilizer_schedule_seq", sequenceName = "fertilizer_schedule_seq", allocationSize = 1)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "crop_schedule_id", nullable = false)
    private CropSchedule cropSchedule;

    @ManyToOne
    @JoinColumn(name = "fertilizer_id", nullable = false)
    private Fertilizer fertilizer;

//    @Embedded
//    @NotNull(message = "Crop Stage Of Growth for fertilizer application is required")
//    @AttributeOverrides({
//            @AttributeOverride(name = "periodUnit", column = @Column(name = "stage_of_growth_period_unit")),
//            @AttributeOverride(name = "periodValue", column = @Column(name = "stage_of_growth_period_value"))
//    })

    @ManyToOne
    @JoinColumn(name = "period_id_stage_of_growth", nullable = false)
    private Period stageOfGrowth;

    @ManyToOne
    @JoinColumn(name = "period_id_application_interval", nullable = false)
    private Period applicationInterval;

    //todo
    private Integer rate; //grams per plant

    @Enumerated(EnumType.STRING)
    private FertilizerApplicationMethod applicationMethod;

    @Column(length = 500)
    private String remarks;

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

        public CropFertilizerScheduleBuilder cropSchedule(CropSchedule cropSchedule) {
            instance.setCropSchedule(cropSchedule);
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
