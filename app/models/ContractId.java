package models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ContractId
{
    @Id
    private int contractId;

    public int getContractId()
    {
        return contractId;
    }

    public void setContractId(int contractId)
    {
        this.contractId = contractId;
    }
}
