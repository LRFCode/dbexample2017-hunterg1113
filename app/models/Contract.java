package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

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
    @Column(name = "Photos")
    private byte[] photos;

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

    public byte[] getPhotos()
    {
        return photos;
    }

    public void setPhotos(byte[] photos)
    {
        this.photos = photos;
    }
}
