package connectDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB_MySQL {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		System.out.println("Driver loading...");
		Class.forName("com.mysql.cj.jdbc.Driver");
		System.out.println("Driver loaded.");
		
		System.out.println("Connecting...");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost/qlluongsp", "root", "cuchuoi123");
		System.out.println("Connected.");
		
		con.close();
	}
}
