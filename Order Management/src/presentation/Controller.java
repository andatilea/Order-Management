package presentation;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import businessLayer.Management;

	/**
	 * Controller.java - a class created in order to process the inputs;
	 * The input will be given by a text file which contains the commands and it is called "commands.txt";
	 * All the Data will be computed by the business Layer;
	 */
	public class Controller { 
	
	Management m = new Management();
	/**
	 * The constructor will use the loadTextFile function for extracting the commands;
	 * @param fileName : A variable of type String;
	 */
	public Controller(String fileName) {
	loadTextFile(fileName);
	}
	
	/**
	 * All the data from the input file, will be loaded and will be processed by Line in the bussinessLayer;
	 * @param fileName : A variable of type String;
	 */
	public void loadTextFile(String fileName) {
	BufferedReader reader;
	try {
	String path = new File("").getAbsolutePath() + "\\";
	FileReader fr = new FileReader(path.concat(fileName));
	reader = new BufferedReader(fr);
	String line = reader.readLine();
	while(line!= null) {
	m.processLineClient(line);
	m.processLineProduct(line);
	m.processLineOrder(line);
	m.processLineReportClient(line);
	m.processLineReportProduct(line);
	m.processLineReportOrder(line);
	line = reader.readLine();
	}
	}catch(IOException e) {
	e.printStackTrace();
			}
		}			
	}