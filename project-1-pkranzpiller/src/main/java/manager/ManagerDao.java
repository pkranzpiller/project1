package manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import shared.ConnectionUtil;

public class ManagerDao {
	
	public boolean authenticateManager(String username, String password) {
		Connection con = new ConnectionUtil().getConnection();
		ResultSet results = null;
		
		try {
			PreparedStatement ps = con.prepareStatement("select username, password from manager where username = ?");
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
