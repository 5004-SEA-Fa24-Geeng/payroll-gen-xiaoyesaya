package student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SalaryEmployeeTest {
    private SalaryEmployee salaryEmployee;
    @BeforeEach
    void setUp() {
        salaryEmployee = new SalaryEmployee("Alex", "x233", 50000.00, 20000.00, 3000.00, 500.00);
    }

    @Test
    void getEmployeeType() {
        assertEquals("SALARY", salaryEmployee.getEmployeeType());
    }

    @Test
    void calculateGrossPay() {
        assertEquals(2083.33, salaryEmployee.calculateGrossPay(0), 0.01);
    }
}