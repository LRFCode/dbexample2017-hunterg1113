package models;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
public class AvgEstHoursPerCat
{
    @Id
    private int categoryId;
    private String categoryName;
    private BigDecimal estimateHours;
    private BigDecimal actualHours;

    public int getCategoryId()
    {
        return categoryId;
    }

    public void setCategoryId(int categoryId)
    {
        this.categoryId = categoryId;
    }

    public String getCategoryName()
    {
        return categoryName;
    }

    public void setCategoryName(String categoryName)
    {
        this.categoryName = categoryName;
    }

    public BigDecimal getEstimateHours()
    {
        return estimateHours;
    }

    public BigDecimal getEstimateHoursRounded()
    {
        return estimateHours.setScale(2,BigDecimal.ROUND_HALF_UP);
    }

    public void setEstimateHours(BigDecimal estimateHours)
    {
        this.estimateHours = estimateHours;
    }

    public BigDecimal getActualHours()
    {
        return actualHours;
    }

    public void setActualHours(BigDecimal actualHours)
    {
        this.actualHours = actualHours;
    }
}
