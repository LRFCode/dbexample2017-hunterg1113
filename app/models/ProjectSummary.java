package models;

import javax.persistence.Entity;
import java.math.BigDecimal;
import javax.persistence.Id;


@Entity
public class ProjectSummary
{
    @Id
    private int ContractId;
    private BigDecimal totalEstimateHours;
    private BigDecimal totalActualHours;

    public int getContractId()
    {
        return ContractId;
    }

    public void setContractId(int contractId)
    {
        ContractId = contractId;
    }

    public BigDecimal getTotalEstimateHours()
    {
        return totalEstimateHours;
    }

    public void setTotalEstimateHours(BigDecimal totalEstimateHours)
    {
        this.totalEstimateHours = totalEstimateHours;
    }

    public BigDecimal getTotalActualHours()
    {
        return totalActualHours;
    }

    public void setTotalActualHours(BigDecimal totalActualHours)
    {
        this.totalActualHours = totalActualHours;
    }
}
