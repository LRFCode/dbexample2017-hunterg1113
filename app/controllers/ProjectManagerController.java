package controllers;

import models.Employee;
import play.data.FormFactory;
import play.db.jpa.JPAApi;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import java.util.List;

public class ProjectManagerController extends Controller
{
    JPAApi jpaApi;
    FormFactory formFactory;

    @Inject
    public ProjectManagerController(JPAApi jpaApi, FormFactory formFactory)
    {
        this.jpaApi = jpaApi;
        this.formFactory = formFactory;
    }

    @Transactional
    public Result getWelcomeScreen(Integer id)
    {
        Employee projectManager = jpaApi.em().createQuery("FROM Employee e WHERE employeeId = :id", Employee.class).setParameter("id", id).getSingleResult();

        List<Employee> foremen = jpaApi.em().createQuery("FROM Employee e WHERE title = 'foreman'").getResultList();

        return ok(views.html.pmwelcome.render(projectManager, foremen));
    }
}
