package controllers;

import models.Employee;
import play.data.DynamicForm;
import play.data.FormFactory;
import play.db.jpa.JPAApi;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.List;

public class EmployeeController extends Controller
{
    private JPAApi jpaApi;
    private FormFactory formFactory;

    @Inject
    public EmployeeController(FormFactory formFactory, JPAApi jpaApi)
    {
        this.jpaApi = jpaApi;
        this.formFactory = formFactory;
    }

    @Transactional(readOnly = true)
    public Result getEmployees()
    {
        List<Employee> employees =
            jpaApi.
            em().
            createQuery("SELECT e FROM Employee e ORDER BY lastname, firstname", Employee.class).
            getResultList();

        return ok(views.html.employees.render(employees));
    }

    @Transactional(readOnly = true)
    public Result getEmployee(Integer id)
    {
        Employee employee =
                jpaApi.em().
                createQuery("SELECT e FROM Employee e WHERE employeeId = :id", Employee.class).
                setParameter("id",id).
                getSingleResult();

        return ok(views.html.employee.render(employee));
    }

    @Transactional
    public Result updateEmployee(Integer id)
    {
        DynamicForm dynamicForm= formFactory.form().bindFromRequest();

        String firstName = dynamicForm.get("firstname");
        String lastName = dynamicForm.get("lastname");
        String title = dynamicForm.get("title");
        BigDecimal salary = new BigDecimal(dynamicForm.get("salary"));

        Employee employee =
                jpaApi.em().
                createQuery("SELECT e FROM Employee e WHERE employeeId = :id", Employee.class).
                setParameter("id",id).
                getSingleResult();

        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        employee.setTitle(title);
        employee.setSalary(salary);

        jpaApi.em().persist(employee);

        return redirect(routes.EmployeeController.getEmployees());
    }
}
