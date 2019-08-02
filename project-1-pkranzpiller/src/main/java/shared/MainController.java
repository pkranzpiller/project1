package shared;

import java.net.URISyntaxException;
import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
	public Response getLogin(@FormParam("username") String username, @FormParam("password") String password) {
		
		if(new EmployeeDao().authenticateEmployee(username, password)) {
			try {
				java.net.URI location = new java.net.URI("../employeeMenu.html");
				return Response.temporaryRedirect(location).build();
			} catch (URISyntaxException e) {
				System.out.println("Couldn't redirect user");
				return null;
			}
		}else if(new ManagerDao().authenticateManager(username, password)) {
			try {
				java.net.URI location = new java.net.URI("../managerMenu.html");
				return Response.temporaryRedirect(location).build();
			} catch (URISyntaxException e) {
				System.out.println("Couldn't redirect user");
				return null;
			}	
		}else
			return null;
	}
	
	
	
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String index() {
		return "Welcome to the API: use   '/main/*'   where *= employee, manager, or resource";
	}


}