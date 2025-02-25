package com.xplug.tech.cropguide;

import com.xplug.tech.crop.data.NurseryPeriodicTimes;
import com.xplug.tech.crop.data.NurserySpacing;
import com.xplug.tech.crop.data.PlantPopulation;
import com.xplug.tech.crop.data.SoilTemperatureForGermination;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NurseryManagementRequest {

    private PlantPopulation seedRate;

    private NurseryPeriodicTimes nurseryPeriodicTimes;

    private SoilTemperatureForGermination soilTemperatureForGermination;

    private NurserySpacing nurserySpacing;

    private NurseryBedPreparationRequest nurseryBedPreparation;

}
