package dataAccess;
import java.sql.*;
import model.*;

	/**  
	 * ClientDAO.java - this is a more complex class, created in order to perform all of the operations needed for a Client;
	 * Here, the methods are designed using the Connection already established with the SQL database;  
	 */ 
	public class ClientDAO {
		
	/**  
	* This method inserts a new Client object in the Database created using Strings with all the needed Data;
	* @param conn : A variable representing the Connection with the SQL;
	* @param clientName : A variable of type String. It represents the Name of the new Client;
	* @param clientAddress: A variable of type String. It represents the Address of the new Client;
	*/ 
	public void insertClient(Connection conn, String clientName, String clientAddress) {
	String sql = "INSERT INTO javadbconnection.client(name,address) Values(" + clientName + ", "+ clientAddress+ ")";
	try {
	PreparedStatement stmt = conn.prepareStatement(sql);
	stmt.executeUpdate(sql);
	}catch (SQLException e) {
	System.err.println(e.getMessage()); 
		}
	}
	
	/**  
	* This method deletes a Client object, from the Database, using its name;
	* @param conn : A variable representing the Connection with the SQL;
	* @param clientName : A variable of type String. It represents the Name of the Client which will be deleted;
	*/ 
	public void deleteClient(Connection conn, String clientName) {
	String sql = "Delete from javadbconnection.client where name =" + clientName;
	try {
	PreparedStatement stmt = conn.prepareStatement(sql);
	stmt.executeLargeUpdate(sql);
	}catch (SQLException e) {
	System.err.println(e.getMessage()); 
	}catch (SecurityException e) {
	e.printStackTrace();
		}
	}
	
	/**  
	* This method finds a Client object from the Database created using its name;
	* @param conn : A variable representing the Connection with the SQL;
	* @param clientName : A variable of type String. It represents the Name of the Client which will be searched;
	* The method will be used in Management, when an Order will be processed;
	* The result of this method tells the Application if the Client is Registered or not;
	*/
	public Client findClient(Connection con, String name) {
	String [] args = new String[10];
	Statement stat;
	try {
	stat = con.createStatement();
	String sql = "Select * from javadbconnection." + Client.class.getSimpleName() + " " + "where name = " + name ;
	ResultSet rs = stat.executeQuery(sql);
	while(rs.next()) {
	args[0] = rs.getString("name");
	args[1] = rs.getString("address");
	}
	} catch (SQLException e) {
	e.printStackTrace();
	}
	Client c = new Client(args[0], args[1]);
	return c;
		}
	}