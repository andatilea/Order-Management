	package businessLayer;
	import java.io.FileNotFoundException;
	import java.io.FileOutputStream;
	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.ResultSet;
	import java.sql.Statement;
	import com.itextpdf.text.Document;
	import com.itextpdf.text.DocumentException;
	import com.itextpdf.text.FontFactory;
	import com.itextpdf.text.Paragraph;
	import com.itextpdf.text.pdf.PdfPTable;
	import com.itextpdf.text.pdf.PdfWriter;

import dataAccess.OrderDAO;
	
	/**
	 * This Class will contain the Response on the operations on Orders.
	 * As a result, the User can see which Orders have been processed and accepted (bills generated), which Orders have been rejected (understock/clientNotRegistered);
	 * Also, this Class helps the implementation of the two 'Extra' tables for accumulating more information about the Warehouse: the Delivery option and the Understock Table.
	 */
	public class OrderBLL {
		
	int billNumber = 1;
	private OrderDAO od = new OrderDAO();
	/**
	 * By using the PDF generator implemented here, the Order Report will be produced in the Project Folder;	
	 */
	public void insertOrderPDF() {
	try {
	Document doc = new Document();
	PdfWriter.getInstance(doc,  new FileOutputStream("RaportOrder" + billNumber + ".pdf"));
	billNumber++;
	doc.open();
	doc.add(new Paragraph("Raport Order:",FontFactory.getFont(FontFactory.TIMES_ROMAN,18)));
	PdfPTable table = new PdfPTable(3);
	table.addCell("Name Client");
	table.addCell("Name Product");
	table.addCell("Quantity:");
	Class.forName("com.mysql.cj.jdbc.Driver");
	Connection con = DriverManager.getConnection("jdbc:mysql://localhost/javadbconnection?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "nEDAvy6aMAMI007!");
	Statement st=con.createStatement();
	ResultSet rs=st.executeQuery("Select * from javadbconnection.order");
	while(rs.next()){
	table.addCell(rs.getString("clientName"));
	table.addCell(rs.getString("productName"));
	table.addCell(rs.getString("quantity"));
	}
	doc.add(table);
	System.out.println("Report Saved!");
	doc.close();
	}catch(Exception E){
	E.printStackTrace();
		}
	}
	
	/**
	 * This method represents the Output generated in the case in which the placed Order is accepted;
	 * @param client : representing the name of the Client which placed the Order;
	 * @param product : representing the product wanted;
	 * @param amount : representing the wanted number of products;
	 * @param price : the Client has to pay this amount of money;
	 */
	public void createBill(String client, String product, int amount, double price) {
	try {
	Document document = new Document();
	PdfWriter.getInstance(document, new FileOutputStream("bill" + billNumber + ".pdf"));
	document.open();
	document.add(new Paragraph("Order processed!\nYou have the summary below: "));
	billNumber++;
	document.add(new Paragraph("Hello, " + client + " you are ordering the product: " + product + "in the Quantity of: " + amount + "\nTotal amount to pay will be: " + price + "\nWe thank you!"));
	document.close();
	}catch(DocumentException e1) {
	e1.printStackTrace();
	}catch(FileNotFoundException e1) {
	e1.printStackTrace();
		}
	}
	
	/**
	 * This method represents the Output generated in the case in which the Product stock is not enough;
	 * @param conn : the Connection with the SQL database;
	 * @param client : the name of the Client which should be informed;
	 * @param amount : the wanted quantity; 
	 * @param product : the Product which is understock;
	 * @param stock : the current available quantity;
	 */
	public void understockCase(Connection conn, String client, int amount, String product, int stock) {
	od.understockCase(conn, client, amount, product, stock);
	try {
	Document document = new Document();
	PdfWriter.getInstance(document, new FileOutputStream("understock" + billNumber + ".pdf"));
	document.open();
	document.add(new Paragraph("Order NOT processed!\n"));
	billNumber++;
	document.add(new Paragraph(" We are sorry, the product is: UNDERSTOCK. " + "You, " + client + ", ordered an amount of: " +  amount + " of the product: " + product+ " . Sadly, the left quantity is only: " + stock));
	document.close();
	}catch(DocumentException e1) {
	e1.printStackTrace();
	}catch(FileNotFoundException e1) {
	e1.printStackTrace();
			}
		}
	}
