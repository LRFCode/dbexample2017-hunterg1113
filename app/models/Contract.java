package models;

import javax.persistence.*;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Contract")
public class Contract
{
    @Id
    @Column(name = "ContractId")
    private int contractId;
    @Column(name = "ClientId")
    private int clientId;
    @Column(name = "StartDate")
    private String startDate;
    @Column(name = "CompletionDate")
    private String completionDate;
    @Column(name = "Plans")
    private byte[] plans;
    @OneToMany(mappedBy = "contract")
    private List<Estimate> estimates = new ArrayList<>();

    public int getContractId()
    {
        return contractId;
    }

    public void setContractId(int contractId)
    {
        this.contractId = contractId;
    }

    public int getClientId()
    {
        return clientId;
    }

    public void setClientId(int clientId)
    {
        this.clientId = clientId;
    }

    public String getStartDate()
    {
        return startDate;
    }

    public void setStartDate(String startDate)
    {
        this.startDate = startDate;
    }

    public String getCompletionDate()
    {
        return completionDate;
    }

    public void setCompletionDate(String completionDate)
    {
        this.completionDate = completionDate;
    }

    public byte[] getPlans()
    {
        return plans;
    }

    public void setPlans(byte[] plans)
    {
        this.plans = plans;
    }

    public List<Estimate> getEstimates()
    {
        return estimates;
    }

    public void setEstimates(List<Estimate> estimates)
    {
        this.estimates = estimates;
    }
}
