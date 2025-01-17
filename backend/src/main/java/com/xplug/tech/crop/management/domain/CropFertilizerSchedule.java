package com.xplug.tech.crop.management.domain;

import com.xplug.tech.crop.management.enums.FertilizerApplicationMethod;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CropFertilizerSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    private FertilizerApplicationMethod applicationMethod; //enum

    @Column(length = 500)
    private String remarks;

}
