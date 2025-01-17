package com.xplug.tech.crop.management.utils;

import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

@Slf4j
public class DateFormatter {

    //todo
    public static String format(String inputDate) {

//        SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy/MM/dd");

        // Create a SimpleDateFormat instance for the output format (ISO 8601)
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
        outputFormat.setTimeZone(TimeZone.getTimeZone("UTC")); // Set timezone to UTC
        String formattedDate = "";
        try {
            // Parse the input date string into a Date object
            Date date = inputFormat.parse(inputDate);
            // Format the Date object into the desired output format
            formattedDate = outputFormat.format(date);
            log.info("FormattedDate {}", formattedDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return formattedDate;
    }

}
