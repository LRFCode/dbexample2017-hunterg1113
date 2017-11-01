package models;

import javax.persistence.*;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.List;

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
    @Column(name = "CompletionDate")
    private String completionDate;
    @ManyToOne
    @JoinColumn(name = "contractId", insertable = false, updatable = false)
    private Contract contract;
    @OneToMany(mappedBy = "estimate")
    private List<Actual> actuals;
    @ManyToOne
    @JoinColumn(name = "categoryId", insertable = false, updatable = false)
    private Category category;

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

    public Contract getContract()
    {
        return contract;
    }

    public void setContract(Contract contract)
    {
        this.contract = contract;
    }

    public List<Actual> getActuals()
    {
        return actuals;
    }

    public void setActuals(List<Actual> actuals)
    {
        this.actuals = actuals;
    }

    public Category getCategory()
    {
        return category;
    }

    public void setCategory(Category category)
    {
        this.category = category;
    }

    public String getCompletionDate()
    {
        return completionDate;
    }

    public void setCompletionDate(String completionDate)
    {
        this.completionDate = completionDate;
    }


}

