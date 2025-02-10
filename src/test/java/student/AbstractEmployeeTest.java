package student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AbstractEmployeeTest {
    private HourlyEmployee hourlyEmployee;
    private SalaryEmployee salaryEmployee;
    @BeforeEach
    void setUp() {
        hourlyEmployee = new HourlyEmployee("Kelly", "a109", 20.00, 5000.00, 1000.00, 0.00);
        salaryEmployee = new SalaryEmployee("Alex", "x233", 50000.00, 20000.00, 3000.00, 500.00);
    }

    @Test
    void testConstructor() {

        assertEquals("Kelly", hourlyEmployee.getName());
        assertEquals("a109", hourlyEmployee.getID());
        assertEquals(20.00, hourlyEmployee.getPayRate(), 0.01);
        assertEquals(5000.00, hourlyEmployee.getYTDEarnings(), 0.01);
        assertEquals(1000.00, hourlyEmployee.getYTDTaxesPaid(), 0.01);
        assertEquals(0.00, hourlyEmployee.getPretaxDeductions(), 0.01);

        assertEquals("Alex", salaryEmployee.getName());
        assertEquals("x233", salaryEmployee.getID());
        assertEquals(50000.00, salaryEmployee.getPayRate(), 0.01);
        assertEquals(20000.00, salaryEmployee.getYTDEarnings(), 0.01);
        assertEquals(3000.00, salaryEmployee.getYTDTaxesPaid(), 0.01);
        assertEquals(500.00, salaryEmployee.getPretaxDeductions(), 0.01);
    }

    @Test
    void getName() {
        assertEquals("Kelly", hourlyEmployee.getName());
        assertEquals("Alex", salaryEmployee.getName());
    }

    @Test
    void getID() {
        assertEquals("a109", hourlyEmployee.getID());
        assertEquals("x233", salaryEmployee.getID());
    }

    @Test
    void getPayRate() {
        assertEquals(20.00, hourlyEmployee.getPayRate(), 0.01);
        assertEquals(50000.00, salaryEmployee.getPayRate(), 0.01);
    }

    @Test
    void getYTDEarnings() {
        assertEquals(5000.00, hourlyEmployee.getYTDEarnings(), 0.01);
        assertEquals(20000.00, salaryEmployee.getYTDEarnings(), 0.01);
    }

    @Test
    void getYTDTaxesPaid() {
        assertEquals(1000.00, hourlyEmployee.getYTDTaxesPaid(), 0.01);
        assertEquals(3000.00, salaryEmployee.getYTDTaxesPaid(), 0.01);
    }

    @Test
    void getPretaxDeductions() {
        assertEquals(0.00, hourlyEmployee.getPretaxDeductions(), 0.01);
        assertEquals(500.00, salaryEmployee.getPretaxDeductions(), 0.01);
    }

    @Test
    void calculateGrossPay() {
        assertEquals(800.00, hourlyEmployee.calculateGrossPay(40), 0.01);
        assertEquals(950.00, hourlyEmployee.calculateGrossPay(45), 0.01);
        assertEquals(2083.33, salaryEmployee.calculateGrossPay(0), 0.01);
    }


    @Test
    void runPayroll() {
        assertNull(hourlyEmployee.runPayroll(-5));
        double hoursWorked = 40;
        double initialYTDEarnings = hourlyEmployee.getYTDEarnings();
        double initialYTDTaxesPaid = hourlyEmployee.getYTDTaxesPaid();
        IPayStub paystub = hourlyEmployee.runPayroll(40);
        assertNotNull(paystub);
        double expectedGrossPay = hourlyEmployee.calculateGrossPay(40);
        double expectedTaxableIncome = AbstractEmployee.round(Math.max(expectedGrossPay - hourlyEmployee.getPretaxDeductions(), 0));
        double expectedTaxes = AbstractEmployee.round(expectedTaxableIncome * 0.2265);
        double expectedNetPay = AbstractEmployee.round(expectedGrossPay - hourlyEmployee.getPretaxDeductions() - expectedTaxes);
        double expectedYTDEarnings = AbstractEmployee.round(initialYTDEarnings + expectedNetPay);
        double expectedYTDTaxesPaid = AbstractEmployee.round(initialYTDTaxesPaid + expectedTaxes);
        assertEquals(expectedNetPay, paystub.getPay(), 0.01);
        assertEquals(expectedTaxes, paystub.getTaxesPaid(), 0.01);
        assertEquals(expectedYTDEarnings, hourlyEmployee.getYTDEarnings(), 0.01);
        assertEquals(expectedYTDTaxesPaid, hourlyEmployee.getYTDTaxesPaid(), 0.01);
    }

    @Test
    void runPayrollWithZeroHours(){
        IPayStub paystub = hourlyEmployee.runPayroll(0);
        assertNotNull(paystub);
        assertEquals(0.00, paystub.getPay(), 0.01);
        assertEquals(0.00, paystub.getTaxesPaid(), 0.01);
        assertEquals(hourlyEmployee.getYTDEarnings(), 5000.00, 0.01);
        assertEquals(hourlyEmployee.getYTDTaxesPaid(), 1000.00, 0.01);
    }

    @Test
    void toCSV() {
        String expectedHourlyCSV = "HOURLY,Kelly,a109,20.00,0.00,5000.00,1000.00";
        String expectedSalaryCSV = "SALARY,Alex,x233,50000.00,500.00,20000.00,3000.00";

        assertEquals(expectedHourlyCSV, hourlyEmployee.toCSV());
        assertEquals(expectedSalaryCSV, salaryEmployee.toCSV());
    }

    @Test
    void round() {
        assertEquals(10.12, AbstractEmployee.round(10.123), 0.01);
        assertEquals(10.13, AbstractEmployee.round(10.125), 0.01);
        assertEquals(15.00, AbstractEmployee.round(15.004), 0.01);
    }
}