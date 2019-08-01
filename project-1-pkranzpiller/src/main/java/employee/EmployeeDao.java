package employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import shared.ConnectionUtil;


public class EmployeeDao{
	
	public ArrayList<Employee> getEmployees(){
		Connection con = new ConnectionUtil().getConnection();
		ResultSet results=null;
		ArrayList<Employee> employees = new ArrayList<Employee>();
		Employee employee;
		
		try {
			PreparedStatement ps = con.prepareStatement("select * from employee");
			results = ps.executeQuery();
			
			while(results.next()) {
				employee = new Employee();
				employee.setId(results.getInt("id"));
				employee.setUsername(results.getString("username"));
				employee.setFirstname(results.getString("firstname"));
				employee.setLastname(results.getString("lastname"));
				employee.setPassword(results.getString("password"));
				employees.add(employee);
			}
			con.close();
			return employees;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQL Error: Couldn't get employees");
			return null;
		}
	}
	
	public boolean authenticateEmployee(String username, String password) {
		Connection con = new ConnectionUtil().getConnection();
		ResultSet results = null;
		
		try {
			PreparedStatement ps = con.prepareStatement("select username, password from employee where username = ?");
			ps.setString(1, username);
			results = ps.executeQuery();
			results.next();
			
			if(username.equals(results.getString("username")) && password.equals(results.getString("password")) ){
				return true;
			}else
				return false;
		} catch (SQLException e) {
			System.out.println("Failed to authenticate user");
			return false;
		}
	}
	
	
	
}
