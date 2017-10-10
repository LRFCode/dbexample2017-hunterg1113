package controllers;

import models.Product;
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

        return ok(views.html.product.render(product));
    }

    @Transactional
    public Result updateProduct(Integer id)
    {
        DynamicForm dynamicForm = formFactory.form().bindFromRequest();

        String productName = dynamicForm.get("productname");
        BigDecimal unitPrice = new BigDecimal(dynamicForm.get("unitprice"));
        Integer unitsInStock = Integer.parseInt(dynamicForm.get("unitsinstock"));

        Product product = jpaApi.em().createQuery("SELECT p FROM Product p WHERE productid = :id", Product.class).setParameter("id", id).getSingleResult();

        product.setProductName(productName);
        product.setUnitPrice(unitPrice);
        product.setUnitsInStock(unitsInStock);

        jpaApi.em().persist(product);

        return redirect(routes.ProductController.getProducts());
    }
}
