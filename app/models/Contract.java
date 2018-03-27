package models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Contract")
public class Contract {
    @Id
    @Column(name = "ContractId")
    private int contractId;

    @Column(name = "ClientId")
    private int clientId;

    @Column(name = "StartDate")
    private String startDate;

    @Column(name = "Completed")
    private int completed;

    @Column(name = "Plans")
    private byte[] plans;

    @OneToMany(mappedBy = "contract")
    private List<Estimate> estimates;

    @ManyToOne
    @JoinColumn(name = "clientId", insertable = false, updatable = false)
    private Client client;

    @OneToMany(mappedBy = "contract")
    private List<Equipment> equipments;

    public int getContractId()
    {
        return contractId;
    }

    public void setContractId(int contractId)
    {
        this.contractId = contractId;
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

    public byte[] getPlans()
    {
        return plans;
    }

    public List<Estimate> getEstimates()
    {
        return estimates;
    }

    public Client getClient()
    {
        return client;
    }

    public void setClient(Client client)
    {
        this.client = client;
    }
}
