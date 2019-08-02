package request;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import shared.ConnectionUtil;

public class RequestDao {
	
	public void insertRequest(Request request){
		Connection con = new ConnectionUtil().getConnection();
		
		try {
			PreparedStatement ps = con.prepareStatement("insert into requests(imageid, employeeid, approval, details) values(?, ?, ?, ?)");
			ps.setString(1, request.getImageid());
			ps.setInt(2, request.getEmployeeid());
			ps.setString(3, "pending");
			ps.setString(4, request.getDetails());
			ps.executeUpdate();
			con.close();
		} catch (Exception e) {
			try {
				con.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			System.out.println("SQL Error: Couldn't insert request");
			e.printStackTrace();
		}
		
	}

}
