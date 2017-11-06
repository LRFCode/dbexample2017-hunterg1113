package models;

import java.util.List;

public class ForemanSummary
{
    private int listNo;
    private int employeeId;
    private String lastName;
    private String lastClockIn;
    private List<Actual> actuals;

    public int getListNo()
    {
        return listNo;
    }

    public void setListNo(int listNo)
    {
        this.listNo = listNo;
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

    public String getLastClockIn()
    {
        return lastClockIn;
    }

    public void setLastClockIn(String lastClockIn)
    {
        this.lastClockIn = lastClockIn;
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