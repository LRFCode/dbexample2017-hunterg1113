package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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
    private int contractId;

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

    public int getContractId()
    {
        return contractId;
    }

    public void setContractId(int contractId)
    {
        this.contractId = contractId;
    }
}
