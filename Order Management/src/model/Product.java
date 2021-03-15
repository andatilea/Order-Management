package model;

	/**  
	 * Product.java - a simple class created in order to resemble the "product" table from the MYSQL database created;    
	 */ 
	public class Product {
		
	public String name;
	public int quantity;
	public double price;
	
	/**  
	* The first constructor creates a new Product object with the given parameters;
	* @param name : A variable of type String;
	* @param quantity: A variable of type Integer.It represents the number of products available in stock;
	* @param price: A variable of type Double;  
	* It will be used later, in Management, when the Order made by a Client is processed;
	*/ 
	public Product(String name, int quantity, double price) {
	this.name = name;
	this.quantity = quantity;
	this.price = price;
	}
	
	/**  
	* The second constructor will create a new Product object with all the parameters given as String data type;
	* @param name : A variable representing the name of the Product;
	* @param quantity: A variable representing the stock of the Product;
	* @param price: A variable representing the price of the Product; 
	* It will be used later, when the Application needs to find a Product which is already in the database;
	*/
	public Product(String name, String quantity, String price) {
	this.name = name;
	this.quantity = Integer.parseInt(quantity);
	this.price = Double.parseDouble(price);
	}

	/**  
	* Retrieve the value of the Integer: quantity;  
	* @return An Integer data type, representing the stock of the Product;
	*/ 
	public int getQuantity() {	
	return this.quantity;
	}
	
	/**  
	* Retrieve the value of the Double: price;  
	* @return A Double data type, representing the price of the Product wanted;
	*/ 
	public double getPrice() {
	return this.price;
		}
	}


