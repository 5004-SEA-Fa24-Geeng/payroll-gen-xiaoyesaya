package student;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Abstract class representing a general employee.
 *
 * This class implements common attributes and methods for all types of employees,
 * including calculation and data formatting.
 * It also defines an abstract method for calculating gross pay, which is implemented by subclasses.
 */
public abstract class AbstractEmployee implements IEmployee {
    /** Employee's name */
    protected final String name;
    /** Employee's ID */
    protected final String id;
    /** Employee's pay rate */
    protected final double payRate;
    /** Year-to-date earnings */
    protected double YTDEarnings;
    /** Year-to-date taxes paid */
    protected double YTDTaxesPaid;
    /** Pre-tax deductions */
    protected final double pretaxDeductions;
    /** Fixed tax rate of 22.65% */
    private static final double TAX_RATE = 0.2265;

    /**
     * Constructs an AbstractEmployee with specified details.
     *
     * @param name Employee's name
     * @param id Employee's ID
     * @param payRate Employee's pay rate (must be greater than 0)
     * @param YTDEarnings Year-to-date earnings (cannot be negative)
     * @param YTDTaxesPaid Year-to-date taxes paid (cannot be negative)
     * @param pretaxDeductions Pre-tax deductions (cannot be negative)
     * @throws IllegalArgumentException if any input is invalid
     */
    public AbstractEmployee(String name, String id, double payRate, double YTDEarnings, double YTDTaxesPaid, double pretaxDeductions) {
        if (payRate <= 0) {
            throw new IllegalArgumentException("payRate must be greater than 0");
        }
        if (YTDEarnings < 0) {
            throw new IllegalArgumentException("YTDEarnings cannot be negative");
        }
        if (YTDTaxesPaid < 0) {
            throw new IllegalArgumentException("YTDTaxesPaid cannot be negative");
        }
        if (pretaxDeductions < 0) {
            throw new IllegalArgumentException("pretaxDeductions cannot be negative");
        }
        this.name = name;
        this.id = id;
        this.payRate = payRate;
        this.YTDEarnings = YTDEarnings;
        this.YTDTaxesPaid = YTDTaxesPaid;
        this.pretaxDeductions = pretaxDeductions;

    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getID() {
        return id;
    }

    @Override
    public double getPayRate() {
        return payRate;
    }

    @Override
    public double getYTDEarnings() {
        return YTDEarnings;
    }

    @Override
    public double getYTDTaxesPaid() {
        return YTDTaxesPaid;
    }

    @Override
    public double getPretaxDeductions() {
        return pretaxDeductions;
    }

    /**
     * Abstract method to calculate gross pay based on hours worked.
     *
     * @param hoursWorked Number of hours worked in the pay period
     * @return Gross pay for the period
     */
    protected abstract double calculateGrossPay(double hoursWorked);

    /**
     * Runs payroll for the employee based on hours worked.
     *
     * If hours worked is negative, the method returns null.
     * Otherwise, it calculates gross pay, taxable income, taxes, and net pay,
     * and updates year-to-date earnings and taxes paid.
     *
     * @param hoursWorked Number of hours worked
     * @return A PayStub containing payroll details
     */
    @Override
    public IPayStub runPayroll(double hoursWorked) {
        if (hoursWorked < 0) {
            return null;
        }
        double grossPay = calculateGrossPay(hoursWorked);
        double taxableIncome = round(Math.max(grossPay - pretaxDeductions, 0));
        double taxes = round(taxableIncome * TAX_RATE);
        double netPay = round(grossPay - pretaxDeductions - taxes);
        YTDEarnings = round(YTDEarnings + netPay);
        YTDTaxesPaid = round(YTDTaxesPaid + taxes);
        return new PayStub(name, netPay, taxes, YTDEarnings, YTDTaxesPaid);
    }

    /**
     * Converts the employee's data to a CSV string.
     *
     * Format: employee_type,name,ID,payRate,pretaxDeductions,YTDEarnings,YTDTaxesPaid
     *
     * @return A CSV-formatted string representing the employee
     */
    @Override
    public String toCSV() {
        return String.format("%s,%s,%s,%.2f,%.2f,%.2f,%.2f",
                getEmployeeType(), name, id, payRate, pretaxDeductions, YTDEarnings, YTDTaxesPaid);
    }

    /**
     * Rounds a given double value to two decimal places using HALF_UP rounding mode.
     *
     * @param value The value to be rounded
     * @return The rounded value to two decimal places
     */
    protected static double round(double value) {
        return new BigDecimal(value).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }
}
