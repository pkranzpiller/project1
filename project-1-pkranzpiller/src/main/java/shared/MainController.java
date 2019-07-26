package shared;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import employee.Employee;
import manager.Manager;
import request.Request;

@Path("main")
public class MainController {
//	public static List<?> cache;
	
	public static List<Employee> employeeCache;
	public static List<Manager> managerCache;
	public static List<Request> requestCache;
	
	public MainController() {
		
	}
	
	
	
//	@GET
//	@Path("login")
//	@Produces(MediaType.TEXT_HTML)
//	public String getLogin(@QueryParam("username") String username, @QueryParam("password") String password) {
//		return ("You entered: " + username + " " + password);
//	}
	
	@POST
	@Path("login")
	@Consumes(MediaType.TEXT_HTML)
	@Produces(MediaType.TEXT_HTML)
	public String getLogin(@QueryParam("username") String username, @QueryParam("password") String password) {
		return ("POST: Username: " + username + " Password: " + password);
	}
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String index() {
		return "Welcome to the API: use   '/main/*'   where *= employee, manager, or resource";
	}
	
	
	
	
	
	
}
