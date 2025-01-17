package com.xplug.tech.crop.management.service.period;

import com.xplug.tech.crop.management.domain.Period;
import com.xplug.tech.crop.management.enums.PeriodUnit;
import lombok.Builder;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

@Setter
@ToString
@Builder
public class PeriodResponse {

    private Long id;

    private PeriodUnit periodUnit;

    private Integer periodValue;

    public static PeriodResponse of(Period period) {
        Objects.requireNonNull(period, "Crop cannot be null!");
        return PeriodResponse.builder()
                .id(period.getId())
                .periodUnit(period.getPeriodUnit())
                .periodValue(period.getPeriodValue())
                .build();
    }

}
