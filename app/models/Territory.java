package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Territory")
public class Territory
{
    @Id
    @Column(name = "TerritoryId")
    private int territoryId;
    @Column(name = "TerritoryDescription")
    private String territoryDescription;

    public int getTerritoryId()
    {
        return territoryId;
    }

    public void setTerritoryId(int territoryId)
    {
        this.territoryId = territoryId;
    }

    public String getTerritoryDescription()
    {
        return territoryDescription;
    }

    public void setTerritoryDescription(String territoryDescription)
    {
        this.territoryDescription = territoryDescription;
    }
}
