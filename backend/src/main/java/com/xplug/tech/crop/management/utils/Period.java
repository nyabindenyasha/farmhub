package com.xplug.tech.crop.management.utils;

import com.xplug.tech.crop.management.enums.PeriodUnit;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class Period {

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PeriodUnit periodUnit;

    @Column(nullable = false)
    private Integer periodValue;

}
