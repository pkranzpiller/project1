package shared;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import employee.Employee;
import employee.EmployeeDao;
import manager.Manager;
import manager.ManagerDao;
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
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_HTML)
	public String getLogin(@FormParam("username") String username, @FormParam("password") String password) {
//		System.out.println("rawr");
//		System.out.println("Got Username: " + username);
		if(new EmployeeDao().authenticateEmployee(username, password)) {		//if authentication works for employee
			return "employee";
		}else if(new ManagerDao().authenticateManager(username, password)) {
			return "manager";
		}else
			return "authentication failed";
	}
	
	
	
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String index() {
		return "Welcome to the API: use   '/main/*'   where *= employee, manager, or resource";
	}


}