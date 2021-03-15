package model;

	/**  
	 * Client.java - a simple class created in order to resemble the "client" table from the MYSQL database created;   
	 */ 
	public class Client {
		
	public String name;
	public String address;
	
	/**  
	* The constructor creates a new Client object with the given parameters;
	* @param name : A variable of type String;
	* @param address: A variable of type String;  
	*/ 
	public Client(String name, String address) {
	this.name = name;
	this.address = address;
	}

	/**  
	* Retrieve the value of the String: name;  
	* @return A String data type, representing the name of the Client;
	*/  
	public String getName() {
	return this.name;
	}
	
	/**  
	* Retrieve the value of the String: address;  
	* @return A String data type, representing the address of the Client;
	*/
	public String getAddress() {
		return this.address;
		}
	}

