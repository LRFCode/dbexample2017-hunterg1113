package models;

import javax.persistence.*;
import javax.persistence.Id;
import java.util.List;


@Entity
@Table(name = "Category")
public class Category
{
    @Id
    @Column(name = "CategoryId")
    private int categoryId;
    @Column(name = "CategoryName")
    private String categoryName;

    public int getCategoryId()
    {
        return categoryId;
    }

    public void setCategoryId(int categoryId)
    {
        this.categoryId = categoryId;
    }

    public String getCategoryName()
    {
        return categoryName;
    }

    public void setCategoryName(String categoryName)
    {
        this.categoryName = categoryName;
    }
}