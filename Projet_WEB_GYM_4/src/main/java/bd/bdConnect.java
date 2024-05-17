package bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class bdConnect {
	static Connection connection = null;

	public static Connection getConnection() throws ClassNotFoundException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost/gym_management", "root", "");

		} catch (SQLException e) {

		}
		return connection;
	}

}
