package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {
	public static Connection con = null;
	public static ConnectDB instance = new ConnectDB();
	public static ConnectDB getInstance() {
		return instance;
	}
	public static void connect() throws SQLException {
		String url = "jdbc:sqlserver://localhost:1433;databasename=QLyLuong";
		String user = "sa";
		String password = "sapassword";
		con = DriverManager.getConnection(url, user, password);	
	}
	public void disconnect() {
		if(con != null) {
			try {
				con.close();
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public static Connection getConnection() {
		return con;
	}
	public static void main(String[] args) throws SQLException {
		connect();
		System.out.println(con);
	}
}