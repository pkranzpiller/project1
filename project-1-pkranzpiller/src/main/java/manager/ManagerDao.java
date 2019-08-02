package manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import shared.ConnectionUtil;
import shared.User;

public class ManagerDao {
	
	public Manager authenticateManager(String username, String password) {
		Connection con = new ConnectionUtil().getConnection();
		ResultSet results = null;
		Manager manager = new Manager();
		
		try {
			PreparedStatement ps = con.prepareStatement("select username, password,firstname, lastname, id from manager where username = ?");
			ps.setString(1, username);
			results = ps.executeQuery();
			
			
			if(results.next()) {
				if(username.equals(results.getString("username")) && password.equals(results.getString("password")) ){
					manager.setFirstname(results.getString("firstname"));
					manager.setLastname(results.getString("lastname"));
					manager.setUsername(results.getString("username"));
					manager.setId(results.getInt("id"));
					manager.setType(User.Type.MANAGER);
					con.close();
					return manager;
				}
			}
			con.close();
			return null;
		} catch (Exception e) {
			try {
				con.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			return null;
		}
	}

}
