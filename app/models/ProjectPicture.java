package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ProjectPicture")
public class ProjectPicture
{
    @Id
    @Column (name = "PictureId")
    private int pictureId;
    @Column (name = "ContractId")
    private int contractId;
    @Column (name = "PictureName")
    private String pictureName;
    @Column (name = "PictureDate")
    private String pictureDate;
    @Column (name = "Picture")
    private byte[] picture;

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
