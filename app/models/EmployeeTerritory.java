package models;

import javax.persistence.*;

@Entity
@Table(name = "EmployeeTerritory")
public class EmployeeTerritory
{
    @Id
    @Column(name = "EmployeeId")
    private int employeeId;
    @Column(name = "TerritoryId")
    private Integer territoryId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employeeid")
    private Employee employee;

    public int getEmployeeId()
    {
        return employeeId;
    }

    public void setEmployeeId(int employeeId)
    {
        this.employeeId = employeeId;
    }

    public int getTerritoryId()
    {
        return territoryId;
    }

    public void setTerritoryId(Integer territoryId)
    {
        this.territoryId = territoryId;
    }
}
