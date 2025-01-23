package com.xplug.tech.period;

import com.xplug.tech.crop.Period;

import java.util.List;

public sealed interface PeriodService permits PeriodServiceImpl {

    List<Period> getAll();

    Period getById(Long id);

    Period create(PeriodRequest periodRequest);

    Period findOrCreatePeriod(PeriodRequest request);

    Period update(PeriodUpdateRequest periodUpdateRequest);

    void delete(Long id);

}
