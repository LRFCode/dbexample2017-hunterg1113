package models;

import javax.persistence.*;
import javax.persistence.Id;

@Entity
@Table(name = "User")
public class ProjectUser
{
    @Id
    @Column(name = "UserId")
    private int userId;
    @Column(name = "EmployeeId")
    private int employeeId;
    @Column(name = "UserName")
    private String userName;
    @Column(name = "Password")
    private byte[] password;
    @Column(name = "Salt")
    private byte[] salt;

    public int getUserId()
    {
        return userId;
    }

    public void setUserId(int userId)
    {
        this.userId = userId;
    }

    public int getEmployeeId()
    {
        return employeeId;
    }

    public void setEmployeeId(int employeeId)
    {
        this.employeeId = employeeId;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public byte[] getPassword()
    {
        return password;
    }

    public void setPassword(byte[] password)
    {
        this.password = password;
    }

    public byte[] getSalt()
    {
        return salt;
    }

    public void setSalt(byte[] salt)
    {
        this.salt = salt;
    }
}
