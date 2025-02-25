package com.xplug.tech.crop.data;

import lombok.*;

import javax.persistence.*;


@Entity
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CropNurseryManagement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private PlantPopulation seedRate;

    @Embedded
    private NurseryPeriodicTimes nurseryPeriodicTimes;

    @Embedded
    private SoilTemperatureForGermination soilTemperatureForGermination;

    @Embedded
    private NurserySpacing nurserySpacing;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "nursery_bed_preparation_id", nullable = false)
    private NurseryBedPreparation nurseryBedPreparation;

}



