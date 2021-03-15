	package businessLayer;
	import java.io.FileOutputStream;
	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.ResultSet;
	import java.sql.Statement;
	import com.itextpdf.text.Document;
	import com.itextpdf.text.FontFactory;
	import com.itextpdf.text.Paragraph;
	import com.itextpdf.text.pdf.PdfPTable;
	import com.itextpdf.text.pdf.PdfWriter;
	
	/**
	 * This Class will contain the Response on all the operations done on Clients.
	 * As a result, the User is able to see the development: some Clients are inserted, some of them are deleted;
	 */
	public class ClientBLL {
	int billNumber = 1;
	
	/**
	 * By using the PDF generator implemented here, all the Client Reports will be produced in the Project Folder;	
	 */
	public void insertClientPDF() {
	try {
	Document doc = new Document();
	PdfWriter.getInstance(doc,  new FileOutputStream("RaportClient" + billNumber + ".pdf"));
	billNumber++;
	doc.open();
	doc.add(new Paragraph("Raport Clients:",FontFactory.getFont(FontFactory.TIMES_ROMAN,18)));
	PdfPTable table = new PdfPTable(2);
	table.addCell("Name");
	table.addCell("Address");
	Class.forName("com.mysql.cj.jdbc.Driver");
	Connection con = DriverManager.getConnection("jdbc:mysql://localhost/javadbconnection?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "nEDAvy6aMAMI007!");
	Statement st=con.createStatement();
	ResultSet rs=st.executeQuery("Select * from client");
	while(rs.next()){
	table.addCell(rs.getString("name"));
	table.addCell(rs.getString("address"));
	}
	doc.add(table);
	doc.close();
	}catch(Exception E){
	E.printStackTrace();
			}
		}
	}
