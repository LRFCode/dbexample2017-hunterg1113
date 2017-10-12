package controllers;

import models.Category;
import models.Product;
import models.Supplier;
import play.data.DynamicForm;
import play.data.FormFactory;
import play.db.jpa.JPAApi;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.List;

public class ProductController extends Controller
{
    private JPAApi jpaApi;
    private FormFactory formFactory;

    @Inject
    public ProductController(FormFactory formFactory, JPAApi jpaApi)
    {
        this.formFactory = formFactory;
        this.jpaApi = jpaApi;
    }

    @Transactional(readOnly = true)
    public Result getProducts()
    {
        List<Product> products = jpaApi.em().createQuery("SELECT p FROM Product p ORDER BY productName", Product.class).getResultList();

        return ok(views.html.products.render(products));
    }

    @Transactional(readOnly = true)
    public Result getProduct(Integer id)
    {
        Product product = jpaApi.em().createQuery("SELECT p FROM Product p WHERE productid = :id", Product.class).setParameter("id", id).getSingleResult();

        List<Supplier> suppliers = jpaApi.em().createQuery("SELECT s FROM Supplier s ORDER BY companyname", Supplier.class).getResultList();

        List<Category> categories = jpaApi.em().createQuery("SELECT c FROM Category c ORDER BY categoryid", Category.class).getResultList();

        return ok(views.html.product.render(product, suppliers, categories));
    }

    @Transactional
    public Result updateProduct(Integer id)
    {
        DynamicForm dynamicForm = formFactory.form().bindFromRequest();

        String productName = dynamicForm.get("productname");
        BigDecimal unitPrice = new BigDecimal(dynamicForm.get("unitprice"));
        Integer unitsInStock = Integer.parseInt(dynamicForm.get("unitsinstock"));
        Integer categoryId = Integer.parseInt(dynamicForm.get("categoryname"));
        Integer supplierId = Integer.parseInt(dynamicForm.get("suppliername"));


        Product product = jpaApi.em().createQuery("SELECT p FROM Product p WHERE productid = :id", Product.class).setParameter("id", id).getSingleResult();

        product.setProductName(productName);
        product.setUnitPrice(unitPrice);
        product.setUnitsInStock(unitsInStock);
        product.setCategoryId(categoryId);
        product.setSupplierId(supplierId);

        jpaApi.em().persist(product);

        return redirect(routes.ProductController.getProducts());
    }

    @Transactional(readOnly = true)
    public Result searchProducts()
    {
        DynamicForm dynamicForm = formFactory.form().bindFromRequest();
        String productName = dynamicForm.get("productname");

        if(productName == null)
        {
            productName = "";
        }

        List<Product> products = jpaApi.em().
                                 createQuery("SELECT p FROM Product p WHERE ProductName LIKE :productName ORDER BY productname").
                                 setParameter("productName", "%" + productName + "%").
                                 getResultList();

        return ok(views.html.searchproducts.render(products));
    }

    @Transactional
    public Result newProduct()
    {
        List<Supplier> suppliers = jpaApi.em().createQuery("SELECT s FROM Supplier s ORDER BY companyname", Supplier.class).getResultList();
        List<Category> categories = jpaApi.em().createQuery("SELECT c FROM Category c ORDER BY categoryname", Category.class).getResultList();

        return ok(views.html.addproduct.render(suppliers,categories));
    }

    @Transactional
    public Result addProduct()
    {
        DynamicForm dynamicForm = formFactory.form().bindFromRequest();

        String productName = dynamicForm.get("productname");
        BigDecimal unitPrice = new BigDecimal(dynamicForm.get("unitprice"));
        Integer unitsInStock = Integer.parseInt(dynamicForm.get("unitsinstock"));
        Integer supplierId = Integer.parseInt(dynamicForm.get("suppliername"));
        Integer categoryId = Integer.parseInt(dynamicForm.get("categoryname"));

        Product product = new Product();

        product.setProductName(productName);
        product.setUnitPrice(unitPrice);
        product.setUnitsInStock(unitsInStock);
        product.setSupplierId(supplierId);
        product.setCategoryId(categoryId);

        jpaApi.em().persist(product);

        return redirect(routes.ProductController.getProducts());
    }
}
