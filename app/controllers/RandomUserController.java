package controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import models.*;
import play.db.jpa.JPAApi;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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

            return ok(views.html.randomusers.render(randomUser.getResults()));

        } catch (Exception e)
        {
            return ok("Unable to get users: " + e.getMessage());
        }
    }

    @Transactional
    public Result generateDb()
    {
        for (int i = 1; i < 10; i++)
        {
            Category category = new Category();
            category.setCategoryId(i);

            if (i == 1)
            {
                category.setCategoryName("Excavation/ Debris Removal");
            }
            else if (i == 2)
            {
                category.setCategoryName("Irrigation");
            }
            else if (i == 3)
            {
                category.setCategoryName("Patio");
            }
            else if (i == 4)
            {
                category.setCategoryName("Outdoor Chimney");
            }
            else if (i == 5)
            {
                category.setCategoryName("Fire Pit");
            }
            else if (i == 6)
            {
                category.setCategoryName("Retaining Wall");
            }
            else if (i == 7)
            {
                category.setCategoryName("Soil Preparation");
            }
            else if (i == 8)
            {
                category.setCategoryName("Plant Installation");
            }
            else
            {
                category.setCategoryName("Sod Installation");
            }

            jpaApi.em().persist(category);
        }
        return ok("done");
    }
}
/*
//  Generate employees....

URL url = new URL("https://randomuser.me/api/?nat=us&results=4&gender=male");
url.openConnection();

ObjectMapper objectMapper = new ObjectMapper();
RandomUser randomUser = objectMapper.readValue(url, RandomUser.class);

List<User> users = randomUser.getResults();

int x = 1;

for (User user : users)
{
    Random random = new Random();
    Employee employee = new Employee();

    employee.setFirstName(user.getName().getFirst());
    employee.setLastName(user.getName().getLast());

    String hireDate = user.getDob().substring(0, 10);

    if (x == 1)
    {
        int year = random.nextInt(3) + 2008;
        employee.setHireDate("" + year + hireDate.substring(4));

        employee.setTitle("Foreman");
        employee.setHourlyWage(new BigDecimal("20.00"));
    }
    else if (x == 2)
    {
        int year = random.nextInt(3) + 2011;
        employee.setHireDate("" + year + hireDate.substring(4));

        employee.setTitle("Assistant Foreman");
        employee.setHourlyWage(new BigDecimal("17.50"));
    }
    else
    {
        int year = random.nextInt(3) + 2014;
        employee.setHireDate("" + year + hireDate.substring(4));

        employee.setTitle("Crewmember");
        employee.setHourlyWage(new BigDecimal("15.00"));
    }

    employee.setEmployeeId(x);

    jpaApi.em().persist(employee);

    x++;
}


// Generate equipment...
{
Random random = new Random();

for (
        int i = 1;
        i < 8; i++)

{
    Equipment equipment = new Equipment();

    equipment.setEquipmentHours(random.nextInt(500) + 75);
    equipment.setEquipmentId(i);
    equipment.setContractId(1);

    if (i < 3)
    {
        equipment.setEquipmentName("Mini-X 0" + i);
    }
    else if (i < 5)
    {
        equipment.setEquipmentName("Mini-Skid 0" + (i - 2));
    }
    else
    {
        equipment.setEquipmentName("Skid 0" + (i - 4));
    }

    jpaApi.em().persist(equipment);
}

return ok("done");
}


// Generate client
try
{
    URL url = new URL("https://randomuser.me/api/?nat=us&results=4");
    url.openConnection();

    ObjectMapper objectMapper = new ObjectMapper();
    RandomUser randomUser = objectMapper.readValue(url, RandomUser.class);

    List<User> users = randomUser.getResults();

    int x = 1;

    for (User user : users)
    {
        Random random = new Random();
        Client client = new Client();

        client.setFirstName(user.getName().getFirst());
        client.setLastName(user.getName().getLast());
        client.setAddress(user.getLocation().getStreet());
        client.setCity("Little Rock");
        client.setZipCode("7220" + random.nextInt(10));
        client.setEmail(user.getEmail());
        client.setClientId(x);
        client.setState("AR");

        client.setPhone(user.getPhone().replace(user.getPhone().subSequence(0,5),"(501)"));

        jpaApi.em().persist(client);

        x++;
    }

    return ok("ok");

} catch (Exception e)
{
    return ok("Unable to get users: " + e.getMessage());
}
}

// Generate project
{
Random random = new Random();

for (int i = 1; i < 5; i++)
{
    Contract contract = new Contract();

    contract.setClientId(i);
    contract.setContractId(i);
    contract.setStartDate("" + (random.nextInt(2) + 2016) + "-" + (random.nextInt(12) + 1) + "-" + (random.nextInt(30) + 1));

    jpaApi.em().persist(contract);
}
return ok("done");
}

//Generate category
 {
for (int i = 1; i < 10; i++)
{
    Category category = new Category();
    category.setCategoryId(i);

    if (i == 1)
    {
        category.setCategoryName("Excavation/ Debris Removal");
    }
    else if (i == 2)
    {
        category.setCategoryName("Irrigation");
    }
    else if (i == 3)
    {
        category.setCategoryName("Patio");
    }
    else if (i == 4)
    {
        category.setCategoryName("Outdoor Chimney");
    }
    else if (i == 5)
    {
        category.setCategoryName("Fire Pit");
    }
    else if (i == 6)
    {
        category.setCategoryName("Retaining Wall");
    }
    else if (i == 7)
    {
        category.setCategoryName("Soil Preparation");
    }
    else if (i == 8)
    {
        category.setCategoryName("Plant Installation");
    }
    else
    {
        category.setCategoryName("Sod Installation");
    }

    jpaApi.em().persist(category);
}
return ok("done");
}

 */