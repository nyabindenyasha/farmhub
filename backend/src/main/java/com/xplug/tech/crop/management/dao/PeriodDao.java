package com.xplug.tech.crop.management.dao;

import com.xplug.tech.crop.management.domain.Period;
import com.xplug.tech.crop.management.enums.PeriodUnit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PeriodDao extends JpaRepository<Period, Long> {

    Optional<Period> findByPeriodUnitAndPeriodValue(PeriodUnit periodUnit, Integer periodValue);

}
