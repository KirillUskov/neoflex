package ru.kduskov.vacationcalculator.util;

import ru.kduskov.vacationcalculator.constant.WorkingCalendar;

import java.time.LocalDate;

public final class DateUtil {
    public static int getAmountOfHolidays(LocalDate startDate, LocalDate endDate) {
        int amountOfHolidays = 0;
        do {
            if(WorkingCalendar.HOLIDAY_DATES.contains(startDate))
                amountOfHolidays++;
            startDate = startDate.plusDays(1);
        } while (endDate.isAfter(startDate));
        return amountOfHolidays;
    }
}
