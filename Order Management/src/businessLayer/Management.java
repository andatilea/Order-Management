package businessLayer;
import java.sql.Connection;
import businessLayer.validators.ClientValidator;
import connection.ConnectionFactory;
import dataAccess.ClientDAO;
import dataAccess.OrderDAO;
import dataAccess.ProductDAO;
import model.Client;
import model.Product;

	/**
	 * Management.java - this complex Class contains all the Logic of the Applcation;
	 * For each line of requests, the Application will do the corresponding command;
	 */
	public class Management {
	
	private Connection Myconnection = ConnectionFactory.getConnection();
	private ProductDAO pd = new ProductDAO();
	private OrderDAO od = new OrderDAO();
	private ClientDAO cd = new ClientDAO();
	private ClientBLL C_bll = new ClientBLL();
	private ProductBLL P_bll = new ProductBLL();
	private OrderBLL O_bll = new OrderBLL();
	private ClientValidator C_validate = new ClientValidator();
	
	/**
	 * The method is used in order to find the total Stock for the ordered Product. We search the Product after its name;
	 * @param productOrdered : A variable of type String, representing the name of the Product;
	 * @return A variable of Integer data type, which represents the available number of the requested Product;
	 */
	public int getStock(String productOrdered) {
	Product p = pd.findProduct(Myconnection, productOrdered);
	return p.getQuantity();
	}
	
	/**
	 * The method is used in order to find the correct Price for the ordered Product. We search the Product after its name;
	 * @param productOrdered : A variable of type String, representing the name of the Product;
	 * @return A variable of Double data type, which represents the correct price;
	 */
	public double getCorrectPrice(String productOrdered) {
	Product p = pd.findProduct(Myconnection, productOrdered);
	return p.getPrice();
	}

	/**
	 * The method is used in order to find if the ordering Client is registered and appears on the Client Report;
	 * @param orderingClient : A variable of type String, representing the name of the Client which placed the Order;
	 * @return A variable of String data type. If the Client is not registered the Order will not be processed;
	 */
	public String checkClient(String orderingClient) {
	Client c = cd.findClient(Myconnection, orderingClient);
	return c.getName();
	}
	
	/**
	 * The method is used in order to find the Address attached to the ordering Client. This function helps the insertion into the Delivery SQL Table;
	 * @param orderingClient : the name of the client placing the Order;
	 * @return the String representing the Address of the found Client;
	 */
	public String getAddress(String orderingClient) {
	Client c = cd.findClient(Myconnection,orderingClient);
	return c.getAddress();
	}
	
	/**
	 * A method which takes all the possible cases available in the command lines regarding Clients and perform the specific task;
	 * @param line: A variable of String data type which represents the line we are computing;
	 */
	public void processLineClient(String line) {
	if(line.contains("Insert client")) {
	int delimitator = line.indexOf(':');
	String content = line.substring(delimitator+1).trim();
	String[] inputs = content.split(",");
	String[] trimmedInputs = new String[inputs.length];
	for(int i =0  ; i <  inputs.length ; i ++) {
	trimmedInputs[i] = inputs[i].trim();
	}
	String clientName = "'" + trimmedInputs[0] + "'";
	String clientAddress = "'" + trimmedInputs[1] + "'";
	cd.insertClient(Myconnection, clientName, clientAddress);
	System.out.println("client " + clientName + " inserted!");	 
	}
	if(line.contains("Delete client")) {
	int delimitator = line.indexOf(':');
	String content = line.substring(delimitator+1).trim();
	String[] inputs = content.split(",");	 
	String[] trimmedInputs = new String[inputs.length];
	for(int i =0  ; i <  inputs.length ; i ++) {
	trimmedInputs[i] = inputs[i].trim();
	}
	String clientName = "'" + trimmedInputs[0] + "'";
	cd.deleteClient(Myconnection, clientName);
	System.out.println("client " + clientName + " deleted!");
		}
	}
	
	/**
	 * A method which takes all the possible cases available in the command lines regarding Products and perform the specific task;
	 * @param line: A variable of String data type which represents the line we are computing;
	 */
	public void processLineProduct(String line) {
	if(line.contains("Insert product")) {
	int delimitator = line.indexOf(':');
	String content = line.substring(delimitator+1).trim();
	String[] inputs = content.split(",");
	String[] trimmedInputs = new String[inputs.length];
	for(int i =0  ; i <  inputs.length ; i ++) {
	trimmedInputs[i] = inputs[i].trim();
	}
	String productName = "'" + trimmedInputs[0] + "'";
	String productQuantity = "'" + trimmedInputs[1] + "'";
	String productPrice = "'" + trimmedInputs[2] + "'";
	pd.insertProduct(Myconnection, productName, productQuantity, productPrice);
	System.out.println("product " + productName + " inserted!");
	}
	if(line.contains("Delete product")) {
	int delimitator = line.indexOf(':');
	String content = line.substring(delimitator+1).trim();
	String[] inputs = content.split(","); 
	String[] trimmedInputs = new String[inputs.length];
	for(int i =0  ; i <  inputs.length ; i ++) {
	trimmedInputs[i] = inputs[i].trim();
	}
	String productName = "'" + trimmedInputs[0] + "'";
	pd.deleteProduct(Myconnection, productName);
	System.out.println("product " + productName + " deleted!");
		}
	}
	
	/**
	 * The method presents the variables declared in the Order command. It checks them, and after that the Application will check the stock, the registration.
	 * @param line : A variable of String data type which represents the line we are computing;
	 */
	public void processLineOrder(String line) {
	if(line.contains("Order")) {
	int delimitator = line.indexOf(':');
	String content = line.substring(delimitator+1).trim();
	String[] inputs = content.split(",");
	String[] trimmedInputs = new String[inputs.length];
	for(int i =0  ; i <  inputs.length ; i ++) {
	trimmedInputs[i] = inputs[i].trim();
	} 
	String orderingClient = "'" + trimmedInputs[0] + "'";
	String clientRegistered = checkClient(orderingClient);
	String productOrdered = "'" + trimmedInputs[1] + "'";	
	int amount = Integer.parseInt(trimmedInputs[2]);	
	int stock = getStock(productOrdered);
	double productPrice = getCorrectPrice(productOrdered);
	double totalPrice = amount * productPrice;
	if(clientRegistered == null) {
	C_validate.validate(orderingClient); 
	} else {	
	if(amount > stock){
	O_bll.understockCase(Myconnection,clientRegistered, amount, productOrdered, stock);
	} else if(amount <= stock){
	od.insertOrder(Myconnection, orderingClient,productOrdered,amount);
	Product p = new Product(productOrdered, stock - amount, productPrice);
	pd.updateProduct(p, Myconnection);
	String address = getAddress(orderingClient);
	String price = Double.toString(totalPrice);
	od.insertDelivery(Myconnection, clientRegistered, address, trimmedInputs[2], price);
	O_bll.createBill(clientRegistered, productOrdered, amount, totalPrice); 
				} }
	} }

	/**
	 * The method treats the case in which the given command requests a PDF Clients Report;
	 * @param line : A variable of String data type which represents the line we are computing;
	 */
	public void processLineReportClient(String line) {
	if(line.contains("Report client")) {
	C_bll.insertClientPDF();
		} 
	}
	
	/**
	 * The method treats the case in which the given command requests a PDF Products Report;
	 * @param line : A variable of String data type which represents the line we are computing;
	 */
	public void processLineReportProduct(String line) {
	if(line.contains("Report product")) {
	P_bll.insertProductPDF();	
		}
	}
	
	/**
	 * The method treats the case in which the given command requests a PDF Order Report;
	 * @param line : A variable of String data type which represents the line we are computing;
	 */
	public void processLineReportOrder(String line) {
	if(line.contains("Report order")) {
	O_bll.insertOrderPDF();	
		}		
	}
}
	