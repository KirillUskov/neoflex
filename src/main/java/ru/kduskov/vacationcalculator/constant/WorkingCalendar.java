package ru.kduskov.vacationcalculator.constant;

import java.time.LocalDate;
import java.util.Set;

public final class WorkingCalendar {
    public static final Set<LocalDate> HOLIDAY_DATES =
            Set.of(
                    LocalDate.of(2024, 1, 1),
                    LocalDate.of(2024, 1, 2),
                    LocalDate.of(2024, 1, 3),
                    LocalDate.of(2024, 1, 4),
                    LocalDate.of(2024, 1, 5),
                    LocalDate.of(2024, 1, 6),
                    LocalDate.of(2024, 1, 7),
                    LocalDate.of(2024, 1, 8),
                    LocalDate.of(2024, 2, 23),
                    LocalDate.of(2024, 3, 8),
                    LocalDate.of(2024, 5, 1),
                    LocalDate.of(2024, 5, 9),
                    LocalDate.of(2024, 6, 12),
                    LocalDate.of(2024, 11, 4),

                    LocalDate.of(2025, 1, 1),
                    LocalDate.of(2025, 1, 2),
                    LocalDate.of(2025, 1, 3),
                    LocalDate.of(2025, 1, 4),
                    LocalDate.of(2025, 1, 5),
                    LocalDate.of(2025, 1, 6),
                    LocalDate.of(2025, 1, 7),
                    LocalDate.of(2025, 1, 8),
                    LocalDate.of(2025, 5, 1),
                    LocalDate.of(2025, 5, 9),
                    LocalDate.of(2025, 6, 12),
                    LocalDate.of(2025, 11, 4)
            );

}
