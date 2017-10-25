package models;

import javax.persistence.*;
import javax.persistence.Id;
import java.util.List;

@Entity
@Table(name = "Client")
public class Client
{
    @Id
    @Column(name = "ClientId")
    private int clientId;
    @Column(name = "LastName")
    private String lastName;
    @Column(name = "FirstName")
    private String firstName;
    @Column(name = "Address")
    private String address;
    @Column(name = "City")
    private String city;
    @Column(name = "State")
    private String state;
    @Column(name = "ZipCode")
    private String zipCode;
    @Column(name = "Phone")
    private String phone;
    @Column(name = "Email")
    private String email;
    @OneToMany(mappedBy = "client")
    private List<Contract> contracts;

    public int getClientId()
    {
        return clientId;
    }

    public void setClientId(int clientId)
    {
        this.clientId = clientId;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getFullName()
    {
        return firstName + " " + lastName;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public String getState()
    {
        return state;
    }

    public void setState(String state)
    {
        this.state = state;
    }

    public String getZipCode()
    {
        return zipCode;
    }

    public void setZipCode(String zipCode)
    {
        this.zipCode = zipCode;
    }

    public String getFullAddress()
    {
        return address + ", " + state + " " + zipCode;
    }

    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public List<Contract> getContracts()
    {
        return contracts;
    }

    public void setContracts(List<Contract> contracts)
    {
        this.contracts = contracts;
    }
}
