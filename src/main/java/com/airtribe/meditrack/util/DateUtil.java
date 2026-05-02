package com.airtribe.meditrack.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class DateUtil {

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private static final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    private DateUtil() {
    }

    public static String formatDate(LocalDate date) {
        return date == null ? "" : date.format(DATE_FORMAT);
    }

    public static String formatDateTime(LocalDateTime dateTime) {
        return dateTime == null ? "" : dateTime.format(DATE_TIME_FORMAT);
    }

    public static LocalDate parseDate(String value) {
        return value == null || value.isBlank() ? null : LocalDate.parse(value, DATE_FORMAT);
    }

    public static LocalDateTime parseDateTime(String value) {
        return value == null || value.isBlank() ? null : LocalDateTime.parse(value, DATE_TIME_FORMAT);
    }
}
