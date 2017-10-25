package controllers;

import models.Actual;
import models.Client;
import models.Employee;
import play.data.FormFactory;
import play.db.jpa.JPAApi;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import java.util.ArrayList;
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

        List<Employee> foremen = new ArrayList<>();

        for (int i = 1; i < 10; i = i + 4)
        {
            Employee foreman = jpaApi.em().createQuery("FROM Employee e WHERE employeeId = :id", Employee.class).setParameter("id", i).getSingleResult();

            Actual actual = jpaApi.em().createQuery("FROM Actual a WHERE employeeId = :id ORDER BY actualDate DESC", Actual.class).setMaxResults(1).setParameter("id", i).getSingleResult();

            String currentDate = actual.getActualDate();

            List<Actual> actuals = jpaApi.em().createQuery("FROM Actual a WHERE actualDate = :currentDate", Actual.class).setParameter("currentDate", currentDate).getResultList();

            foreman.setActuals(actuals);

            foremen.add(foreman);
        }

        for (Employee foreman : foremen)
        {
            List<Actual> actuals = foreman.getActuals();

            for(Actual actual : actuals)
            {
                actual.getEstimate().getContract().getClient().getFullAddress();
            }
        }

        return ok(views.html.pmwelcome.render(projectManager, foremen));
    }
}
