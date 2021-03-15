package connection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

	/**
	 * ConnectionFactory.java - makes possible the connection between the java Workspace and the MySQL database;
	 * Here, are presented the URL received when the connection was made, the user and the password for the LocalHost and how the Connection is created;
	 */
	public class ConnectionFactory {
		
	private static ConnectionFactory instance = new ConnectionFactory();
	/**
	 * this specific String in the URL value, is used due to the error caused by the difference in TimeZones;
	 */
	private static final String URL = "jdbc:mysql://localhost/javadbconnection?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	private static final String USER = "root";
	private static final String PASSWORD = "nEDAvy6aMAMI007!";
	private static final String DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";
	
	/**
	 * The constructor facilitates the connection using the existing connector;
	 */
	public ConnectionFactory() {
	try {
	Class.forName(DRIVER_CLASS);
	}catch(ClassNotFoundException e) {
	e.printStackTrace();
		}
	}
	
	/**
	 * This method creates a connection towards the database server;
	 * @return the connection variable;
	 */
	private Connection createConnection() {
	Connection connection = null;
	try {
	connection = DriverManager.getConnection(URL, USER, PASSWORD);
	}catch (SQLException e) {
	System.out.println("ERROR: Unable to Connect to Database.");
	}
	return connection;
	}
	
	/**
	 * Retrieve the value of the created Connection;
	 * @return A Connection data type;
	 */
	public static Connection getConnection() {
	return instance.createConnection();
		}
	}
