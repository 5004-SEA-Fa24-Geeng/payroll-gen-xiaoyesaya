package student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BuilderTest {


    @Test
    void buildEmployeeFromCSV_ValidHourlyEmployee() {
        String csv = "HOURLY,Kelly,a109,20.00,200.00,5000.00,1000.00";
        IEmployee employee = Builder.buildEmployeeFromCSV(csv);

        assertNotNull(employee);
        assertEquals("Kelly", employee.getName());
        assertEquals("a109", employee.getID());
        assertEquals(20.00, employee.getPayRate(), 0.01);
        assertEquals(5000.00, employee.getYTDEarnings(), 0.01);
        assertEquals(1000.00, employee.getYTDTaxesPaid(), 0.01);
        assertEquals(200.00, employee.getPretaxDeductions(), 0.01);
    }

    @Test
    void buildEmployeeFromCSV_ValidSalaryEmployee() {
        String csv = "SALARY,Alex,x233,50000.00,500.00,20000.00,3000.00";
        IEmployee employee = Builder.buildEmployeeFromCSV(csv);

        assertNotNull(employee);
        assertEquals("Alex", employee.getName());
        assertEquals("x233", employee.getID());
        assertEquals(50000.00, employee.getPayRate(), 0.01);
        assertEquals(20000.00, employee.getYTDEarnings(), 0.01);
        assertEquals(3000.00, employee.getYTDTaxesPaid(), 0.01);
        assertEquals(500.00, employee.getPretaxDeductions(), 0.01);
    }

    @Test
    void buildEmployeeFromCSV_InvalidInputExceptionThrown() {
        String csv = ",Kelly,a109,20.00,5000.00";
        assertThrows(IllegalArgumentException.class, () -> Builder.buildEmployeeFromCSV(csv));
    }

    @Test
    void buildEmployeeFromCSV_ValidInputDoesNotThrow() {
        String csv = "HOURLY,Ross,a208,30.00,300.00,7000.00,2000.00";
        assertDoesNotThrow(() -> {
            Builder.buildEmployeeFromCSV(csv);
        });
    }

    @Test
    void buildTimeCardFromCSV() {
        String csv = "a109,40.5";
        ITimeCard timeCard = Builder.buildTimeCardFromCSV(csv);

        assertNotNull(timeCard);
        assertEquals("a109", timeCard.getEmployeeID());
        assertEquals(40.5, timeCard.getHoursWorked(), 0.01);

    }

    @Test
    void buildTimeCardFromCSV_InvalidInputExceptionThrown() {
        String csv = "a109";
        assertThrows(IllegalArgumentException.class, () -> Builder.buildTimeCardFromCSV(csv));
    }

    @Test
    void buildTimeCardFromCSV_ValidInputDoesNotThrow() {
        String csv = "a109,40.5";
        assertDoesNotThrow(() -> {Builder.buildTimeCardFromCSV(csv);});
    }
}