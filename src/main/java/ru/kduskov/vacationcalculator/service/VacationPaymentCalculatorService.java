package ru.kduskov.vacationcalculator.service;

import lombok.extern.log4j.Log4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;
import ru.kduskov.vacationcalculator.constant.BoundaryValues;
import ru.kduskov.vacationcalculator.exception.InvalidRequestException;
import ru.kduskov.vacationcalculator.util.DateUtil;
import ru.kduskov.vacationcalculator.util.NumberUtil;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;
import java.util.logging.Logger;

import static java.lang.String.format;
import static ru.kduskov.vacationcalculator.constant.BoundaryValues.MAX_VACATION_DURATION;
import static ru.kduskov.vacationcalculator.constant.BoundaryValues.MIN_VACATION_DURATION;

@Service
public class VacationPaymentCalculatorService {
    private static final Logger logger = Logger.getLogger(VacationPaymentCalculatorService.class.getName());

    private static final double AVG_DAYS_IN_MONTH_AMOUNT = 29.3;

    public Double calculatePayment(double avgSalary, Integer duration, LocalDate startDate, LocalDate endDate) throws InvalidRequestException {
        logger.info(format("Request: %f, %d, %s, %s", avgSalary, duration, startDate, endDate));
        if(duration <= 0 || avgSalary <= 0)
            throw new InvalidRequestException("Invalid input values.");
        avgSalary = Math.max(avgSalary, BoundaryValues.MIN_SALARY);
        // расчет с учетом дат начала и конца
        if(Objects.nonNull(startDate) && Objects.nonNull(endDate)){
            logger.info(format("Request dates: %s, %s", startDate, endDate));
            if(!(endDate.isAfter(startDate) || endDate.isEqual(startDate)))
                throw new InvalidRequestException("Invalid dates.");

            int holidaysAmount = DateUtil.getAmountOfHolidays(startDate, endDate);
            int datesForPayment = duration - holidaysAmount;
            logger.info(format("Holidays amount: %d", holidaysAmount ));

            return getPayment(datesForPayment, avgSalary);
        }
        // расчет без учета дат начала и конца
        return getPayment(duration, avgSalary);
    }

    private double getPayment(int vacationDuration, double salary) {
        double dailyWage = salary / AVG_DAYS_IN_MONTH_AMOUNT;
        return NumberUtil.roundDoubleTwoDigits(vacationDuration * dailyWage);
    }
}
