package controllers;

import models.Employee;
import models.EmployeeTerritory;
import models.FullEmployee;
import play.data.DynamicForm;
import play.data.FormFactory;
import play.db.jpa.JPA;
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
        List<FullEmployee> employees =
                jpaApi.
                        em().
                        createQuery("SELECT NEW FullEmployee(e.employeeId, e.titleOfCourtesy, e.lastName, e.firstName, e.title, e.salary, m.lastName, m.firstName) " +
                                    "FROM Employee e " +
                                    "LEFT OUTER JOIN Employee m ON e.reportsTo = m.employeeId", FullEmployee.class).
                        getResultList();


        return ok(views.html.employees.render(employees));
    }

    @Transactional(readOnly = true)
    public Result getEmployee(Integer id)
    {
        Employee employee =
                jpaApi.em().
                        createQuery("SELECT e FROM Employee e WHERE employeeId = :id", Employee.class).
                        setParameter("id", id).
                        getSingleResult();

        List<Employee> employees =
                jpaApi.
                        em().
                        createQuery("SELECT e FROM Employee e ORDER BY lastname, firstname", Employee.class).
                        getResultList();

        return ok(views.html.employee.render(employee, employees));
    }

    @Transactional
    public Result updateEmployee(Integer id)
    {
        DynamicForm dynamicForm = formFactory.form().bindFromRequest();

        String titleOfCourtesy = dynamicForm.get("titleofcourtesy");
        String firstName = dynamicForm.get("firstname");
        String lastName = dynamicForm.get("lastname");
        String title = dynamicForm.get("title");
        BigDecimal salary = new BigDecimal(dynamicForm.get("salary"));
        Integer reportsToId = Integer.parseInt(dynamicForm.get("reportsto"));

        Employee employee =
                jpaApi.em().
                createQuery("SELECT e FROM Employee e WHERE employeeId = :id", Employee.class).
                setParameter("id", id).
                getSingleResult();

        employee.setTitleOfCourtesy(titleOfCourtesy);
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        employee.setTitle(title);
        employee.setSalary(salary);
        employee.setReportsTo(reportsToId);

        jpaApi.em().persist(employee);

        return redirect(routes.EmployeeController.getEmployees());
    }

    @Transactional(readOnly = true)
    public Result searchEmployees()
    {
        DynamicForm dynamicForm = formFactory.form().bindFromRequest();
        String lastName = dynamicForm.get("lastname");

        if(lastName == null)
        {
            lastName = "";
        }

        List<Employee> employees =
                jpaApi.em().
                createQuery("SELECT e FROM Employee e WHERE lastname LIKE :lastname ORDER BY lastname, firstname", Employee.class).
                setParameter("lastname","%" + lastName + "%").
                getResultList();

        return ok(views.html.searchemployees.render(employees));
    }

    @Transactional
    public Result addEmployee()
    {
        DynamicForm dynamicForm = formFactory.form().bindFromRequest();

        String titleOfCourtesy = dynamicForm.get("titleofcourtesy");
        String firstName = dynamicForm.get("firstname");
        String lastName = dynamicForm.get("lastname");
        String title = dynamicForm.get("title");
        BigDecimal salary = new BigDecimal(dynamicForm.get("salary"));
        Integer reportsToId = Integer.parseInt(dynamicForm.get("reportsto"));

        Employee employee = new Employee();

        employee.setTitleOfCourtesy(titleOfCourtesy);
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        employee.setTitle(title);
        employee.setSalary(salary);
        employee.setReportsTo(reportsToId);

        jpaApi.em().persist(employee);

        return redirect(routes.EmployeeController.getEmployees());
    }

    @Transactional(readOnly = true)
    public Result newEmployee()
    {
        List<Employee> employees =
                jpaApi.
                        em().
                        createQuery("SELECT e FROM Employee e ORDER BY lastname, firstname", Employee.class).
                        getResultList();

        return ok(views.html.addemployee.render(employees));
    }
}
