package com.xplug.tech.utils;

import com.xplug.tech.crop.Period;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.xplug.tech.crop.Period.toDays;

public class PeriodUtils {

    private static final int HOURS_IN_DAY = 24;
    private static final int HOURS_IN_WEEK = HOURS_IN_DAY * 7;
    private static final int HOURS_IN_MONTH = HOURS_IN_DAY * 30; // Approximate
    private static final int HOURS_IN_YEAR = HOURS_IN_DAY * 365; // Non-leap year

    /**
     * Converts a Period to hours for comparison purposes
     *
     * @param period The period to convert
     * @return The number of hours equivalent to the period
     */
    public static long toHours(Period period) {
        if (period == null || period.getPeriodUnit() == null || period.getPeriodValue() == null) {
            throw new IllegalArgumentException("Period, period unit, and period value cannot be null");
        }

        long value = period.getPeriodValue();

        switch (period.getPeriodUnit()) {
            case MINUTES:
                return value /60;
            case HOURS:
                return value;
            case DAYS:
                return value * HOURS_IN_DAY;
            case WEEKS:
                return value * HOURS_IN_WEEK;
            case MONTHS:
                return value * HOURS_IN_MONTH;
            case YEARS:
                return value * HOURS_IN_YEAR;
            default:
                throw new IllegalArgumentException("Unknown period unit: " + period.getPeriodUnit());
        }
    }

    /**
     * Comparator for sorting Period objects
     */
    public static final Comparator<Period> PERIOD_COMPARATOR = (p1, p2) -> {
        long hours1 = toHours(p1);
        long hours2 = toHours(p2);
        return Long.compare(hours1, hours2);
    };

    /**
     * Sorts a list of periods in ascending order
     *
     * @param periods The list of periods to sort
     * @return A new sorted list
     */
    public static List<Period> sortPeriods(List<Period> periods) {
        if (periods == null) {
            throw new IllegalArgumentException("Periods list cannot be null");
        }
        return periods.stream()
                .sorted(PERIOD_COMPARATOR)
                .collect(Collectors.toList());
    }

    /**
     * Checks if one period is less than another
     *
     * @param period1 The first period
     * @param period2 The second period
     * @return true if period1 is less than period2
     */
    public static boolean isLessThan(Period period1, Period period2) {
        return PERIOD_COMPARATOR.compare(period1, period2) < 0;
    }

    /**
     * Checks if one period is greater than another
     *
     * @param period1 The first period
     * @param period2 The second period
     * @return true if period1 is greater than period2
     */
    public static boolean isGreaterThan(Period period1, Period period2) {
        return PERIOD_COMPARATOR.compare(period1, period2) > 0;
    }

    /**
     * Checks if two periods are equal in duration
     *
     * @param period1 The first period
     * @param period2 The second period
     * @return true if the periods represent the same duration
     */
    public static boolean isEqual(Period period1, Period period2) {
        return PERIOD_COMPARATOR.compare(period1, period2) == 0;
    }

    public static LocalDateTime addPeriod(LocalDateTime dateTime, Period period) {
        if (dateTime == null || period == null) {
            throw new IllegalArgumentException("DateTime and period cannot be null");
        }

        switch (period.getPeriodUnit()) {
            case MINUTES:
                return dateTime.plusMinutes(period.getPeriodValue());
            case HOURS:
                return dateTime.plusHours(period.getPeriodValue());
            case DAYS:
                return dateTime.plusDays(period.getPeriodValue());
            case WEEKS:
                return dateTime.plusWeeks(period.getPeriodValue());
            case MONTHS:
                return dateTime.plusMonths(period.getPeriodValue());
            case YEARS:
                return dateTime.plusYears(period.getPeriodValue());
            default:
                throw new IllegalArgumentException("Unknown period unit: " + period.getPeriodUnit());
        }
    }

}
