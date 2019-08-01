//package shared;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//
//import employee.Employee;
//import manager.Manager;
//
//import shared.ConnectionUtil;															FEEL FREE TO DELETE ME
//
//
//public abstract class Dao {
//	
//	public ArrayList<?> getUserData(User user){
//		
//		Connection con = new ConnectionUtil().getConnection();
//		ResultSet results=null;
//		ArrayList<User> userList = new ArrayList<>();
//		
//		try {
//			PreparedStatement ps = con.prepareStatement("select * from employee");
//			results = ps.executeQuery();
//			
//			while(results.next()) {
//				user = new User();
//				user.setId(results.getInt("id"));
//				user.setUsername(results.getString("username"));
//				user.setFirstname(results.getString("firstname"));
//				user.setLastname(results.getString("lastname"));
//				user.setPassword(results.getString("password"));
//				userList.add(user);
//			}
//			con.close();
//			return userList;
//		} catch (SQLException e) {
//			e.printStackTrace();
//			System.out.println("SQL Error: Couldn't get users");
//			return null;
//		}
//	}
//
//}
//
