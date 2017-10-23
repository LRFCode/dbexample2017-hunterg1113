package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "Estimate")
public class Estimate
{
    @Id
    @Column(name = "EstimateId")
    private int estimateId;
    @Column(name = "ContractId")
    private int contractId;
    @Column(name = "CategoryId")
    private int categoryId;
    @Column(name = "EstimateHours")
    private BigDecimal estimateHours;
    @Column(name = "Plans")
    private byte[] plans;

    public int getEstimateId()
    {
        return estimateId;
    }

    public void setEstimateId(int estimateId)
    {
        this.estimateId = estimateId;
    }

    public int getContractId()
    {
        return contractId;
    }

    public void setContractId(int contractId)
    {
        this.contractId = contractId;
    }

    public int getCategoryId()
    {
        return categoryId;
    }

    public void setCategoryId(int categoryId)
    {
        this.categoryId = categoryId;
    }

    public BigDecimal getEstimateHours()
    {
        return estimateHours;
    }

    public void setEstimateHours(BigDecimal estimateHours)
    {
        this.estimateHours = estimateHours;
    }

    public byte[] getPlans()
    {
        return plans;
    }

    public void setPlans(byte[] plans)
    {
        this.plans = plans;
    }
}

