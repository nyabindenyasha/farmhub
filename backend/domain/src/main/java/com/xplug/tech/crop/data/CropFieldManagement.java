package com.xplug.tech.crop.data;

import com.xplug.tech.crop.CropVariety;
import com.xplug.tech.enums.EaseOfCare;
import com.xplug.tech.enums.WaterRequirement;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CropFieldManagement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String soilRequirements;  // e.g., "Clay loams rich in organic matter"

    @Column(nullable = false)
    private String pH;

    @Column(nullable = false)
    private String temperature; // e.g., "18-30°C"

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private WaterRequirement waterRequirement; // Enum (LOW, MEDIUM, HEAVY)

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EaseOfCare easeOfCare; // Enum (LOW, MEDIUM, HIGH)

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "minimumSpacing", column = @Column(name = "in_row_spacing_min_spacing")),
            @AttributeOverride(name = "maximumSpacing", column = @Column(name = "in_row_spacing_max_spacing")),
            @AttributeOverride(name = "unit", column = @Column(name = "in_row_spacing_unit")),
            @AttributeOverride(name = "description", column = @Column(name = "in_row_spacing_description"))
    })
    private Spacing inRowSpacing;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "minimumSpacing", column = @Column(name = "inter_row_spacing_min_spacing")),
            @AttributeOverride(name = "maximumSpacing", column = @Column(name = "inter_row_spacing_max_spacing")),
            @AttributeOverride(name = "unit", column = @Column(name = "inter_row_spacing_unit")),
            @AttributeOverride(name = "description", column = @Column(name = "inter_row_spacing_description"))
    })
    private Spacing interRowSpacing;

    @Embedded
    private PlantPopulation plantPopulation;

    //todo: for response
    @Transient
    private Set<CropVariety> cropVarieties;

}
