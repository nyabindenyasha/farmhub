package com.xplug.tech.period;

import com.xplug.tech.crop.Period;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public non-sealed class PeriodMapperImpl implements PeriodMapper {

    @Override
    public Period periodFromPeriodRequest(PeriodRequest periodRequest) {
        Objects.requireNonNull(periodRequest, "PeriodRequest cannot be null!");
        return Period.builder()
                .periodUnit(periodRequest.getPeriodUnit())
                .periodValue(periodRequest.getPeriodValue())
                .build();
    }

    @Override
    public Period periodFromPeriodUpdateRequest(Period period, PeriodUpdateRequest periodUpdateRequest) {
        Objects.requireNonNull(period, "Period cannot be null!");
        Objects.requireNonNull(periodUpdateRequest, "PeriodUpdateRequest cannot be null!");
        period.setPeriodUnit(periodUpdateRequest.getPeriodUnit());
        period.setPeriodValue(periodUpdateRequest.getPeriodValue());
        return period;
    }

    @Override
    public PeriodResponse periodResponseFromPeriod(Period period) {
        return PeriodResponse.of(period);
    }

}
