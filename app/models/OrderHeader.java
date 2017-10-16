package models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "OrderHeader")
public class OrderHeader
{
    @Id
    @Column(name = "OrderId")
    private int orderId;
    @Column(name = "EmployeeId")
    private int employeeId;
    @Column(name = "OrderDate")
    private Date orderDate;
    @OneToMany(mappedBy = "orderHeader")
    private List<OrderDetail> orderDetails = new ArrayList<>();
    @ManyToOne
    private Employee employee;

    public int getOrderId()
    {
        return orderId;
    }

    public void setOrderId(int orderId)
    {
        this.orderId = orderId;
    }

    public int getEmployeeId()
    {
        return employeeId;
    }

    public void setEmployeeId(int employeeId)
    {
        this.employeeId = employeeId;
    }

    public Date getOrderDate()
    {
        return orderDate;
    }

    public void setOrderDate(Date orderDate)
    {
        this.orderDate = orderDate;
    }

    public List<OrderDetail> getOrderDetails()
    {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails)
    {
        this.orderDetails = orderDetails;
    }

    public Employee getEmployee()
    {
        return employee;
    }

    public void setEmployee(Employee employee)
    {
        this.employee = employee;
    }
}
