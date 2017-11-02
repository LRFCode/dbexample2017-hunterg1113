package controllers;

import com.google.common.io.Files;
import models.*;
import org.joda.time.DateTime;
import org.joda.time.Minutes;
import org.joda.time.format.DateTimeFormat;
import play.data.DynamicForm;
import play.data.FormFactory;
import play.db.jpa.JPAApi;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

import javax.inject.Inject;
import java.io.File;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
    public Result getClockInScreen(int id)
    {
        Employee foreman = jpaApi.em().createQuery("FROM Employee e WHERE employeeId = :id", Employee.class).setParameter("id", id).getSingleResult();

        List<Employee> employees = jpaApi.em().createQuery("FROM Employee e WHERE NOT title = 'project manager' ORDER BY lastName", Employee.class).getResultList();

        List<Equipment> equipments = jpaApi.em().createQuery("FROM Equipment e").getResultList();

        List<Contract> contracts = jpaApi.em().createQuery("FROM Contract c WHERE completed = 0").getResultList();

        int contractId = jpaApi.em().createQuery("FROM Actual a WHERE employeeId = :employeeId ORDER BY actualDate DESC", Actual.class).setParameter("employeeId", id).setMaxResults(1).getSingleResult().getEstimate().getContractId();

        createEntriesInDb(id);

        return ok(views.html.crewclockin.render(foreman, employees, equipments, contracts, contractId));
    }

    @Transactional
    public Result getWelcomeScreen(int id)
    {
        Employee foreman = jpaApi.em().createQuery("FROM Employee e WHERE employeeId = :id", Employee.class).setParameter("id", id).getSingleResult();

        Actual actual = jpaApi.em().createQuery("FROM Actual a WHERE employeeId = :id ORDER BY actualDate DESC", Actual.class).setMaxResults(1).setParameter("id", id).getSingleResult();

        Client client = actual.getEstimate().getContract().getClient();

        String actualDate = actual.getActualDate();

        List<Actual> actuals = jpaApi.em().createQuery("FROM Actual a WHERE employeeId = :id AND actualDate = :actualDate").setParameter("id", id).setParameter("actualDate", actualDate).getResultList();

        DynamicForm dynamicForm = formFactory.form().bindFromRequest();
        List<Employee> crew = createCrewAndUpdateInDb(dynamicForm, id);
        List<Equipment> crewEquipment = createCrewEquipmentAndUpdateInDb(dynamicForm, id);

        return ok(views.html.foremanwelcome.render(foreman, client, actuals, crew, crewEquipment));
    }

    @Transactional
    public Result getClockOutScreen(int id, int contractId)
    {
        Employee foreman = jpaApi.em().createQuery("FROM Employee e WHERE employeeId = :id", Employee.class).setParameter("id", id).getSingleResult();

        List<Employee> crew = getCrewFromDb(contractId);
        List<Equipment> crewEquipment = getCrewEquipmentFromDb(contractId);

        return ok(views.html.crewclockout.render(foreman, crew, crewEquipment));
    }

    @Transactional
    public Result postCrewClockOut(int id)
    {
        Employee foreman = jpaApi.em().createQuery("FROM Employee e WHERE employeeId = :id", Employee.class).setParameter("id", id).getSingleResult();

        DynamicForm dynamicForm = formFactory.form().bindFromRequest();

        BigDecimal hours = getCrewHoursFromForm(dynamicForm);
        String crewHours = hours.toString();

        updateCrewEquipmentInDb(dynamicForm);

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.now();
        String today = dtf.format(localDate);

        int contractId = jpaApi.em().createQuery("FROM Actual a WHERE actualDate = :today AND employeeId = :id", Actual.class).setParameter("id", id).setParameter("today", today).setMaxResults(1).getSingleResult().getEstimate().getContractId();

        List<Actual> actuals = jpaApi.em().createQuery("SELECT a FROM Actual a JOIN Estimate e ON e.estimateId = a.estimateId JOIN Contract c ON e.contractId = c.contractId WHERE c.contractId = :contractId AND a.actualDate = :today", Actual.class).setParameter("contractId", contractId).setParameter("today", today).getResultList();

        for (Actual actual : actuals)
        {
            actual.setEmployeeId(id);
            jpaApi.em().persist(actual);
        }

        return ok(views.html.categoryclockout.render(foreman, crewHours, actuals));
    }

    @Transactional
    public Result postSummaryScreen(int id)
    {
        Employee foreman = jpaApi.em().createQuery("FROM Employee e WHERE employeeId = :id", Employee.class).setParameter("id", id).getSingleResult();

        DynamicForm dynamicForm = formFactory.form().bindFromRequest();

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.now();
        String today = dtf.format(localDate);

        int contractId = jpaApi.em().createQuery("FROM Actual a WHERE actualDate = :today AND employeeId = :id", Actual.class).setParameter("id", id).setParameter("today", today).setMaxResults(1).getSingleResult().getEstimate().getContractId();

        List<Actual> actuals = jpaApi.em().createQuery("SELECT a FROM Actual a JOIN Estimate e ON e.estimateId = a.estimateId JOIN Contract c ON e.contractId = c.contractId WHERE c.contractId = :contractId AND a.actualDate = :today", Actual.class).setParameter("contractId", contractId).setParameter("today", today).getResultList();

        List<Actual> dailyActuals = new ArrayList<>();

        for(Actual actual : actuals)
        {
            String search = "category" + actual.getEstimate().getCategoryId();

            if (!dynamicForm.get(search).equals(""))
            {
                String hours = dynamicForm.get(search);

                BigDecimal dailyHours = new BigDecimal(hours);

                actual.setActualHours(actual.getActualHours().add(dailyHours));

                dailyActuals.add(actual);

                jpaApi.em().persist(actual);
            }
        }

        List<Estimate> estimates = jpaApi.em().createQuery("FROM Estimate e WHERE contractId = :contractId").setParameter("contractId", contractId).getResultList();

        return ok(views.html.foremandailysummary.render(foreman, dailyActuals));
    }

    @Transactional
    public Result getUploadPhoto()
    {
        return ok(views.html.uploadimages.render());
    }

    @Transactional
    public Result postUploadPhoto()
    {
        Http.MultipartFormData<File> formData = request().body().asMultipartFormData();
        Http.MultipartFormData.FilePart<File> filePart = formData.getFile("filename");
        File file = filePart.getFile();

        byte[] photo;

        try
        {
            photo = Files.toByteArray(file);
        } catch (Exception e)
        {
            photo = null;
        }

        int nextPicId = jpaApi.em().createQuery("FROM ProjectPicture ORDER BY pictureId DESC", ProjectPicture.class).setMaxResults(1).getSingleResult().getPictureId() + 1;

        ProjectPicture projectPicture = new ProjectPicture();

        projectPicture.setPictureId(nextPicId);
        projectPicture.setContractId(128);
        projectPicture.setPicture(photo);

        jpaApi.em().persist(projectPicture);

        return ok("Saved photo");
    }

    @Transactional
    public Result getPicture(int contractId)
    {
        Contract contract = jpaApi.em().createQuery("FROM Contract c WHERE contractId = :id", Contract.class).setParameter("id", contractId).getSingleResult();

        if (contract.getPlans() == null)
        {
            return null;
        }
        else
        {
            return ok(contract.getPlans()).as("image.jpg");
        }
    }

    @Transactional
    public void createEntriesInDb(int id)
    {
        Actual actual = jpaApi.em().createQuery("FROM Actual a WHERE employeeId = :id ORDER BY actualDate DESC", Actual.class).setMaxResults(1).setParameter("id", id).getSingleResult();
        int nextActualId = jpaApi.em().createQuery("FROM Actual a ORDER BY actualId DESC", Actual.class).setMaxResults(1).getSingleResult().getActualId() + 1;
        String actualDate = actual.getActualDate();

        List<Actual> actuals = jpaApi.em().createQuery("FROM Actual a WHERE employeeId = :id AND actualDate = :actualDate").setParameter("id", id).setParameter("actualDate", actualDate).getResultList();

        for (Actual actual1 : actuals)
        {
            Actual newActual = new Actual();

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate localDate = LocalDate.now();
            String today = dtf.format(localDate);

            newActual.setActualDate(today);
            newActual.setActualHours(actual1.getActualHours());
            newActual.setActualId(nextActualId);
            newActual.setEstimateId(actual1.getEstimateId());
            newActual.setEmployeeId(id);

            jpaApi.em().persist(newActual);

            nextActualId++;
        }
    }

    @Transactional
    public List<Employee> createCrewAndUpdateInDb(DynamicForm dynamicForm, int id)
    {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate localDate = LocalDate.now();
        String today = dtf.format(localDate);

        List<Employee> crew = new ArrayList<>();

        List<Employee> employees = jpaApi.em().createQuery("FROM Employee e WHERE NOT title = 'Project Manager'").getResultList();

        for (Employee employee : employees)
        {
            String employeeSearch = "clockintime" + employee.getEmployeeId();

            if (!dynamicForm.get(employeeSearch).equals("notcrew"))
            {
                crew.add(employee);

                String time = dynamicForm.get(employeeSearch);
                employee.setLastClockIn(today + " " + time);

                jpaApi.em().persist(employee);
            }
        }

        int searchSize = crew.size();

        List<Actual> dbActuals = jpaApi.em().createQuery("FROM Actual a WHERE employeeId = :id AND actualDate = :today ORDER BY actualDate DESC").setParameter("id", id).setParameter("today", today).setMaxResults(searchSize).getResultList();

        for (int i = 0; i < searchSize; i++)
        {
            dbActuals.get(i).setEmployeeId(crew.get(i).getEmployeeId());

            jpaApi.em().persist(dbActuals.get(i));
        }

        return crew;
    }

    @Transactional
    public List<Equipment> createCrewEquipmentAndUpdateInDb(DynamicForm dynamicForm, int id)
    {
        int contractId = Integer.parseInt(dynamicForm.get("contract"));

        List<Equipment> equipments = jpaApi.em().createQuery("FROM Equipment e").getResultList();

        List<Equipment> crewEquipment = new ArrayList<>();

        for (Equipment equipment : equipments)
        {
            String equipmentSearch = "equipment" + equipment.getEquipmentId();

            if (dynamicForm.get(equipmentSearch) != null)
            {
                crewEquipment.add(equipment);
                equipment.setContractId(contractId);

                jpaApi.em().persist(equipment);
            }
        }

        return crewEquipment;
    }

    @Transactional
    public List<Employee> getCrewFromDb(int contractId)
    {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate localDate = LocalDate.now();
        String today = dtf.format(localDate);

        List<EmployeeId> employeeIds = jpaApi.em().createNativeQuery("SELECT DISTINCT(a.employeeId) FROM Contract c JOIN Estimate e ON c.ContractId = e.ContractId JOIN Actual a ON a.EstimateId = e.EstimateId WHERE a.ActualDate = :today AND c.ContractId = :contractId", EmployeeId.class).setParameter("contractId", contractId).setParameter("today", today).getResultList();

        List<Employee> crew = new ArrayList<>();

        for (EmployeeId employeeId : employeeIds)
        {
            int x = employeeId.getEmployeeId();

            Employee employee = jpaApi.em().createQuery("FROM Employee e WHERE employeeId = :employeeId", Employee.class).setParameter("employeeId", x).getSingleResult();

            crew.add(employee);
        }

        return crew;
    }

    @Transactional
    public List<Equipment> getCrewEquipmentFromDb(int contractId)
    {
        List<Equipment> crewEquipment = jpaApi.em().createQuery("FROM Equipment e WHERE contractId = :contractId").setParameter("contractId", contractId).getResultList();

        return crewEquipment;
    }

    @Transactional
    public BigDecimal getCrewHoursFromForm(DynamicForm dynamicForm)
    {
        List<Employee> employees = jpaApi.em().createQuery("FROM Employee e WHERE NOT title = 'Project Manager'").getResultList();

        BigDecimal crewHours = BigDecimal.ZERO;

        for (Employee employee : employees)
        {
            String employeeSearch = "clockouttime" + employee.getEmployeeId();

            if (dynamicForm.get(employeeSearch) != null)
            {
                DateTime startTime = DateTime.parse(employee.getLastClockIn(), DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss.SSS"));

                String time = employee.getLastClockIn().substring(0, 11) + dynamicForm.get(employeeSearch);

                DateTime stopTime = DateTime.parse(time, DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss"));

                String minutes = "" + Minutes.minutesBetween(startTime, stopTime).getMinutes();

                BigDecimal employeeMins = new BigDecimal(minutes);
                BigDecimal hourMins = new BigDecimal("60");

                BigDecimal employeeHours = employeeMins.divide(hourMins);

                crewHours = crewHours.add(employeeHours);
            }
        }

        return crewHours;
    }

    @Transactional
    public void updateCrewEquipmentInDb(DynamicForm dynamicForm)
    {
        List<Equipment> equipments = jpaApi.em().createQuery("FROM Equipment e").getResultList();

        List<Equipment> crewEquipment = new ArrayList<>();

        for (Equipment equipment : equipments)
        {
            String equipmentSearch = "equipment" + equipment.getEquipmentId();

            if (dynamicForm.get(equipmentSearch) != null)
            {
                crewEquipment.add(equipment);
                equipment.setEquipmentHours(Integer.parseInt(dynamicForm.get(equipmentSearch)));
                jpaApi.em().persist(equipment);
            }
        }
    }

    @Transactional
    public Result getForemanTest(int id)
    {
        Employee foreman = jpaApi.em().createQuery("FROM Employee e WHERE employeeId = :id", Employee.class).setParameter("id", id).getSingleResult();

        Actual actual = jpaApi.em().createQuery("FROM Actual a WHERE employeeId = :id ORDER BY actualDate DESC", Actual.class).setMaxResults(1).setParameter("id", id).getSingleResult();

        Client client = actual.getEstimate().getContract().getClient();

        String actualDate = actual.getActualDate();

        List<Actual> actuals = jpaApi.em().createQuery("FROM Actual a WHERE employeeId = :id AND actualDate = :actualDate").setParameter("id", id).setParameter("actualDate", actualDate).getResultList();

        return ok(views.html.foremantest.render(foreman,client,actuals));
    }

}
