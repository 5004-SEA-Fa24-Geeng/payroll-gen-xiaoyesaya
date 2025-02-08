package student;

/** 
 * This is a static class (essentially functions) that will help you build objects from CSV strings.
 * These objects are then used in the rest of the program. Often these builders are associated
 * with the objects themselves and the concept of a factory, but we placed
 * them here to keep the code clean (and to help guide you).
 */
public final class Builder {
    
    private Builder() {
    }


     /**
     * Builds an employee object from a CSV string.
     * 
     * You may end up checking the type of employee (hourly or salary) by looking at the first
     * element of the CSV string. Then building an object specific to that type.
     * 
     * @param csv the CSV string
     * @return the employee object
     */
    public static IEmployee buildEmployeeFromCSV(String csv) {

        String[] parts = csv.split(",");
        if (parts.length != 7) {
            throw new IllegalArgumentException("Invalid CSV format");
        }
        String type = parts[0];
        String name = parts[1];
        String id = parts[2];
        double payRate, pretaxDeduction, YTDEarnings, YTDTaxesPaid;
        try{
            payRate = Double.parseDouble(parts[3]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid pay rate");
        }
        try{
            pretaxDeduction = Double.parseDouble(parts[4]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid pretax deduction");
        }
        try{
            YTDEarnings = Double.parseDouble(parts[5]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid YTDEarnings");
        }
        try{
            YTDTaxesPaid = Double.parseDouble(parts[6]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid YTDTaxesPaid");
        }


        if (type.equals("HOURLY")) {
            return new HourlyEmployee(name, id, payRate, YTDEarnings, YTDTaxesPaid, pretaxDeduction);
        } else if (type.equals("SALARY")) {
            return new SalaryEmployee(name, id, payRate, YTDEarnings, YTDTaxesPaid, pretaxDeduction);
        } else {
            throw new IllegalArgumentException("Invalid type");
        }
    }



   /**
     * Converts a TimeCard from a CSV String.
     * 
     * @param csv csv string
     * @return a TimeCard object
     */
    public static ITimeCard buildTimeCardFromCSV(String csv) {

        String[] parts = csv.split(",");
        if (parts.length != 2) {
            throw new IllegalArgumentException("Invalid CSV format");
        }
        String employeeID = parts[0];
        double hoursWorked;
        try{
            hoursWorked = Double.parseDouble(parts[1]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid hours worked");
        }
        return new TimeCard(employeeID, hoursWorked);
    }
}
