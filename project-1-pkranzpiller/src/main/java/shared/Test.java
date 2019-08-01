package shared;

import java.util.ArrayList;

import employee.Employee;
import employee.EmployeeDao;

public class Test {

	public static void main(String[] args) {
		ArrayList<Employee> employeeCache;
		
		employeeCache = new EmployeeDao().getEmployees();
		
		System.out.println(employeeCache.get(1).getUsername());

	}

}
