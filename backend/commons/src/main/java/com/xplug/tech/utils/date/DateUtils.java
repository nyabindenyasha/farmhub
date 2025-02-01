package com.xplug.tech.utils.date;

import java.time.*;
import java.util.Date;

public class DateUtils {

    public static Date convertToDateViaInstant(LocalDateTime dateToConvert) {
        return Date.from(dateToConvert.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static Date convertToDateViaInstantV2(LocalDate dateToConvert) {
        LocalDateTime localDateTime = LocalDateTime.of(dateToConvert, LocalTime.MIDNIGHT);
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static LocalDateTime convertToLocalDateTime(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    public static LocalDate convertToLocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public static LocalTime convertToLocalTime(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalTime();
    }

    public static LocalDateTime getCurrentTime() {
        return ZonedDateTime.now(ZoneId.of("Africa/Harare")).toLocalDateTime();
    }

}
