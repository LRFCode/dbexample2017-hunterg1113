package models;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.List;


@Entity
public class FullEmployee
{
    @Id
    private int employeeId;
    private String titleOfCourtesy;
    private String lastName;
    private String firstName;
    private String title;
    private BigDecimal salary;
    private String reportsToLastName;
    private String reportsToFirstName;

    public FullEmployee(int employeeId, String titleOfCourtesy, String lastName, String firstName, String title, BigDecimal salary, String reportsToLastName, String reportsToFirstName)
    {
        this.employeeId = employeeId;
        this.titleOfCourtesy = titleOfCourtesy;
        this.lastName = lastName;
        this.firstName = firstName;
        this.title = title;
        this.salary = salary;
        this.reportsToLastName = reportsToLastName;
        this.reportsToFirstName = reportsToFirstName;
    }

    public int getEmployeeId()
    {
        return employeeId;
    }

    public String getTitleOfCourtesy()
    {
        return titleOfCourtesy;
    }

    public String getLastName()
    {
        return lastName;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public String getFullName()
    {
        return firstName + " " + lastName;
    }

    public String getTitle()
    {
        return title;
    }

    public BigDecimal getSalary()
    {
        return salary;
    }

    public BigDecimal getSalaryRounded()
    {
        return salary.setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    public String getReportsToLastName()
    {
        return reportsToLastName;
    }

    public String getReportsToFirstName()
    {
        return reportsToFirstName;
    }

    public String getReportsToFullName()
    {
        return reportsToFirstName + " " + reportsToLastName;
    }
}