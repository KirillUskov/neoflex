import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.kduskov.vacationcalculator.exception.InvalidRequestException;
import ru.kduskov.vacationcalculator.service.VacationPaymentCalculatorService;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class VacationPaymentCalculatorServiceTest {
    private final VacationPaymentCalculatorService vacationService = new VacationPaymentCalculatorService();

    @Test
    void calculateVacationPayWithValidAvgSalaryAndDuration() {
        double pay = vacationService.calculatePayment(60000, 10, null, null);
        Assertions.assertEquals(20477.81, pay, 0.01);
    }

    @Test
    void calculateVacationPayWithAllValidFields() {
        double pay = vacationService.calculatePayment(60000, 5, LocalDate.of(2024, 11,10), LocalDate.of(2024, 11,14));
        Assertions.assertEquals(10238.91, pay, 0.01);
    }

    @Test
    void salaryLessThanMinimumRequestReturnPaymentWithMinimumWage() {
        double pay = vacationService.calculatePayment(5000, 10, null, null);

        Assertions.assertEquals(6567.24, pay, 0.01);
    }

    @Test
    void negativeSalaryRequestThrowsException() {
        InvalidRequestException thrown = assertThrows(InvalidRequestException.class, () -> {
            vacationService.calculatePayment(-5000, 5, null, null);
        });

        Assertions.assertEquals("Invalid input values.", thrown.getMessage());
    }

    @Test
    void negativeDurationRequestThrowsException() {
        InvalidRequestException thrown = assertThrows(InvalidRequestException.class, () -> {
            vacationService.calculatePayment(50000, -5, null, null);
        });

        Assertions.assertEquals("Invalid input values.", thrown.getMessage());
    }

    @Test
    void endDateBeforeStartDateRequestThrowsException() {
        InvalidRequestException thrown = assertThrows(InvalidRequestException.class, () -> {
            vacationService.calculatePayment(50000, 5, LocalDate.of(2024, 12,10),  LocalDate.of(2024, 11,10));
        });

        Assertions.assertEquals("Invalid dates.", thrown.getMessage());
    }

}
