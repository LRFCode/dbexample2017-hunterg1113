package models;


public class ProjectPicWId
{
    private int listNo;
    private int pictureId;
    private int contractId;
    private String pictureName;
    private String pictureDate;
    private byte[] picture;

    public int getListNo()
    {
        return listNo;
    }

    public void setListNo(int listNo)
    {
        this.listNo = listNo;
    }

    public int getPictureId()
    {
        return pictureId;
    }

    public void setPictureId(int pictureId)
    {
        this.pictureId = pictureId;
    }

    public int getContractId()
    {
        return contractId;
    }

    public void setContractId(int contractId)
    {
        this.contractId = contractId;
    }

    public String getPictureName()
    {
        return pictureName;
    }

    public void setPictureName(String pictureName)
    {
        this.pictureName = pictureName;
    }

    public String getPictureDate()
    {
        return pictureDate;
    }

    public void setPictureDate(String pictureDate)
    {
        this.pictureDate = pictureDate;
    }

    public byte[] getPicture()
    {
        return picture;
    }

    public void setPicture(byte[] picture)
    {
        this.picture = picture;
    }
}
