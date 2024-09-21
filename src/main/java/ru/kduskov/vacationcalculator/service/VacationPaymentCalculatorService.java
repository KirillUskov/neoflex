package ru.kduskov.vacationcalculator.service;

import org.springframework.stereotype.Service;
import ru.kduskov.vacationcalculator.constant.BoundaryValues;
import ru.kduskov.vacationcalculator.exception.InvalidRequestException;
import ru.kduskov.vacationcalculator.util.DateUtil;
import ru.kduskov.vacationcalculator.util.NumberUtil;

import java.time.LocalDate;
import java.util.Objects;
import java.util.logging.Logger;

import static java.lang.String.format;

@Service
public class VacationPaymentCalculatorService {
    private static final Logger logger = Logger.getLogger(VacationPaymentCalculatorService.class.getName());

    private static final double AVG_DAYS_IN_MONTH = 29.3;

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
        double dailyWage = salary / AVG_DAYS_IN_MONTH;
        return NumberUtil.roundDoubleTwoDigits(vacationDuration * dailyWage);
    }
}
