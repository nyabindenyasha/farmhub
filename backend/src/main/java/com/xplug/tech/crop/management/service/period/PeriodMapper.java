package com.xplug.tech.crop.management.service.period;

import com.xplug.tech.crop.management.domain.Period;

public sealed interface PeriodMapper permits PeriodMapperImpl {

    Period periodFromPeriodRequest(PeriodRequest periodRequest);

    Period periodFromPeriodUpdateRequest(Period period, PeriodUpdateRequest periodUpdateRequest);

    PeriodResponse periodResponseFromPeriod(Period period);

}
