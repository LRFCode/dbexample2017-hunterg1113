package controllers;

import models.Password;
import models.ProjectUser;
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
    FormFactory formFactory;
    JPAApi jpaApi;

    @Inject
    public LoginController(FormFactory formFactory, JPAApi jpaApi)
    {
        this.formFactory = formFactory;
        this.jpaApi = jpaApi;
    }

    public Result redirectToLogin()
    {
        return redirect(routes.LoginController.getLogin());
    }

    public Result getLogin()
    {
        return ok(views.html.login.render());
    }

    @Transactional
    public Result postLogin()
    {
        DynamicForm dynamicForm = formFactory.form().bindFromRequest();

        String password = dynamicForm.get("password");
        String username = dynamicForm.get("username");

        List<ProjectUser> projectUsers = jpaApi.em().createQuery("FROM ProjectUser WHERE username = :username").
                setParameter("username", username).getResultList();

        if (projectUsers.size() > 0)
        {
            byte[] hashedPassword = Password.hashPassword(password.toCharArray(), projectUsers.get(0).getSalt());

            if (Arrays.equals(hashedPassword, projectUsers.get(0).getPassword()) && projectUsers.get(0).getEmployee().getTitle().equals("Foreman"))
            {
                return redirect(routes.ForemanController.getClockInScreen(projectUsers.get(0).getEmployeeId()));
            }
            else if (Arrays.equals(hashedPassword, projectUsers.get(0).getPassword()) && projectUsers.get(0).getEmployee().getTitle().equals("Project Manager"))
            {
                return redirect(routes.ProjectManagerController.getTemplate(projectUsers.get(0).getEmployeeId()));
            }
        }

        return ok("Error: username & password combination not found in database. Please try again.");

    }

    public Result getLogOut()
    {
        return ok(views.html.login.render());
    }
}
