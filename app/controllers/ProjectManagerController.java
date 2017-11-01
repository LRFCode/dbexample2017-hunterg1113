package controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import models.*;
import models.Geocoder.Geocoder;
import play.data.FormFactory;
import play.db.jpa.JPAApi;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import java.net.URL;
import java.net.URLEncoder;
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

        List<Employee> foremen = jpaApi.em().createQuery("FROM Employee e WHERE title = 'foreman'").getResultList();

        List<JobCoords> mapCoordinates = new ArrayList<>();

        for (Employee employee : foremen)
        {
            int employeeId = employee.getEmployeeId();

            Actual actual = jpaApi.em().createQuery("FROM Actual a WHERE employeeId = :id ORDER BY actualDate DESC", Actual.class).setParameter("id", employeeId).setMaxResults(1).getSingleResult();

            String recentDate = actual.getActualDate();

            List<Actual> actuals = jpaApi.em().createQuery("FROM Actual a WHERE employeeId = :id AND actualDate = :recentDate", Actual.class).setParameter("id", employeeId).setParameter("recentDate", recentDate).getResultList();

            employee.setActuals(actuals);

            String clientAddress = actual.getEstimate().getContract().getClient().getFullAddress();

            try
            {
                String searchAddress = URLEncoder.encode(clientAddress, "UTF-8");

                URL url = new URL("https://maps.google.com/maps/api/geocode/json?key=AIzaSyAPXxNo-fiKxRlytPCdHAWLYEt2KB2bxpI&address=" + searchAddress);
                url.openConnection();

                ObjectMapper objectMapper = new ObjectMapper();
                Geocoder geocoder = objectMapper.readValue(url, Geocoder.class);

                String lat = geocoder.getResults().get(0).getGeometry().getLocation().getLat().toString();
                String lng = geocoder.getResults().get(0).getGeometry().getLocation().getLng().toString();

                JobCoords jobCoords = new JobCoords();

                jobCoords.setClientLastName(actuals.get(0).getEstimate().getContract().getClient().getFullName());
                jobCoords.setForemanLastName(employee.getFirstName() + " " + employee.getLastName());
                jobCoords.setLat(lat);
                jobCoords.setLng(lng);

                mapCoordinates.add(jobCoords);

            } catch (Exception e)
            {
                e.printStackTrace();
                return ok("Error retrieving map");
            }
        }

        return ok(views.html.pmwelcome.render(projectManager, foremen, mapCoordinates));
    }

    @Transactional
    public Result getPlan(int id)
    {
        Employee projectManager = jpaApi.em().createQuery("FROM Employee e WHERE employeeId = :id", Employee.class).setParameter("id", id).getSingleResult();

        Actual actual = jpaApi.em().createQuery("FROM Actual a WHERE employeeId = :id ORDER BY actualDate DESC", Actual.class).setParameter("id", id).setMaxResults(1).getSingleResult();

        byte[] plan = actual.getEstimate().getContract().getPlans();

        return ok(plan);
    }

    @Transactional
    public Result getPhotos(int id)
    {
        List<ProjectPicture> projectPictures = jpaApi.em().createQuery("FROM ProjectPicture p WHERE contractId = :id", ProjectPicture.class).setParameter("id", id).getResultList();

        List<ProjectPicWId> projectPicWIds = new ArrayList<>();

        for(int i = 0; i < projectPictures.size(); i++)
        {
            ProjectPicWId projectPicWId = new ProjectPicWId();

            projectPicWId.setContractId(projectPictures.get(i).getContractId());
            projectPicWId.setListNo(i + 1);
            projectPicWId.setPicture(projectPictures.get(i).getPicture());
            projectPicWId.setPictureDate(projectPictures.get(i).getPictureDate());
            projectPicWId.setPictureName(projectPictures.get(i).getPictureName());
            projectPicWId.setPictureId(projectPictures.get(i).getPictureId());

            projectPicWIds.add(projectPicWId);
        }

        return ok(views.html.projectphotos.render(projectPicWIds));
    }

    @Transactional
    public Result getPhoto(int photoId)
    {
        ProjectPicture projectPicture = jpaApi.em().createQuery("FROM ProjectPicture p WHERE pictureId = :id", ProjectPicture.class).setParameter("id", photoId).getSingleResult();

        return ok(projectPicture.getPicture()).as("image.jpg");
    }

    @Transactional
    public Result getForemanOverview(int id)
    {
        Employee foreman = jpaApi.em().createQuery("FROM Employee e WHERE employeeId = :id", Employee.class).setParameter("id", id).getSingleResult();

        String sqlSearch = "SELECT c.ContractId, SUM(e.EstimateHours) AS totalEstimateHours, SUM(a.ActualHours) AS totalActualHours " +
                "FROM actual a " +
                "JOIN estimate e " +
                "ON e.EstimateId = a.EstimateId " +
                "JOIN contract c " +
                "ON c.ContractId = e.ContractId " +
                "WHERE c.Completed = 1 AND a.EmployeeId = :id " +
                "GROUP BY c.ContractId";

        List<ProjectSummary> projectSummaries = jpaApi.em().createNativeQuery(sqlSearch, ProjectSummary.class).setParameter("id", id).getResultList();

        List<Contract> completedContracts = new ArrayList<>();

        for (ProjectSummary projectSummary : projectSummaries)
        {
            int x = projectSummary.getContractId();

            Contract contract = jpaApi.em().createQuery("FROM Contract c WHERE contractId = :id", Contract.class).setParameter("id", x).getSingleResult();

            completedContracts.add(contract);
        }


        return ok(views.html.foremanoverview.render(foreman, completedContracts, projectSummaries));
    }
}
