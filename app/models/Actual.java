package models;

import javax.persistence.*;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "Actual")
public class Actual
{
    @Id
    @Column(name = "ActualId")
    private int actualId;
    @Column(name = "EstimateId")
    private int estimateId;
    @Column(name = "EmployeeId")
    private int employeeId;
    @Column(name = "ActualDate")
    private String actualDate;
    @Column(name = "ActualHours")
    private BigDecimal actualHours;
    @ManyToOne
    @JoinColumn(name = "estimateId", insertable = false, updatable = false)
    private Estimate estimate;
    @ManyToOne
    @JoinColumn(name = "employeeId", insertable = false, updatable = false)
    private Employee employee;

    public int getActualId()
    {
        return actualId;
    }

    public void setActualId(int actualId)
    {
        this.actualId = actualId;
    }

    public int getEstimateId()
    {
        return estimateId;
    }

    public void setEstimateId(int estimateId)
    {
        this.estimateId = estimateId;
    }

    public int getEmployeeId()
    {
        return employeeId;
    }

    public void setEmployeeId(int employeeId)
    {
        this.employeeId = employeeId;
    }

    public String getActualDate()
    {
        return actualDate;
    }

    public void setActualDate(String actualDate)
    {
        this.actualDate = actualDate;
    }

    public BigDecimal getActualHours()
    {
        return actualHours;
    }

    public void setActualHours(BigDecimal actualHours)
    {
        this.actualHours = actualHours;
    }

    public Estimate getEstimate()
    {
        return estimate;
    }

    public void setEstimate(Estimate estimate)
    {
        this.estimate = estimate;
    }

    public Employee getEmployee()
    {
        return employee;
    }

    public void setEmployee(Employee employee)
    {
        this.employee = employee;
    }

    public BigDecimal getDifferenceFromEstimate()
    {
        return actualHours.subtract(estimate.getEstimateHours());
    }
}
