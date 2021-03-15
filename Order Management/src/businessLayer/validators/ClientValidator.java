	package businessLayer.validators;
	import java.io.FileNotFoundException;
	import java.io.FileOutputStream;
	import java.sql.Connection;
	import com.itextpdf.text.Document;
	import com.itextpdf.text.DocumentException;
	import com.itextpdf.text.Paragraph;
	import com.itextpdf.text.pdf.PdfWriter;
	import connection.ConnectionFactory;
	import dataAccess.ClientDAO;
	import model.Client;
	
	/**
	 * This class is used in order to help the Application verify if the Client placing an Order actually exists in the Reports (in the client SQL table);
	 */
	public class ClientValidator {
	private Connection Myconnection = ConnectionFactory.getConnection();
	private ClientDAO cd = new ClientDAO();
	int billNumber = 1;
		
	/**
	 *  By using the PDF generator implemented here, the 'negative' Validation Report will be produced in the Project Folder, it represents the case in which the Client is not registered;	
	 * @param orderingClient : this String represents the name of the Client which needs to be verified;;
	 */
	public void validate(String orderingClient) {
	Client c = cd.findClient(Myconnection, orderingClient);
	if (c.getName() == null) {  
				
	try {
	Document document = new Document();
	PdfWriter.getInstance(document, new FileOutputStream("clientNotRegistered" + billNumber + ".pdf"));
	document.open();
	document.add(new Paragraph("You are not registered in order to buy from us!\n"));
	billNumber++;
	document.add(new Paragraph(" We are so sorry, you can not buy the product. " + "\n Please register, and try again!"));
	document.close();
	}catch(DocumentException e1) {
	e1.printStackTrace();
	}catch(FileNotFoundException e1) {
	e1.printStackTrace();
				}
			}
		}
	}
