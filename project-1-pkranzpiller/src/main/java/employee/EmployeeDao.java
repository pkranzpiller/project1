package employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import shared.ConnectionUtil;
import shared.User;


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
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("SQL Error: Couldn't get employees");
			try {
				con.close();
			} catch (SQLException e1) {
				return null;
			}
			return null;
		}
	}
	
	public Employee authenticateEmployee(String username, String password){
		Connection con = new ConnectionUtil().getConnection();
		ResultSet results = null;
		Employee emp = new Employee();
		
		try {
			PreparedStatement ps = con.prepareStatement("select username, password, firstname, lastname, id from employee where username = ?");
			ps.setString(1, username);
			results = ps.executeQuery();
			
			if(results.next()) {
				if(username.equals(results.getString("username")) && password.equals(results.getString("password")) ){
					emp.setFirstname(results.getString("firstname"));
					emp.setLastname(results.getString("lastname"));
					emp.setUsername(results.getString("username"));
					emp.setId(results.getInt("id"));
					emp.setType(User.Type.EMPLOYEE);
					con.close();
					return emp;
				}
			}
			con.close();
			return null;
		} catch (Exception e) {
			try {
				con.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
				return null;
			}
			e.printStackTrace();
			return null;
		}
	}
	
	
	
}
