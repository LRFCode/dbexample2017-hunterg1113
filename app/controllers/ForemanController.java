package controllers;

import models.*;
import play.data.FormFactory;
import play.db.jpa.JPAApi;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public class ForemanController extends Controller
{
    JPAApi jpaApi;
    FormFactory formFactory;

    @Inject
    public ForemanController(JPAApi jpaApi, FormFactory formFactory)
    {
        this.jpaApi = jpaApi;
        this.formFactory = formFactory;
    }

    @Transactional
    public Result getWelcomeScreen(Integer id)
    {
        Employee foreman = jpaApi.em().createQuery("FROM Employee e WHERE employeeId = :id", Employee.class).setParameter("id", id).getSingleResult();

        List<Actual> actuals = jpaApi.em().createQuery("FROM Actual a WHERE employeeId = :id ORDER BY actualDate DESC", Actual.class).setParameter("id", id).getResultList();

        Client client = actuals.get(0).getEstimate().getContract().getClient();
        String currentDate = actuals.get(0).getActualDate();

        List<Actual> actuals1 = jpaApi.em().createQuery("FROM Actual a WHERE actualDate = :currentDate ORDER BY actualDate DESC", Actual.class).setParameter("currentDate", currentDate).getResultList();

        return ok(views.html.foremanwelcome.render(foreman, client, actuals1));
    }

    @Transactional
    public Result getClockInScreen(Integer id)
    {
        Employee foreman = jpaApi.em().createQuery("FROM Employee e WHERE employeeId = :id", Employee.class).setParameter("id", id).getSingleResult();

        List<Employee> employees = jpaApi.em().createQuery("FROM Employee e WHERE NOT title IN ('Project Manager', 'Foreman')").getResultList();
        List<Equipment> equipments = jpaApi.em().createQuery("FROM Equipment").getResultList();


        return ok(views.html.crewclockin.render(foreman, employees, equipments));
    }

    @Transactional
    public Result getClockOutScreen(Integer id)
    {
        Employee foreman = jpaApi.em().createQuery("FROM Employee e WHERE employeeId = :id", Employee.class).setParameter("id", id).getSingleResult();

        List<Employee> employees = jpaApi.em().createQuery("FROM Employee e WHERE NOT title IN ('Project Manager', 'Foreman')").getResultList();
        List<Equipment> equipments = jpaApi.em().createQuery("FROM Equipment").getResultList();

        return ok(views.html.crewclockout.render(foreman, employees, equipments));
    }
}
