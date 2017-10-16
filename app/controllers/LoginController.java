package controllers;

import models.Employee;
import play.data.DynamicForm;
import play.data.FormFactory;
import play.db.jpa.JPAApi;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import java.util.List;

public class LoginController extends Controller
{
    private FormFactory formFactory;
    private JPAApi jpaApi;

    @Inject
    public LoginController(FormFactory formFactory, JPAApi jpaApi)
    {
        this.formFactory = formFactory;
        this.jpaApi = jpaApi;
    }

    public Result getLogin()
    {
        return ok(views.html.login.render());
    }

    @Transactional(readOnly = true)
    public Result postLogin()
    {
        DynamicForm dynamicForm = formFactory.form().bindFromRequest();
        String username = dynamicForm.get("username");
        String password = dynamicForm.get("password");

        String sql = "SELECT e FROM Employee e WHERE lastname = :username AND password = :password";

        List<Employee> employees = jpaApi.em().createQuery(sql).
                                   setParameter("username", username).
                                   setParameter("password",password).
                                   getResultList();

        if (employees.size() == 1)
        {
            session().put("employeeId", "" + employees.get(0).getEmployeeId());
            return redirect(routes.ProductController.getProducts());
        }
        else
        {
            return ok(views.html.accessdenied.render());
        }
    }

    public Result getAccessDenied()
    {
        return ok(views.html.accessdenied.render());
    }

    public Result logout()
    {
        session().clear();

        return ok("You are logged out");
    }
}
