package com.xplug.tech.period;

import com.xplug.tech.crop.Period;

public sealed interface PeriodMapper permits PeriodMapperImpl {

    Period periodFromPeriodRequest(PeriodRequest periodRequest);

    Period periodFromPeriodUpdateRequest(Period period, PeriodUpdateRequest periodUpdateRequest);

    PeriodResponse periodResponseFromPeriod(Period period);

}
