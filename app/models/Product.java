package models;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "Product")
public class Product
{
    @Id
    @Column(name = "ProductID")
    private int productId;
    @Column(name = "ProductName")
    private String ProductName;
    @Column(name = "UnitPrice")
    private BigDecimal unitPrice;
    @Column(name = "UnitsInStock")
    private int unitsInStock;
    @Column(name = "SupplierId")
    private Integer supplierId;
    @Column(name = "CategoryId")
    private Integer categoryId;
    @ManyToOne
    @JoinColumn(name = "supplierId")
    private Supplier supplier;

    public int getProductId()
    {
        return productId;
    }

    public void setProductId(int productId)
    {
        this.productId = productId;
    }

    public String getProductName()
    {
        return ProductName;
    }

    public void setProductName(String productName)
    {
        ProductName = productName;
    }

    public BigDecimal getUnitPrice()
    {
        return unitPrice;
    }

    public BigDecimal getRoundedUnitPrice()
    {
        return unitPrice.setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    public void setUnitPrice(BigDecimal unitPrice)
    {
        this.unitPrice = unitPrice;
    }

    public int getUnitsInStock()
    {
        return unitsInStock;
    }

    public void setUnitsInStock(int unitsInStock)
    {
        this.unitsInStock = unitsInStock;
    }

    public Integer getSupplierId()
    {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId)
    {
        this.supplierId = supplierId;
    }

    public Integer getCategoryId()
    {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId)
    {
        this.categoryId = categoryId;
    }

    public Supplier getSupplier()
    {
        return supplier;
    }

    public void setSupplier(Supplier supplier)
    {
        this.supplier = supplier;
    }
}
