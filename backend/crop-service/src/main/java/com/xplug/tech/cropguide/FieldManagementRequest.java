package com.xplug.tech.cropguide;

import com.xplug.tech.crop.data.PlantPopulation;
import com.xplug.tech.crop.data.Spacing;
import com.xplug.tech.enums.EaseOfCare;
import com.xplug.tech.enums.WaterRequirement;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FieldManagementRequest {

    private String soilRequirements;

    private String pH;

    private String temperature;

    private WaterRequirement waterRequirement;

    private EaseOfCare easeOfCare;

    private Spacing inRowSpacing;

    private Spacing interRowSpacing;

    private PlantPopulation plantPopulation;

}
