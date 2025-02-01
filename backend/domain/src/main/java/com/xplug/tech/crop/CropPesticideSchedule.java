package com.xplug.tech.crop;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.xplug.tech.enums.PesticideApplicationMethod;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString(exclude = {"cropSchedule"})
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
    private PesticideApplicationMethod applicationMethod;

    @Column(length = 500)
    private String remarks;

}
