package models;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;

@Entity
public class EmployeeId
{
    @Id
    private int employeeId;

    public int getEmployeeId()
    {
        return employeeId;
    }

    public void setEmployeeId(int employeeId)
    {
        this.employeeId = employeeId;
    }
}
