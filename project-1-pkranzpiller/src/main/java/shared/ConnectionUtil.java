package shared;

import java.sql.Connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class ConnectionUtil {
	private Connection connection;
	
//	private static ConnectionUtil connectionInstance;
//	
//	private ConnectionUtil(String url, String user, String password) {
//		try {
//			this.connection = DriverManager.getConnection(url, user, password);
//		} catch (SQLException e) {
//			System.out.println("Error: SQL Connection Failed");
//			e.printStackTrace();
//		}
//	}
//	
//	public static synchronized ConnectionUtil getInstance() {
//		if(connectionInstance == null) {
//			connectionInstance = new ConnectionUtil("jdbc:postgresql://localhost:5432/postgres", "postgres","");
//			
//			return connectionInstance;
//		}
//		return connectionInstance;
//	}
//	
//	public synchronized Connection getConnection() {
//		return connection;
//	}
//	
//	public void closeConnection() {
//		try {
//			connection.close();
//		} catch (SQLException e) {
//			System.out.println("Failed to close connection");
//			e.printStackTrace();
//		}
//	}
	
	public ConnectionUtil() {
		try {
			Context initContext = new InitialContext();
			DataSource dataSource = (DataSource) initContext.lookup("java:/comp/env/jdbc/postgres");
			connection = dataSource.getConnection();
		} catch (Exception e) {
			System.out.println("Error establishing connection");
			e.printStackTrace();
		}
	}
	
	public Connection getConnection() {
		return connection;
	}
	
	
}
