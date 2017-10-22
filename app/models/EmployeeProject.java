package models;

import javax.persistence.*;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Employee")
public class EmployeeProject
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
    @Column (name = "Salary")
    private BigDecimal salary;
    @Column (name = "HireDate")
    private String hireDate;
    @OneToMany(mappedBy = "employeeProject")
    private List<ProjectUser> projectUsers;



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

    public BigDecimal getSalary()
    {
        return salary;
    }

    public void setSalary(BigDecimal salary)
    {
        this.salary = salary;
    }

    public String getHireDate()
    {
        return hireDate;
    }

    public void setHireDate(String hireDate)
    {
        this.hireDate = hireDate;
    }
}
