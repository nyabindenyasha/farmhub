package com.xplug.tech.crop.data;

import com.xplug.tech.enums.MeasurementUnit;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
@ToString
@Embeddable
public class Spacing {

    private Integer minimumSpacing;

    private Integer maximumSpacing;

    @Enumerated(EnumType.STRING)
    private MeasurementUnit unit;

    private String description;

}
