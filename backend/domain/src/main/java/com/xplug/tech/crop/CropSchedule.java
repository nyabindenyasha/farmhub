package com.xplug.tech.crop;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class CropSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "crop_program_id", nullable = false)
    private CropProgram cropProgram;

    @ManyToOne
    @JoinColumn(name = "period_id_stage_of_growth", nullable = false)
    private Period stageOfGrowth;

    @ManyToOne
    @JoinColumn(name = "period_id_application_interval", nullable = false)
    private Period applicationInterval;

    @Column(length = 500)
    private String remarks;

}
