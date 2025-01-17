package com.xplug.tech.crop.management.domain;

import com.xplug.tech.crop.management.enums.PesticideApplicationMethod;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CropPesticideSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    private PesticideApplicationMethod applicationMethod; //enum

    @Column(length = 500)
    private String remarks;

}
