package shared;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.json.JSONArray;
import org.json.JSONObject;

import employee.Employee;
import employee.EmployeeDao;
import manager.Manager;
import manager.ManagerDao;
import request.Request;
import request.RequestDao;


@Path("main")
public class MainController {
	
	public Employee currentEmployee = new Employee();
	public Manager currentManager = new Manager();
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
		
		if( (currentEmployee = new EmployeeDao().authenticateEmployee(username, password)) != null) {
			try {
				java.net.URI location = new java.net.URI("../employeeMenu.html");
				
				return Response.temporaryRedirect(location).build();
			} catch (URISyntaxException e) {
				System.out.println("Couldn't redirect user");
				return null;
			}
		}else if( (currentManager = new ManagerDao().authenticateManager(username, password)) != null) {
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
	@Path("logoutEmployee")
	public void logoutEmployee(@Context HttpServletResponse response) {
		try {
			currentEmployee = null;
			response.sendRedirect("../../index.html");
		} catch (IOException e) {
			System.out.println("Couldn't log employee out");
			e.printStackTrace();
		}
	}
	
	@GET
	@Path("logoutManager")
	public void logoutManager(@Context HttpServletResponse response) {
		try {
			currentManager = null;
			response.sendRedirect("../../index.html");
		} catch (IOException e) {
			System.out.println("Couldn't log manager out");
			e.printStackTrace();
		}
	}
	
	@POST
	@Path("upload")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public void getImage(@Context HttpServletRequest request) {
		ServletFileUpload sf = new ServletFileUpload(new DiskFileItemFactory());
		List<FileItem> multifiles;
		try {
			multifiles = sf.parseRequest(request);
			Request req = new Request();
			req.setEmployeeid(currentEmployee.getId());
			for(FileItem file : multifiles) {
				if(file.getFieldName().equals("receipt")) {
					file.write(new File("/home/patrick/git/project1/project-1-pkranzpiller/images/" + file.getName()));
					req.setImageid(file.getName());
				}else if(file.getFieldName().equals("description")) {
					req.setDetails(file.getString());
				}
				//TODO check for same names. Maybe even generate new names for image files
				//TODO enable the addition of a request with multiple images
			}
			new RequestDao().insertRequest(req);
			System.out.println("File uploaded");
		} catch (Exception e) {
			System.out.println("FileUpload Exception");
			e.printStackTrace();
		}
	}
	
	@POST
	@Path("getRequests")
	@Produces(MediaType.APPLICATION_JSON)
	public String getRequests() {
		String message;
		requestCache = new RequestDao().getRequests();
		JSONObject json = new JSONObject();
//		json.put("id", "request");
		JSONArray array = new JSONArray();
		JSONObject item;
		
		for(Request request : requestCache) {
			item = new JSONObject();
			item.put("id", request.getId());
			item.put("imageid", request.getImageid());
			item.put("employeeid", request.getEmployeeid());
			item.put("approval", request.getApproval());
			item.put("managerid", request.getManagerid());
			item.put("description", request.getDetails());
			array.put(item);
		}
//		json.put("request", array);
		message = array.toString();
		return message;
	}
	
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String index() {
		return "Welcome to the API: use   '/main/*'   where *= employee, manager, or resource";
	}


}