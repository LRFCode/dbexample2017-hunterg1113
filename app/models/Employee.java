package models;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Id;

@Entity
@Table(name = "Employee")
public class Employee
{
    @Id
    @Column(name = "EmployeeId")
    private int employeeId;
    @Column(name = "LastName")
    private String lastName;
    @Column(name = "FirstName")
    private String firstName;
    @Column(name = "BirthDate")
    private String birthDate;
    @Column(name = "Title")
    private String title;
    @Column(name = "TitleOfCourtesy")
    private String titleOfCourtesy;
    @Column(name = "Salary")
    private BigDecimal salary;
    @Column(name = "ReportsTo")
    private Integer reportsTo;
    @Column(name = "Photo")
    private byte[] photo;
    @Column(name = "Password")
    private byte[] password;
    @Column (name = "Salt")
    private byte[] salt;
    @OneToMany(mappedBy = "employee")
    private List<EmployeeTerritory> employeeTerritories = new ArrayList<>();

    public String getBirthDate()
    {
        return birthDate;
    }

    public void setBirthDate(String birthDate)
    {
        this.birthDate = birthDate;
    }

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

    public String getFullName()
    {
        return titleOfCourtesy + " " + firstName + " " + lastName;

    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getTitleOfCourtesy()
    {
        return titleOfCourtesy;
    }

    public void setTitleOfCourtesy(String titleOfCourtesy)
    {
        this.titleOfCourtesy = titleOfCourtesy;
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

    public Integer getReportsTo()
    {
        return reportsTo;
    }

    public void setReportsTo(Integer reportsTo)
    {
        this.reportsTo = reportsTo;
    }

    public String getReportsToLastName(List<Employee> employees)
    {
        for (Employee employee : employees)
        {
            if (employee.employeeId == reportsTo)
            {
                return employee.lastName + ", " + employee.firstName;
            }
        }

        return "no supervisor assigned";
    }

    public List<EmployeeTerritory> getEmployeeTerritories()
    {
        return employeeTerritories;
    }

    public void setEmployeeTerritories(List<EmployeeTerritory> employeeTerritories)
    {
        this.employeeTerritories = employeeTerritories;
    }

    public byte[] getPhoto()
    {
        return photo;
    }

    public void setPhoto(byte[] photo)
    {
        this.photo = photo;
    }

    public byte[] getPassword()
    {
        return password;
    }

    public void setPassword(byte[] password)
    {
        this.password = password;
    }

    public byte[] getSalt()
    {
        return salt;
    }

    public void setSalt(byte[] salt)
    {
        this.salt = salt;
    }
}
