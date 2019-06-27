package DB;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DB {
	
	private static Connection connection = null;
	
	public static Connection getConnection() throws SQLException {
		if(connection == null) {
			Properties properties = loadProperties();
			String url = properties.getProperty("dburl");
			connection = DriverManager.getConnection(url, properties);
		}
		return connection;
	}
	
	public static void closeConnection() throws SQLException {
		if(connection != null) {connection.close();}
	}
	
	private static Properties loadProperties() {
		try(FileInputStream fs = new FileInputStream("db.properties")){
			Properties properties = new Properties();
			properties.load(fs);
			return properties;
		}catch (Exception e) {
			return null;
		}
	}
}
