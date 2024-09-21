package ru.kduskov.vacationcalculator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.kduskov.vacationcalculator.service.VacationPaymentCalculatorService;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

import static ru.kduskov.vacationcalculator.constant.BoundaryValues.MAX_VACATION_DURATION;
import static ru.kduskov.vacationcalculator.constant.BoundaryValues.MIN_VACATION_DURATION;

@RestController
public class VacationPaymentCalculatorController {
    private final VacationPaymentCalculatorService vacationPaymentCalculatorService;

    @Autowired
    public VacationPaymentCalculatorController(VacationPaymentCalculatorService calculatorService) {
        this.vacationPaymentCalculatorService = calculatorService;
    }

    @GetMapping(value = "/calculate")
    public ResponseEntity<Object> calculateVacationPaymentAmount(@RequestParam(value = "avgSalary") @Min(0) int avgSalary,
                                                                 @RequestParam(value = "duration") @Min(MIN_VACATION_DURATION) @Max(MAX_VACATION_DURATION) Integer duration,
                                                                 @RequestParam(value = "startDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) @FutureOrPresent LocalDateTime startDate,
                                                                 @RequestParam(value = "endDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) @FutureOrPresent LocalDateTime endDate
    ) {
        LocalDate sDate = Objects.isNull(startDate) ? null : startDate.toLocalDate();
        LocalDate eDate = Objects.isNull(endDate) ? null : endDate.toLocalDate();
        double vacationPaymentAmount = vacationPaymentCalculatorService.calculatePayment(avgSalary, duration, sDate, eDate);
        return ResponseEntity.ok(vacationPaymentAmount);
    }
}
