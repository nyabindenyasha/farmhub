package com.xplug.tech.crop;

import com.xplug.tech.enums.PeriodUnit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PeriodDao extends JpaRepository<Period, Long> {

    Optional<Period> findByPeriodUnitAndPeriodValue(PeriodUnit periodUnit, Integer periodValue);

}
