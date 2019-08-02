package request;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
	
	public ArrayList<Request> getRequests(){
		ArrayList<Request> requests = new ArrayList<Request>();
		Request request;
		Connection con = new ConnectionUtil().getConnection();
		ResultSet results;
		
		try {
			PreparedStatement ps = con.prepareStatement("select * from requests");
			results = ps.executeQuery();
			
			while(results.next()) {
				request = new Request();
				request.setId(results.getInt("id"));
				request.setApproval(results.getString("approval"));
				request.setDetails(results.getString("details"));
				request.setManagerid(results.getInt("managerid"));
				request.setEmployeeid(results.getInt("employeeid"));
				request.setImageid(results.getString("imageid"));
				requests.add(request);
			}
			con.close();
			return requests;
		} catch (Exception e) {
			System.out.println("Couldn't get requests");
			try {
				con.close();
				return requests;
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return requests;
	}

}
