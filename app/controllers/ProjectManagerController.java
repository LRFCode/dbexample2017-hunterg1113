package controllers;

import models.Actual;
import models.Contract;
import models.Employee;
import models.Equipment;
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

        for(Employee employee : foremen)
        {
            int employeeId = employee.getEmployeeId();

            String recentDate = jpaApi.em().createQuery("FROM Actual a WHERE employeeId = :id ORDER BY actualDate DESC", Actual.class).setParameter("id",employeeId).setMaxResults(1).getSingleResult().getActualDate();

            List<Actual> actuals = jpaApi.em().createQuery("FROM Actual a WHERE employeeId = :id AND actualDate = :recentDate", Actual.class).setParameter("id",employeeId).setParameter("recentDate", recentDate).getResultList();



            employee.setActuals(actuals);
        }

        return ok(views.html.pmwelcome.render(projectManager, foremen));
    }

    @Transactional
    public Result getPlan(int id)
    {
        Employee projectManager = jpaApi.em().createQuery("FROM Employee e WHERE employeeId = :id", Employee.class).setParameter("id", id).getSingleResult();

        Actual actual = jpaApi.em().createQuery("FROM Actual a WHERE employeeId = :id ORDER BY actualDate DESC", Actual.class).setParameter("id",id).setMaxResults(1).getSingleResult();

        byte[] plan = actual.getEstimate().getContract().getPlans();

        return ok(plan);
    }

    @Transactional
    public Result newContract()
    {


        return ok(views.html.newcontract.render());
    }
}
