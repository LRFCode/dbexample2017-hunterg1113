package controllers;

import models.Supplier;
import play.db.jpa.JPAApi;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import java.util.List;

public class SupplierController extends Controller
{
    JPAApi jpaApi;

    @Inject
    public SupplierController(JPAApi jpaApi)
    {
        this.jpaApi = jpaApi;
    }

    @Transactional
    public Result getSuppliers()
    {
        String query = "FROM Supplier s";

        List<Supplier> suppliers = jpaApi.em().createQuery(query).getResultList();

        return ok(views.html.suppliers.render(suppliers));
    }
}
