package com.co.technicaltest.transaction_service.infrastructure.shared.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Utility class por parse date on request.
 *
 * @author Andres Polo on 2025/09/28.
 * @version 1.0.0
 */
public class ParseDate {

    private ParseDate(){

    }

    public static LocalDateTime formattDateTimeToParam(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return LocalDateTime.parse(date, formatter);
    }
}
