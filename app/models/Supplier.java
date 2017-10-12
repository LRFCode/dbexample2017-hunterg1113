package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Supplier")
public class Supplier
{
    @Id
    @Column(name = "SupplierId")
    private int supplierId;
    @Column(name = "CompanyName")
    private String companyName;

    public int getSupplierId()
    {
        return supplierId;
    }

    public void setSupplierId(int supplierId)
    {
        this.supplierId = supplierId;
    }

    public String getCompanyName()
    {
        return companyName;
    }

    public void setCompanyName(String companyName)
    {
        this.companyName = companyName;
    }
}
