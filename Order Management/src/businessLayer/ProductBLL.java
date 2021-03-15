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
	 * This Class will contain the Response on all the operations done on Products.
	 * As a result, the development can be seen: some Products are inserted, some of them are deleted and the stock of the ordered Products it's always updated;
	 */
	public class ProductBLL {
	int billNumber = 1;
		
	/**
	 * By using the PDF generator implemented here, all the Product Reports will be produced in the Project Folder;	
	 */
	public void insertProductPDF() {
	try {
	Document doc = new Document();
	PdfWriter.getInstance(doc,  new FileOutputStream("RaportProduct" + billNumber + ".pdf"));
	billNumber++;
	doc.open();
	doc.add(new Paragraph("Raport Product:",FontFactory.getFont(FontFactory.TIMES_ROMAN,18)));
	PdfPTable table = new PdfPTable(3);
	table.addCell("Name");
	table.addCell("Quantity");
	table.addCell("Price");
	Class.forName("com.mysql.cj.jdbc.Driver");
	Connection con = DriverManager.getConnection("jdbc:mysql://localhost/javadbconnection?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "nEDAvy6aMAMI007!");
	Statement st=con.createStatement();
	ResultSet rs=st.executeQuery("Select * from product");
	while(rs.next()){
	table.addCell(rs.getString("name"));
	table.addCell(rs.getString("quantity"));
	table.addCell(rs.getString("price"));
	}
	doc.add(table);
	doc.close();
	}catch(Exception E){
	E.printStackTrace();
			}
		}
	}
