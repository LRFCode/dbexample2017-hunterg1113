package models;

import javax.persistence.*;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Employee")
public class Employee
{
    @Id
    @Column (name = "EmployeeId")
    private int employeeId;
    @Column (name = "LastName")
    private String lastName;
    @Column (name = "FirstName")
    private String firstName;
    @Column (name = "Title")
    private String title;
    @Column (name = "HourlyWage")
    private BigDecimal hourlyWage;
    @Column (name = "HireDate")
    private String hireDate;
    @OneToMany (mappedBy = "employee")
    private List<ProjectUser> projectUsers;
    @OneToMany (mappedBy = "employee")
    private List<Actual> actuals;

    public int getEmployeeId()
    {
        return employeeId;
    }

    public void setEmployeeId(int employeeId)
    {
        this.employeeId = employeeId;
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

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public BigDecimal getHourlyWage()
    {
        return hourlyWage;
    }

    public void setHourlyWage(BigDecimal hourlyWage)
    {
        this.hourlyWage = hourlyWage;
    }

    public String getHireDate()
    {
        return hireDate;
    }

    public void setHireDate(String hireDate)
    {
        this.hireDate = hireDate;
    }

    public List<ProjectUser> getProjectUsers()
    {
        return projectUsers;
    }

    public void setProjectUsers(List<ProjectUser> projectUsers)
    {
        this.projectUsers = projectUsers;
    }

    public List<Actual> getActuals()
    {
        return actuals;
    }

    public void setActuals(List<Actual> actuals)
    {
        this.actuals = actuals;
    }
}
