package student;

/**
 * Represents a pay stub, containing payroll details for an employee.
 * Implements the IPayStub interface.
 */
public class PayStub implements IPayStub {
    /** The name of the employee. */
    private final String employeeName;
    /** The net pay received by the employee for the pay period. */
    private final double netPay;
    /** The amount of taxes paid for the pay period. */
    private final double taxesPaid;
    /** The employee's year-to-date earnings. */
    private final double ytdEarnings;
    /** The employee's year-to-date taxes paid. */
    private final double ytdTaxesPaid;

    /**
     * Constructs a PayStub object with the given payroll details.
     *
     * @param employeeName The name of the employee.
     * @param netPay The net pay received by the employee for the pay period.
     * @param taxesPaid The amount of taxes paid for the pay period.
     * @param ytdEarnings The employee's year-to-date earnings.
     * @param ytdTaxesPaid The employee's year-to-date taxes paid.
     */
    public PayStub(String employeeName, double netPay, double taxesPaid, double ytdEarnings, double ytdTaxesPaid) {
        this.employeeName = employeeName;
        this.netPay = netPay;
        this.taxesPaid = taxesPaid;
        this.ytdEarnings = ytdEarnings;
        this.ytdTaxesPaid = ytdTaxesPaid;
    }

    /**
     * Retrieves the net pay for the current pay period.
     *
     * @return The net pay.
     */
    @Override
    public double getPay() {
        return netPay;
    }

    /**
     * Retrieves the taxes paid for the current pay period.
     *
     * @return The amount of taxes paid.
     */
    @Override
    public double getTaxesPaid() {
        return taxesPaid;
    }

    /**
     * Converts the PayStub object to a CSV-formatted string.
     *
     * The format of the CSV string is:
     * "employee_name,net_pay,taxes,ytd_earnings,ytd_taxes_paid"
     *
     * @return A CSV representation of the pay stub.
     */
    @Override
    public String toCSV() {
        return String.format("%s,%.2f,%.2f,%.2f,%.2f",
                employeeName, netPay, taxesPaid, YTDEarnings, YTDTaxesPaid);
    }

}
