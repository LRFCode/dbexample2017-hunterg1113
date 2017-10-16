package models;

import javax.persistence.*;

@Entity
@Table(name = "EmployeeTerritory")
public class EmployeeTerritory
{
    @EmbeddedId
    CompoundKey compoundKey;
    @ManyToOne
    @JoinColumn(name = "employeeId")
    private Employee employee;

    public CompoundKey getCompoundKey()
    {
        return compoundKey;
    }

    public void setCompoundKey(CompoundKey compoundKey)
    {
        this.compoundKey = compoundKey;
    }

    public Employee getEmployee()
    {
        return employee;
    }

    public void setEmployee(Employee employee)
    {
        this.employee = employee;
    }
}
