package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Project")
public class Project
{
    @Id
    @Column(name = "ProjectId")
    private int projectId;
    @Column(name = "ClientId")
    private int clientId;
    @Column(name = "StartDate")
    private String startDate;
    @Column(name = "CompletionDate")
    private String completionDate;

    public int getProjectId()
    {
        return projectId;
    }

    public void setProjectId(int projectId)
    {
        this.projectId = projectId;
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
}
