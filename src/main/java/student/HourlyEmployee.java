package student;

/**
 * Represents an hourly employee, extending the AbstractEmployee class.
 * This class calculates gross pay based on regular and overtime hours.
 */
public class HourlyEmployee extends AbstractEmployee {
    /**
     * Constructs an HourlyEmployee object with the given details.
     *
     * @param name            The name of the employee.
     * @param id              The unique ID of the employee.
     * @param payRate         The hourly pay rate of the employee.
     * @param ytdEarnings     The year-to-date earnings of the employee.
     * @param ytdTaxesPaid    The year-to-date taxes paid by the employee.
     * @param pretaxDeductions The pretax deductions for the employee.
     */
    public HourlyEmployee(String name, String id, double payRate,
                          double ytdEarnings, double ytdTaxesPaid,
                          double pretaxDeductions) {
        super(name, id, payRate, ytdEarnings, ytdTaxesPaid, pretaxDeductions);
    }

    /**
     * Returns the type of employee.
     *
     * @return The string "HOURLY".
     */
    @Override
    public String getEmployeeType() {
        return "HOURLY";
    }

    /**
     * Calculates the gross pay for the employee based on the number of hours worked.
     * Regular hours (up to 40) are paid at the normal rate, and overtime hours
     * (beyond 40) are paid at 1.5 times the normal rate.
     *
     * @param hoursWorked The number of hours worked in the pay period.
     * @return The rounded gross pay amount.
     */
    @Override
    protected double calculateGrossPay(double hoursWorked) {
        double payRate = getPayRate();
        if (hoursWorked <= 40) {
            return round(payRate * hoursWorked);
        } else {
            double regularPay = payRate * 40;
            double overtimePay = (payRate * 1.5) * (hoursWorked - 40);
            return round(regularPay + overtimePay);
        }
    }
}
