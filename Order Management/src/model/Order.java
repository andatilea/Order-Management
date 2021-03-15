package model;

	/**  
	 * Order.java - a simple class created in order to resemble the "order" table from the MYSQL database created;  
	 */
	public class Order {
		
	public String clientName;
	public String productName;
	public int quantity;
	
	/**  
	* The constructor creates a new Order object with the given parameters;
	* @param clientName : A variable of type String, representing the name of the Client;
	* @param productName: A variable of type String, representing the name of the Product;  
	* @param quantity: A variable of type Integer. It represents the number of products ordered;
	*/ 
	public Order(String clientName, String productName, int quantity) {
	this.clientName = clientName;
	this.productName = productName;
	this.quantity = quantity;
		}
	}
