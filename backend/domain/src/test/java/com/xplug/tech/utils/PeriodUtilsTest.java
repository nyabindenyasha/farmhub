package com.xplug.tech.utils;

import com.xplug.tech.crop.Period;
import com.xplug.tech.enums.PeriodUnit;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PeriodUtilsTest {

    @Test
    void testPeriodComparison() {
        // Create test periods
        Period oneDay = new Period();
        oneDay.setPeriodUnit(PeriodUnit.DAYS);
        oneDay.setPeriodValue(1);

        Period oneWeek = new Period();
        oneWeek.setPeriodUnit(PeriodUnit.WEEKS);
        oneWeek.setPeriodValue(1);

        Period twoWeeks = new Period();
        twoWeeks.setPeriodUnit(PeriodUnit.WEEKS);
        twoWeeks.setPeriodValue(2);

        // Test less than
        assertTrue(PeriodUtils.isLessThan(oneDay, oneWeek), "1 day should be less than 1 week");
        assertTrue(PeriodUtils.isLessThan(oneWeek, twoWeeks), "1 week should be less than 2 weeks");

        // Test greater than
        assertTrue(PeriodUtils.isGreaterThan(oneWeek, oneDay), "1 week should be greater than 1 day");
        assertTrue(PeriodUtils.isGreaterThan(twoWeeks, oneWeek), "2 weeks should be greater than 1 week");

        // Test sorting
        List<Period> periods = Arrays.asList(twoWeeks, oneDay, oneWeek);
        List<Period> sortedPeriods = PeriodUtils.sortPeriods(periods);
        
        assertEquals(3, sortedPeriods.size(), "Sorted list should have same size");
        assertSame(oneDay, sortedPeriods.get(0), "First element should be 1 day");
        assertSame(oneWeek, sortedPeriods.get(1), "Second element should be 1 week");
        assertSame(twoWeeks, sortedPeriods.get(2), "Third element should be 2 weeks");
    }

    @Test
    void testEquivalentPeriods() {
        // 24 hours = 1 day
        Period twentyFourHours = new Period();
        twentyFourHours.setPeriodUnit(PeriodUnit.HOURS);
        twentyFourHours.setPeriodValue(24);

        Period oneDay = new Period();
        oneDay.setPeriodUnit(PeriodUnit.DAYS);
        oneDay.setPeriodValue(1);

        assertTrue(PeriodUtils.isEqual(twentyFourHours, oneDay), "24 hours should equal 1 day");
    }

    @Test
    void testNullHandling() {
        Period validPeriod = new Period();
        validPeriod.setPeriodUnit(PeriodUnit.DAYS);
        validPeriod.setPeriodValue(1);

        assertThrows(IllegalArgumentException.class, () -> PeriodUtils.toHours(null),
                "Should throw exception for null period");

        Period nullUnitPeriod = new Period();
        nullUnitPeriod.setPeriodValue(1);
        assertThrows(IllegalArgumentException.class, () -> PeriodUtils.toHours(nullUnitPeriod),
                "Should throw exception for null period unit");

        Period nullValuePeriod = new Period();
        nullValuePeriod.setPeriodUnit(PeriodUnit.DAYS);
        assertThrows(IllegalArgumentException.class, () -> PeriodUtils.toHours(nullValuePeriod),
                "Should throw exception for null period value");
    }
}
