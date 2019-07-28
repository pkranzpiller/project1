package shared;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.sql.DataSourceDefinition;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import employee.Employee;
import employee.EmployeeDao;
import manager.Manager;
import request.Request;


//@DataSourceDefinition(name="jdbc/postgres",
//className="org.postgresql.Driver",
//url="jdbc:postgresql://localhost:5432/postgres",
//user="postgres",
//password=""
//)

@Path("main")
public class MainController {
//	public static List<?> cache;
	
	public static ArrayList<Employee> employeeCache;
	public static ArrayList<Manager> managerCache;
	public static ArrayList<Request> requestCache;
	
	public MainController() {
		employeeCache = new EmployeeDao().getEmployees();
	}
	
	
	@POST
	@Path("login")
	@Consumes(MediaType.TEXT_HTML)
	@Produces(MediaType.TEXT_HTML)
	public String getLogin(@QueryParam("username") String username, @QueryParam("password") String password) {
//		System.out.println("rawr");
		return ("POST: Username: " + username + " Password: " + password + " random user: " + employeeCache.get(0).getUsername());
	}
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String index() {
		return "Welcome to the API: use   '/main/*'   where *= employee, manager, or resource";
	}
	
	
	
	
	
	
}
