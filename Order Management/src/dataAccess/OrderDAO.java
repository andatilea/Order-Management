package dataAccess;
import java.sql.*;

	/**  
	 * OrderDAO.java - this is a more complex class, created in order to perform all of the operations needed for an Order;
	 * Here, the methods are designed using the Connection already established with the SQL database;  
	 */
	public class OrderDAO {
		
	/**  
	*This method inserts a new Order object in the Database created using a String with all the needed Data;
	*@param conn : A variable representing the Connection with the SQL;
	*@param clientName : A variable of type String. It represents the Name of the Client which made the Order;
	*@param productName: A variable of type String. It represents the Name of the ordered Product;
	*@param orderedQuantity: A variable of type String. It represents the Quantity wanted for the current Product;
	*/ 
	public void insertOrder(Connection conn, String clientName, String productName, int orderedQuantity) {
	String sql = "INSERT INTO javadbconnection.order(clientName,productName,quantity) Values(" + clientName + ", "+ productName + ", "+ orderedQuantity+ ")";
	try {
	PreparedStatement stmt = conn.prepareStatement(sql);
	stmt.executeUpdate(sql);
	}catch (SQLException e) {
	System.err.println(e.getMessage()); 
		}
	}
	
	/**
	 * This first 'EXTRA' method will insert a new Delivery as soon as the Order has been processed;
	 * @param conn : The variable representing the connection with the SQL Database;
	 * @param clientName : The String representing the Name of the Client which made the Order;
	 * @param clientAddress : The String representing the Address of the Client which made the Order;
	 * @param quantity : The String representing the Quantity ordered;
	 * @param totalPrice : The string representing the total amount the Client will have to pay at the delivery;
	 */
	public void insertDelivery(Connection conn, String clientName, String clientAddress, String quantity, String totalPrice) {
		String sql = "INSERT INTO javadbconnection.delivery(clientName,clientAddress,quantity,totalPrice) Values('" + clientName + "'," + "'" + clientAddress + "', "+ quantity + ", "+ totalPrice + ")";
		try {
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.executeUpdate(sql);
		}catch (SQLException e) {
		System.err.println(e.getMessage()); 
			}
		}
	
	/**
	 * The second 'EXTRA' method will insert all the understocked Products into a new table of the SQL Database. This new table, is created as an extra help for the provider to re-make the stock;
	 * @param conn : The variable representing the connection with the SQL Database;
	 * @param client  : The String representing the Name of the Client which made the Order;
	 * @param amount : The Integer representing the Ordered quantity;
	 * @param product : The String representing the wanted Product;
	 * @param stock : The Integer representing the total number of available pieces;
	 */
	public void understockCase(Connection conn, String client, int amount, String product, int stock) {
	String sql = "INSERT INTO javadbconnection.understock_products(productName, current_stock, ordered_quantity) Values(" + product + "," + stock + "," + amount + ")";
	try {
	PreparedStatement stmt = conn.prepareStatement(sql);
	stmt.executeUpdate(sql);
	}catch (SQLException e) {
	System.err.println(e.getMessage()); 
			}
		}
	}