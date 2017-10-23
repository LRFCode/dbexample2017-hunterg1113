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
    @Column(name = "ForemanId")
    private int foremanId;
    @Column(name = "ActualDate")
    private String actualDate;
    @Column(name = "ActualHours")
    private BigDecimal actualHours;
    @ManyToOne
    @JoinColumn(name = "estimateId")
    private Estimate estimate;

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

    public int getForemanId()
    {
        return foremanId;
    }

    public void setForemanId(int foremanId)
    {
        this.foremanId = foremanId;
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
}
