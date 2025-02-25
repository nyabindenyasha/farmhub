package com.xplug.tech.crop.data;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Embeddable
public class SoilTemperatureForGermination {

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "temperatureValue", column = @Column(name = "min_temp_value")),
            @AttributeOverride(name = "description", column = @Column(name = "min_temp_description"))
    })
    private SoilTemperature minimumTemperature;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "temperatureValue", column = @Column(name = "opt_temp_value")),
            @AttributeOverride(name = "description", column = @Column(name = "opt_temp_description"))
    })
    private SoilTemperature optimumTemperature;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "temperatureValue", column = @Column(name = "max_temp_value")),
            @AttributeOverride(name = "description", column = @Column(name = "max_temp_description"))
    })
    private SoilTemperature maximumTemperature;

}

