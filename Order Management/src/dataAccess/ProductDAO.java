package dataAccess;
import java.lang.reflect.Field;
import java.sql.*;
import model.*;

	/**  
	 * ProductDAO.java - this is a more complex class, created in order to perform all of the operations needed for a Product;
	 * Here, the methods are designed using the Connection already established with the SQL database;  
	 */	
	public class ProductDAO {
		
	/**  
	* This method inserts a new Product object in the Database created using a String with all the needed Data;
	* @param conn : A variable representing the Connection with the SQL;
	* @param productName : A variable of type String. It represents the Name of the new Product;
	* @param productQuantity: A variable of type String. It represents the Quantity of the new Product;
	* @param productPrice: A variable of type String. It represents the Price of the new Product;
	*/ 
	public void insertProduct(Connection conn, String productName, String productQuantity, String productPrice) {
	String sql = "INSERT INTO javadbconnection.product(name,quantity,price) Values(" + productName + ", "+ productQuantity + ", "+ productPrice + ")";
	try {
	PreparedStatement stmt = conn.prepareStatement(sql);
	stmt.executeUpdate(sql);
	}catch(SQLException e) {
	System.err.println(e.getMessage()); 
		}
	}
	
	/**  
	* This method deletes a Product object, from the Database, using its name;
	* @param conn : A variable representing the Connection with the SQL;
	* @param productName : A variable of type String. It represents the Name of the Product which will be deleted;
	*/ 
	public void deleteProduct(Connection conn, String productName) {
	String sql = "Delete from javadbconnection.product where name =" + productName;
	try {
	PreparedStatement stmt = conn.prepareStatement(sql);
	stmt.executeLargeUpdate(sql);
	}catch(SQLException e) {
	System.err.println(e.getMessage()); 
	}catch(SecurityException e) {
	e.printStackTrace();
		}
	}
	
	/**  
	* This method finds a Product object, from the Database created, using its name;
	* @param conn : A variable representing the Connection with the SQL;
	* @param name : A variable of type String. It represents the Name of the Product which will be searched;
	* The method will be used in Management, in order to get the total stock for a Product which has been ordered;
	* Also, we use this method to get the correct Price, along with computing the total amount the Client has to pay;
	*/
	public Product findProduct(Connection con, String name) {			
	String [] args = new String[10];
	Statement stat;
	try {
	stat = con.createStatement();
	String sql = "Select * from javadbconnection." + Product.class.getSimpleName() + " " + "where name = " + name ;
	ResultSet rs = stat.executeQuery(sql);
	while(rs.next()) {
	args[0] = rs.getString("name");
	args[1] = Integer.toString(rs.getInt("quantity"));
	args[2] = Double.toString(rs.getDouble("price")); 
	}
	}catch(SQLException e) {
	e.printStackTrace();
	}
	Product p = new Product(args[0], args[1], args[2]);
	return p;
	}
	
	/**
	 * This method updates the Product object after each Order which has been processed, the stock will decrease as soon as the Order is accepted;
	 * @param p : A variable representing a Product object;
	 * @param conn : A variable representing the Connection with the SQL;
	 */
	public void updateProduct(Product p, Connection conn) {
	Field[] fields = Product.class.getDeclaredFields();
	try {
	Statement stat = conn.createStatement();
	String sql = "update javadbconnection." + p.getClass().getSimpleName() + " " + "set quantity =" + "'" + fields[1].get(p) + "'" +" where name = " + fields[0].get(p);
	stat.executeLargeUpdate(sql);
	}catch(SQLException | IllegalArgumentException | IllegalAccessException e) {
	e.printStackTrace();
			}
		}		
	}