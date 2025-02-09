package student;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Abstract class representing a general employee.
 *
 * This class implements common attributes and methods for all types of employees,
 * including calculation and data formatting.
 * It also defines an abstract method for calculating gross pay, which must be implemented by subclasses.
 */
public abstract class AbstractEmployee implements IEmployee {
    /** Employee's name. */
    private final String name;
    /** Employee's ID. */
    private final String id;
    /** Employee's pay rate. */
    private final double payRate;
    /** Year-to-date earnings. */
    private double ytdEarnings;
    /** Year-to-date taxes paid. */
    private double ytdTaxesPaid;
    /** Pre-tax deductions. */
    private final double pretaxDeductions;
    /** Fixed tax rate of 22.65%. */
    private static final double TAX_RATE = 0.2265;

    /**
     * Constructs an AbstractEmployee with specified details.
     *
     * @param name Employee's name.
     * @param id Employee's ID.
     * @param payRate Employee's pay rate (must be greater than 0).
     * @param ytdEarnings Year-to-date earnings (cannot be negative).
     * @param ytdTaxesPaid Year-to-date taxes paid (cannot be negative).
     * @param pretaxDeductions Pre-tax deductions (cannot be negative).
     * @throws IllegalArgumentException if any input is invalid.
     */
    public AbstractEmployee(
            String name, String id, double payRate, double ytdEarnings,
            double ytdTaxesPaid, double pretaxDeductions) {
        this.name = name;
        this.id = id;
        this.payRate = payRate;
        this.ytdEarnings = ytdEarnings;
        this.ytdTaxesPaid = ytdTaxesPaid;
        this.pretaxDeductions = pretaxDeductions;
    }

    /**
     * Gets the employee's name.
     *
     * @return The employee's name.
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Gets the employee's ID.
     *
     * @return The employee's ID.
     */
    @Override
    public String getID() {
        return id;
    }

    /**
     * Gets the employee's pay rate.
     *
     * @return The pay rate.
     */
    @Override
    public double getPayRate() {
        return payRate;
    }

    /**
     * Gets the employee's year-to-date earnings.
     *
     * @return The year-to-date earnings.
     */
    @Override
    public double getYTDEarnings() {
        return ytdEarnings;
    }

    /**
     * Gets the employee's year-to-date taxes paid.
     *
     * @return The year-to-date taxes paid.
     */
    @Override
    public double getYTDTaxesPaid() {
        return ytdTaxesPaid;
    }

    /**
     * Gets the employee's pre-tax deductions.
     *
     * @return The pre-tax deductions.
     */
    @Override
    public double getPretaxDeductions() {
        return pretaxDeductions;
    }

    /**
     * Abstract method to calculate gross pay based on hours worked.
     *
     * @param hoursWorked Number of hours worked in the pay period.
     * @return Gross pay for the period.
     */
    protected abstract double calculateGrossPay(double hoursWorked);

    /**
     * Runs payroll for the employee based on hours worked.
     *
     * If hours worked is negative, the method returns null.
     * Otherwise, it calculates gross pay, taxable income, taxes, and net pay,
     * and updates year-to-date earnings and taxes paid.
     *
     * @param hoursWorked Number of hours worked.
     * @return A PayStub containing payroll details.
     */
    @Override
    public IPayStub runPayroll(double hoursWorked) {
        if (hoursWorked < 0) {
            return null;
        }
        double grossPay = calculateGrossPay(hoursWorked);
        double taxableIncome = Math.max(grossPay - pretaxDeductions, 0);
        double taxes = taxableIncome * TAX_RATE;
        double netPay = grossPay - pretaxDeductions - taxes;
        taxes = round(taxes);
        netPay = round(netPay);
        ytdEarnings = round(ytdEarnings + grossPay);
        ytdTaxesPaid = round(ytdTaxesPaid + taxes);
        return new PayStub(name, netPay, taxes, ytdEarnings, ytdTaxesPaid);
    }

    /**
     * Converts the employee's data to a CSV string.
     *
     * Format: employee_type,name,ID,payRate,pretaxDeductions,ytdEarnings,ytdTaxesPaid.
     *
     * @return A CSV-formatted string representing the employee.
     */
    @Override
    public String toCSV() {
        return String.format("%s,%s,%s,%.2f,%.2f,%.2f,%.2f",
                getEmployeeType(), name, id, payRate, pretaxDeductions, ytdEarnings, ytdTaxesPaid);
    }

    /**
     * Rounds a given double value to two decimal places using HALF_UP rounding mode.
     *
     * @param value The value to be rounded.
     * @return The rounded value to two decimal places.
     */
    protected static double round(double value) {
        return new BigDecimal(value).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }
}
