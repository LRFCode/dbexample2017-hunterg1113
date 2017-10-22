package models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Supplier")
public class Supplier
{
    @Id
    @Column(name = "SupplierId")
    private int supplierId;
    @Column(name = "CompanyName")
    private String companyName;
    @OneToMany(mappedBy = "supplier")
    private List<Product> products = new ArrayList<>();

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

    public List<Product> getProducts()
    {
        return products;
    }

    public void setProducts(List<Product> products)
    {
        this.products = products;
    }
}
