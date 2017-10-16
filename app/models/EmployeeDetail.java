package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
public class EmployeeDetail
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

    public int getEmployeeId()
    {
        return employeeId;
    }

    public void setEmployeeId(int employeeId)
    {
        this.employeeId = employeeId;
    }

    public String getTitleOfCourtesy()
    {
        return titleOfCourtesy;
    }

    public void setTitleOfCourtesy(String titleOfCourtesy)
    {
        this.titleOfCourtesy = titleOfCourtesy;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getFullName()
    {
        return firstName + " " + lastName;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public BigDecimal getSalary()
    {
        return salary;
    }

    public BigDecimal getSalaryRounded()
    {
        return salary.setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    public void setSalary(BigDecimal salary)
    {
        this.salary = salary;
    }

    public String getReportsToLastName()
    {
        return reportsToLastName;
    }

    public void setReportsToLastName(String reportsToLastName)
    {
        this.reportsToLastName = reportsToLastName;
    }

    public String getReportsToFirstName()
    {
        return reportsToFirstName;
    }

    public void setReportsToFirstName(String reportsToFirstName)
    {
        this.reportsToFirstName = reportsToFirstName;
    }

    public String getReportsToFullName()
    {
        return reportsToFirstName + " " + reportsToLastName;
    }
}
