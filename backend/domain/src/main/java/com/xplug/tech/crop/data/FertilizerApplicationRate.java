package com.xplug.tech.crop.data;


import com.xplug.tech.crop.Fertilizer;
import com.xplug.tech.enums.MeasurementUnit;
import com.xplug.tech.enums.RateUnit;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class FertilizerApplicationRate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "fertilizer_id", nullable = false)
    private Fertilizer fertilizer;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MeasurementUnit unit; // e.g., "kg", "L"

    @Column(nullable = false)
    private double quantity; // e.g., 10

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RateUnit perUnit; // e.g., "m²", "ha"

}
