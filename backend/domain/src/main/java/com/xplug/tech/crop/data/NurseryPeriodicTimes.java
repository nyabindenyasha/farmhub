package com.xplug.tech.crop.data;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Embeddable;

@Getter
@Setter
@ToString
@Embeddable
public class NurseryPeriodicTimes {

    private String plantingTime;

    private String emergenceTime;

    private String transplantingTime;

}
