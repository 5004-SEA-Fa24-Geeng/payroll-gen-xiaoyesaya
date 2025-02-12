# Payroll Generator Design Document


This document is meant to provide a tool for you to demonstrate the design process. You need to work on this before you code, and after have a finished product. That way you can compare the changes, and changes in design are normal as you work through a project. It is contrary to popular belief, but we are not perfect our first attempt. We need to iterate on our designs to make them better. This document is a tool to help you do that.


## (INITIAL DESIGN): Class Diagram

Place your class diagram below. Make sure you check the fil in the browser on github.com to make sure it is rendering correctly. If it is not, you will need to fix it. As a reminder, here is a link to tools that can help you create a class diagram: [Class Resources: Class Design Tools](https://github.com/CS5004-khoury-lionelle/Resources?tab=readme-ov-file#uml-design-tools)
```mermaid
classDiagram
    AbstractEmployee <|-- HourlyEmployee
    AbstractEmployee <|-- SalaryEmployee
    IEmployee <|.. AbstractEmployee : implements
    IPayStub <|.. PayStub : implements
    ITimeCard <|.. TimeCard : implements
    PayrollGenerator ..> FileUtil : uses
    PayrollGenerator ..> Builder : uses
    PayrollGenerator ..> IPayStub : uses
    PayrollGenerator *-- Arguments : contains
    IEmployee ..> IPayStub : uses
    Builder ..> ITimeCard : creates
    Builder ..> IEmployee : creates
    class IEmployee {
        <<interface>>
        + getName() String
        + getID() String
        + getPayRate() double
        + getEmployeeType() String
        + getYTDEarnings() double
        + getYTDTaxesPaid() double
        + getPretaxDeductions() double
        + runPayroll(double hoursWorked) IPayStub
        + toCSV() String
    }
    class IPayStub {
        <<interface>>
        + getPay() double
        + getTaxesPaid() double
        + toCSV() String

    }
    class ITimeCard{
        <<interface>>
        + getEmployeeID() String
        + getHoursWorked() double
    }
    class FileUtil{
        - FileUtil
        + static final String EMPLOYEE_HEADER()
        + static final String PAY_STUB_HEADER()
        + static readFileToList(String file) List~String~
        + static writeFile(String outFile, List<String> lines) void
        + static writeFile(String outFile, List<String> lines, boolean backup) void
    }
    class Builder{
        - Builder
        + static buildEmployeeFromCSV(String csv) IEmployee
        + static buildTimeCardFromCSV(String csv) ITimeCard 

    }
    class AbstractEmployee{
        <<abstract>>
        # name: String
        # id: String
        # payRate: double
        # ytdEarnings: double
        # ytdTaxesPaid: double
        # pretaxDeductions: double
        # calculateGrossPay: double
        + getName() String
        + getID() String
        + getPayRate() double
        + getEmployeeType() String
        + getYTDEarnings() double
        + getYTDTaxesPaid() double
        + getPretaxDeductions() double
        + runPayroll(hoursWorked: double) IPayStub
    }
    class HourlyEmployee{
        # calculateGrossPay: double
        + HourlyEmployee(String name, String id, double payRate, double ytdEarnings, double ytdTaxesPaid, double pretaxDeductions)
        + getEmployeeType() String
    }
    class SalaryEmployee{
        # calculateGrossPay: double
        + SalaryEmployee(String name, String id, double payRate, double ytdEarnings, double ytdTaxesPaid, double pretaxDeductions)
        + getEmployeeType() String
    }
    class PayStub{
        - employeeName: String
        - netPay: double
        - taxesPaid: double
        - ytdEarnings: double
        - ytdTaxesPaid: double
        + PayStub(String employeeName, double netPay, double taxesPaid, double ytdEarnings, double ytdTaxesPaid)
        + getPay() double
        + getTaxesPaid() double
        + toCSV() String
    }
    class TimeCard {
        - employeeID: String
        - hoursWorked: double
        + TimeCard(String employeeID, double hoursWorked)
        + getEmployeeID() String
        + getHoursWorked() double
    }
    class PayrollGenerator {
        - DEFAULT_EMPLOYEE_FILE: String
        - DEFAULT_PAYROLL_FILE: String
        - DEFAULT_TIME_CARD_FILE: String
        - PayrollGenerator
        + main(args: String[]) void
    }
    class Arguments {
        - employeeFile: String
        - payrollFile: String
        - timeCards: String
        - Arguments
        + getEmployeeFile() String
        + getPayrollFile() String
        + getTimeCards() String
        + printHelp() void
        + process(args: String[]) Arguments
    }

```



## (INITIAL DESIGN): Tests to Write - Brainstorm

Write a test (in english) that you can picture for the class diagram you have created. This is the brainstorming stage in the TDD process. 

> [!TIP]
> As a reminder, this is the TDD process we are following:
> 1. Figure out a number of tests by brainstorming (this step)
> 2. Write **one** test
> 3. Write **just enough** code to make that test pass
> 4. Refactor/update  as you go along
> 5. Repeat steps 2-4 until you have all the tests passing/fully built program

You should feel free to number your brainstorm. 

1. Test that the `Employee` class properly returns `name` from `getName()`
2. Test that the `Employee` class properly returns `id` from `getId()`
3. Test that the `Employee` class properly returns `payRate` from `getPayRate()`
4. Test that the `Employee` class properly returns `employeeType` from `getEmployeeType()`
5. Test that the `Employee` class properly returns `YTDEarnings` from `getYTDEarnings()`
6. Test that the `Employee` class properly returns `YTDTaxesPaid` from `getYTDTaxesPaid()`
7. Test that the `Employee` class properly returns `pretaxDeductions` from `getPretaxDeductions()`
8. Test that the `PayStub` class properly returns `netPay` from `getPay()`
9. Test that the `PayStub` class properly returns `taxesPaid` from `getTaxesPaid()`
10. Test that the `PayStub` class properly converts to CSV format from `toCSV()`
11. Test that the `TimeCard` class properly returns `employeeID` from `getEmployeeID()`
12. Test that the `TimeCard` class properly returns `hoursWorked` from `getHoursWorked()`
13. Test that the `Builder` class properly creates an HourlyEmployee from `buildEmployeeFromCSV()`
14. Test that the `Builder` class properly creates a SalaryEmployee from `buildEmployeeFromCSV()`
15. Test that the `Builder` class properly throws an error for invalid data in `buildEmployeeFromCSV()` as well as `buildTimeCardFromCSV()`
16. Test that the `Builder` class properly creates a TimeCard from `buildTimeCardFromCSV()`
17. Test that the `PayrollGenerator` class properly skips employees with negative hours.
18. Test that the `PayrollGenerator` class properly processes employees and time cards to generate pay stubs.



## (FINAL DESIGN): Class Diagram

Go through your completed code, and update your class diagram to reflect the final design. Make sure you check the file in the browser on github.com to make sure it is rendering correctly. It is normal that the two diagrams don't match! Rarely (though possible) is your initial design perfect. 

> [!WARNING]
> If you resubmit your assignment for manual grading, this is a section that often needs updating. You should double check with every resubmit to make sure it is up to date.

```mermaid
classDiagram
    AbstractEmployee <|-- HourlyEmployee
    AbstractEmployee <|-- SalaryEmployee
    IEmployee <|.. AbstractEmployee : implements
    IPayStub <|.. PayStub : implements
    ITimeCard <|.. TimeCard : implements
    PayrollGenerator ..> FileUtil : uses
    PayrollGenerator ..> Builder : uses
    PayrollGenerator ..> IPayStub : uses
    PayrollGenerator *-- Arguments : contains
    IEmployee ..> IPayStub : uses
    Builder ..> ITimeCard : creates
    Builder ..> IEmployee : creates
    class IEmployee {
        <<interface>>
        + getName() String
        + getID() String
        + getPayRate() double
        + getEmployeeType() String
        + getYTDEarnings() double
        + getYTDTaxesPaid() double
        + getPretaxDeductions() double
        + runPayroll(double hoursWorked) IPayStub
        + toCSV() String
    }
    class IPayStub {
        <<interface>>
        + getPay() double
        + getTaxesPaid() double
        + toCSV() String

    }
    class ITimeCard{
        <<interface>>
        + getEmployeeID() String
        + getHoursWorked() double
    }
    class FileUtil{
        - FileUtil
        + static final String EMPLOYEE_HEADER()
        + static final String PAY_STUB_HEADER()
        + static readFileToList(String file) List~String~
        + static writeFile(String outFile, List<String> lines) void
        + static writeFile(String outFile, List<String> lines, boolean backup) void
    }
    class Builder{
        - Builder
        + static buildEmployeeFromCSV(String csv) IEmployee
        + static buildTimeCardFromCSV(String csv) ITimeCard 

    }
    class AbstractEmployee{
        <<abstract>>
        - name: String
        - id: String
        - payRate: double
        - ytdEarnings: double
        - ytdTaxesPaid: double
        - pretaxDeductions: double
        - TAX_RATE: double
        # calculateGrossPay: double
        # round: double
        + getName() String
        + getID() String
        + getPayRate() double
        + getEmployeeType() String
        + getYTDEarnings() double
        + getYTDTaxesPaid() double
        + getPretaxDeductions() double
        + runPayroll(hoursWorked: double) IPayStub
        + toCSV() String
    }
    class HourlyEmployee{
        # calculateGrossPay: double
        + HourlyEmployee(String name, String id, double payRate, double ytdEarnings, double ytdTaxesPaid, double pretaxDeductions)
        + getEmployeeType() String
    }
    class SalaryEmployee{
        # calculateGrossPay: double
        + SalaryEmployee(String name, String id, double payRate, double ytdEarnings, double ytdTaxesPaid, double pretaxDeductions)
        + getEmployeeType() String
    }
    class PayStub{
        - employeeName: String
        - netPay: double
        - taxesPaid: double
        - ytdEarnings: double
        - ytdTaxesPaid: double
        + PayStub(String employeeName, double netPay, double taxesPaid, double ytdEarnings, double ytdTaxesPaid)
        + getPay() double
        + getTaxesPaid() double
        + toCSV() String
    }
    class TimeCard {
        - employeeID: String
        - hoursWorked: double
        + TimeCard(String employeeID, double hoursWorked)
        + getEmployeeID() String
        + getHoursWorked() double
    }
    class PayrollGenerator {
        - DEFAULT_EMPLOYEE_FILE: String
        - DEFAULT_PAYROLL_FILE: String
        - DEFAULT_TIME_CARD_FILE: String
        - PayrollGenerator
        + main(args: String[]) void
    }
    class Arguments {
        - employeeFile: String
        - payrollFile: String
        - timeCards: String
        - Arguments
        + getEmployeeFile() String
        + getPayrollFile() String
        + getTimeCards() String
        + printHelp() void
        + process(args: String[]) Arguments
    }
```



## (FINAL DESIGN): Reflection/Retrospective

> [!IMPORTANT]
> The value of reflective writing has been highly researched and documented within computer science, from learning new information to showing higher salaries in the workplace. For this next part, we encourage you to take time, and truly focus on your retrospective.

Take time to reflect on how your design has changed. Write in *prose* (i.e. do not bullet point your answers - it matters in how our brain processes the information). Make sure to include what were some major changes, and why you made them. What did you learn from this process? What would you do differently next time? What was the most challenging part of this process? For most students, it will be a paragraph or two. 

I didn't make any large changes to the whole design. The changes are made in abstractEmployee class, the autograde asks me to use private for fields instead of protected. Therefore, I change all of them to private. Since I have runPayRoll in abstractEmployee class, I also assigned the TAX_RATE as a final constant to avoid magic number. Also to apply Bigdecimal and convert to round, I added a round method. Also, I forgot to include toCSV() in abstractEmployee class considering how it implements the IEmployee.
From this process, i learned that the the first draft will not be perfect and there are many adjustments that will be made. For next time, I wonder if it will be possible to have a payroll calculator outside the abstractemployee class to make the payroll calculation independently.