# Report for Payroll Generator

This report helps you demonstrate your understanding of the concepts. You should write this report after you have completed the project. 

## Technical Questions

1. What does CSV stand for? 

   CSV stands for comma-separated values. It is a file format used to store table of data, where each value is separated by a comma.
2. Why would you declare `List<IEmployee>` instead of `ArrayList<HourlyEmployee>`?

   Declaring List<IEmployee> instead of ArrayList<HourlyEmployee> allows for flexibility and polymorphism. This way, the list can store different types of employees (e.g., HourlyEmployee, SalariedEmployee), making the code more adaptable and easier to maintain.

3. When you have one class referencing another object, such as storing that object as one of the attributes of the first class - what type of relationship is that called (between has-a and is-a)?

   This type of relationship is called a has-a relationship. It means that one class contains an instance of another class as an attribute rather than inheriting from it.

4. Can you provide an example of a has-a relationship in your code (if one exists)?

   The AbstractEmployee class has a has-a relationship with the PayStub class since the runPayroll method creates and returns a PayStub object.

5. Can you provide an example of an is-a relationship in your code (if one exists)?

   There is a is-a relationship between HourlyEmployee and AbstractEmployee because HourlyEmployee extends AbstractEmployee. This means HourlyEmployee is-a type of AbstractEmployee.
6. What is the difference between an interface and an abstract class?

    While class can inherit from only one abstract class, it can implement multiple interfaces. Moreover, abstract class contain both abstract and concrete methods with detailed implementations. Interface only has abstract methods which force a class must implement.
    
7. What is the advantage of using an interface over an abstract class?

   The advantage of using an interface over an abstract class is that a class can implement multiple interfaces but only can extend one abstract class. This allows for more flexibility and code reusability when different classes need to follow the same contract but are not related by inheritance.

8. Is the following code valid or not? `List<int> numbers = new ArrayList<int>();`, explain why or why not. If not, explain how you can fix it. 

    Not Valid. For java generics, primitive types are not allowed (in this case, int). Instead, we can use Integer, then it becomes: List<Integer> numbers = new ArrayList<Integer>() 
9. Which class/method is described as the "driver" for your application?

   The main method insides the main program (in this program: PayrollGenerator) is the driver.

10. How do you create a temporary folder for JUnit Testing? 

    We can create a temporary folder using @TempDir annotation.


## Deeper Thinking 

Salary Inequality is a major issue in the United States. Even in STEM fields, women are often paid less for [entry level positions](https://www.gsb.stanford.edu/insights/whats-behind-pay-gap-stem-jobs). However, not paying equal salary can hurt representation in the field, and looking from a business perspective, can hurt the company's bottom line has diversity improves innovation and innovation drives profits. 

Having heard these facts, your employer would like data about their salaries to ensure that they are paying their employees fairly. While this is often done 'after pay' by employee surveys and feedback, they have the idea that maybe the payroll system can help them ensure that they are paying their employees fairly. They have given you free reign to explore this idea.

Think through the issue / making sure to cite any resources you use to help you better understand the topic. Then write a paragraph on what changes you would need to make to the system. For example, would there be any additional data points you would need to store in the employee file? Why? Consider what point in the payroll process you may want to look at the data, as different people could have different pretax benefits and highlight that. 

The answer to this is mostly open. We ask that you cite at least two sources to show your understanding of the issue. The TAs will also give feedback on your answer, though will be liberal in grading as long as you show a good faith effort to understand the issue and making an effort to think about how your design to could help meet your employer's goals of salary equity. 


The fact that the gender pay gap has remained unchanged for about twenty years, with women receiving only 80 percent of a man's salary, really surprised me [^1].
I believe that including demographic data points such as gender and years of experience in the payroll system will be beneficial in identifying whether there is a pay gap between different genders in a systematic way.
I think employee salary comparisons should be analyzed right after calculating gross pay, before applying any pretax deductions or taxes, because we are comparing employees' base salaries and do not want interference from other benefits.
Moreover, research notes that women are less likely to get promoted when starting from the same level of position [^2]. Therefore, we can add a function to track salary changes over the years. In this way, we can spot not only pay gaps right away but also how salaries change over time for different groups. This will help make sure everyone gets fair raises and promotions, preventing hidden biases from growing into significant wage gaps.
## References

[^1]: Gender pay gap in U.S. hasn’t changed much in two decades. https://www.pewresearch.org/short-reads/2023/03/01/gender-pay-gap-facts/. Accessed: 2025-02-08.
[^2]: A ‘gender promotion gap’ fuels wealth inequality between men and women, Yale professor says https://www.cnbc.com/2024/09/25/gender-promotion-gap-fuels-wealth-inequality-yale-professor-says.html#:~:text=Women%20are%20about%2013%25%20less,men%20and%20women%2C%20she%20said. Accessed: 2025-02-08.