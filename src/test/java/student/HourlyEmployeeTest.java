package student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HourlyEmployeeTest {
    private HourlyEmployee hourlyEmployee;
    @BeforeEach
    void setUp() {
        hourlyEmployee = new HourlyEmployee("Kelly", "a109", 20.00, 5000.00, 1000.00, 200.00);
    }

    @Test
    void getEmployeeType() {
        assertEquals("HOURLY", hourlyEmployee.getEmployeeType());
    }

    @Test
    void calculateGrossPay_regular() {
        assertEquals(800.00, hourlyEmployee.calculateGrossPay(40), 0.01);
    }

    @Test
    void calculateGrossPay_over_time(){
        assertEquals(950.00, hourlyEmployee.calculateGrossPay(45), 0.01);
    }
}