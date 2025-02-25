package com.xplug.tech.cropguide;


import com.xplug.tech.enums.MeasurementUnit;
import com.xplug.tech.enums.RateUnit;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FertilizerApplicationRateRequest {

    private Long fertilizerId;

    private MeasurementUnit unit;

    private double quantity;

    private RateUnit perUnit;

}
