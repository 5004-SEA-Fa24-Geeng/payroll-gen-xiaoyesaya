package student;

/**
 * Represents a salary employee, extending the AbstractEmployee class.
 */
public class SalaryEmployee extends AbstractEmployee {
    /**
     * Constructs an HourlyEmployee object with the given details.
     *
     * @param name            The name of the employee.
     * @param id              The unique ID of the employee.
     * @param payRate         The hourly pay rate of the employee.
     * @param YTDEarnings     The year-to-date earnings of the employee.
     * @param YTDTaxesPaid    The year-to-date taxes paid by the employee.
     * @param pretaxDeductions The pretax deductions for the employee.
     */
    public SalaryEmployee(String name, String id, double payRate, double YTDEarnings, double YTDTaxesPaid, double pretaxDeductions){
        super(name, id, payRate, YTDEarnings, YTDTaxesPaid, pretaxDeductions);
    }

    /**
     * Returns the type of employee.
     *
     * @return The string "SALARY"
     */
    @Override
    public String getEmployeeType() {
        return "SALARY";
    }

    /**
     * Calculates the gross pay for a salaried employee per pay period.
     * Salaried employees receive their salary divided into 24 equal payments (bi-monthly pay schedule).
     * @param hoursWorked Number of hours worked in the pay period
     * @return The rounded gross pay amount
     */
    @Override
    protected double calculateGrossPay(double hoursWorked) {
        return round(payRate / 24);
    }
}
