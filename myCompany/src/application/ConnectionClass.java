package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionClass {

	Connection connection;
	Connection getConnection() throws ClassNotFoundException, SQLException {
		String dbName="myCompany";
		String dbUserName = "root";
		String password = "";
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		connection = DriverManager.getConnection("jdbc:mysql://localhost/"+dbName,dbUserName,password);

		
		return connection;
	}
}
