package models;

public class JobCoords
{
    private String clientLastName;
    private String foremanLastName;
    private String lat;
    private String lng;

    public String getClientLastName()
    {
        return clientLastName;
    }

    public void setClientLastName(String clientLastName)
    {
        this.clientLastName = clientLastName;
    }

    public String getForemanLastName()
    {
        return foremanLastName;
    }

    public void setForemanLastName(String foremanLastName)
    {
        this.foremanLastName = foremanLastName;
    }

    public String getLat()
    {
        return lat;
    }

    public void setLat(String lat)
    {
        this.lat = lat;
    }

    public String getLng()
    {
        return lng;
    }

    public void setLng(String lng)
    {
        this.lng = lng;
    }
}
