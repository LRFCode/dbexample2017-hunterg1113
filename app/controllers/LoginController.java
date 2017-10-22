package controllers;

import models.Employee;
import models.Password;
import play.data.DynamicForm;
import play.data.FormFactory;
import play.db.jpa.JPAApi;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import java.util.Arrays;
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

        String sql = "SELECT e FROM Employee e WHERE lastname = :username";

        List<Employee> employees = jpaApi.em().createQuery(sql).
                setParameter("username", username).
                getResultList();

        if (employees.size() == 1)
        {
            byte[] hashedPassword = Password.hashPassword(password.toCharArray(), employees.get(0).getSalt());
            byte[] dbPassword = employees.get(0).getPassword();

            if (Arrays.equals(hashedPassword, dbPassword))
            {
                session().put("employeeId", "" + employees.get(0).getEmployeeId());
                return redirect(routes.EmployeeController.getEmployeesNativeSQL());
            }
        }

        return ok(views.html.accessdenied.render());
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
