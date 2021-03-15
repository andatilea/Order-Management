package connection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

	/**
	 * DbUtil.java - presents methods used in order to close the created connection;
	 * Also, the Statement and the Result Set will be closed after the execution of the Query;
	 */
	public class DbUtil {
		
	/**
	 * The method checks if the Connection is established. If the condition is true, then the Connection will be closed;
	 * @param connection;
	 */
	public static void close(Connection connection) {
	if (connection != null) {
	try {
	connection.close();
	}catch (SQLException e) {}
		}
	}

	/**
	 * This method is used in order to close the executed Statement;
	 * @param statement;
	 */
	public static void close(Statement statement) {
	if (statement != null) {
	try{
	statement.close();
	}catch (SQLException e) {}
		}
	}

	/**
	 * This method is used in order to close the ResultSet obtained;
	 * @param resultSet;
	 */
	public static void close(ResultSet resultSet) {
	if (resultSet != null) {
	try {
	resultSet.close();
	}catch (SQLException e) {}
			}
		}
	}
