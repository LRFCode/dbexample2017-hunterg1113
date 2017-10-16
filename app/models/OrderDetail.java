package models;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "OrderDetail")
public class OrderDetail
{
    @EmbeddedId
    CompoundKey compoundKey;
    @Column(name = "UnitPrice")
    private BigDecimal unitPrice;
    @Column(name = "Quantity")
    private int quantity;
    @ManyToOne
    private OrderHeader orderHeader;

    public CompoundKey getCompoundKey()
    {
        return compoundKey;
    }

    public void setCompoundKey(CompoundKey compoundKey)
    {
        this.compoundKey = compoundKey;
    }

    public BigDecimal getUnitPrice()
    {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice)
    {
        this.unitPrice = unitPrice;
    }

    public int getQuantity()
    {
        return quantity;
    }

    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }

    public OrderHeader getOrderHeader()
    {
        return orderHeader;
    }

    public void setOrderHeader(OrderHeader orderHeader)
    {
        this.orderHeader = orderHeader;
    }
}