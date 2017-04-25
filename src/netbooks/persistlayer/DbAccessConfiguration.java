package netbooks.persist;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class DbAccessConfiguration {

	//assign values
	static final String DB_DRIVE_NAME = "com.mysql.jdbc.Driver";
	
	static final String DB_CONNECTION_URL = "jdbc:mysql://localhost:3306/imdb2";
	
	static final String DB_CONNECTION_USERNAME = "root"; //change to local user
	
	static final String DB_CONNECTION_PASSWORD = ""; //change to local passwd

}
