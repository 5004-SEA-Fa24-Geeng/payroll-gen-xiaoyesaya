package student;

/**
 * Represents a time card that tracks the number of hours worked by an employee.
 */
public class TimeCard implements ITimeCard {
    /** The unique ID of the employee. */
    private final String employeeID;
    /** The hours worked by employee. */
    private final double hoursWorked;
    /**
     * Constructs a TimeCard with the given employee ID and hours worked.
     *
     * @param employeeID   The unique ID of the employee.
     * @param hoursWorked  The number of hours worked by employee
     */
    public TimeCard(String employeeID, double hoursWorked) {
        this.employeeID = employeeID;
        this.hoursWorked = hoursWorked;
    }

    /**
     * Gets the employee ID.`1`
     *
     * @return The employee ID as a string.
     */
    @Override
    public String getEmployeeID() {
        return employeeID;
    }

    /**
     * Gets the number of hours worked recorded in this time card.
     *
     * @return The number of hours worked.
     */
    @Override
    public double getHoursWorked() {
        return hoursWorked;
    }
}
