package models;

import javax.persistence.*;
import javax.persistence.Id;

@Entity
@Table(name = "Equipment")
public class Equipment
{
    @Id
    @Column(name = "EquipmentId")
    private int equipmentId;
    @Column(name = "EquipmentName")
    private String equipmentName;
    @Column(name = "EquipmentHours")
    private Integer equipmentHours;
    @Column(name = "ContractId")
    private Integer contractId;
    @ManyToOne
    @JoinColumn(name = "contractId")
    private Contract contract;

    public int getEquipmentId()
    {
        return equipmentId;
    }

    public void setEquipmentId(int equipmentId)
    {
        this.equipmentId = equipmentId;
    }

    public String getEquipmentName()
    {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName)
    {
        this.equipmentName = equipmentName;
    }

    public Integer getEquipmentHours()
    {
        return equipmentHours;
    }

    public void setEquipmentHours(Integer equipmentHours)
    {
        this.equipmentHours = equipmentHours;
    }

    public Integer getContractId()
    {
        return contractId;
    }

    public void setContractId(Integer contractId)
    {
        this.contractId = contractId;
    }

    public Contract getContract()
    {
        return contract;
    }

    public void setContract(Contract contract)
    {
        this.contract = contract;
    }
}
