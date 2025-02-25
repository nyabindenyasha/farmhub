package com.xplug.tech.crop.data;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Getter
@Setter
@ToString
@Embeddable
public class SoilTemperature {

    @Column
    private Integer temperatureValue;

    private String description;

    public SoilTemperature() {
    }

    public SoilTemperature(final Integer temperatureValue, final String description) {
        this.temperatureValue = temperatureValue;
        this.description = description;
    }

    public String toString() {
        return "SoilTemperature{temperatureValue=" + this.temperatureValue + ", description='" + this.description + '}';
    }
}

