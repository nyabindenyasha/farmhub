package com.xplug.tech.crop.data;

import com.xplug.tech.enums.RateUnit;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
@ToString
@Embeddable
public class PlantPopulation {

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RateUnit unit;

    @Column(nullable = false)
    private int minRate; // Minimum seeds per unit

    @Column(nullable = false)
    private int maxRate; // Maximum seeds per unit

}

