package controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import models.*;
import play.db.jpa.JPAApi;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomUserController extends Controller
{
    JPAApi jpaApi;

    @Inject
    public RandomUserController(JPAApi jpaApi)
    {
        this.jpaApi = jpaApi;
    }

    @Transactional
    public Result getRandomUsers()
    {
        try
        {
            URL url = new URL("https://randomuser.me/api/?nat=us&results=100");
            url.openConnection();

            ObjectMapper objectMapper = new ObjectMapper();
            RandomUser randomUser = objectMapper.readValue(url, RandomUser.class);

            List<User> users = randomUser.getResults();

            return ok(views.html.randomusers.render(randomUser.getResults()));

        } catch (Exception e)
        {
            return ok("Unable to get users: " + e.getMessage());
        }
    }

    @Transactional
    public Result updateClients()
    {
        Random random = new Random();

        List<Project> projects = jpaApi.em().createQuery("FROM Project p").getResultList();

        for (Project project : projects)
        {
            int x = random.nextInt(14) + 2004;

            project.setStartDate(project.getStartDate().replace(project.getStartDate().subSequence(0,4),"" + x));
        }



        return ok("done");
    }
}
