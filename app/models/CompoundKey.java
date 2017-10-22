package models;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Table;
import java.io.Serializable;

@Embeddable
public class CompoundKey implements Serializable
{
    @Column(name = "EmployeeId")
    private int employeeId;
    @Column(name = "TerritoryId")
    private int territoryId;

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

    public void setTerritoryId(int territoryId)
    {
        this.territoryId = territoryId;
    }
}
