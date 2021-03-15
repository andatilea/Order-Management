package start;
import presentation.Controller;

	/**
	 * Main.java - the main class which will launch the Application;
	 */
	public class Main {

	/**
	 * The Controller Class is called, starting the Application by extracting the input information;
	 * @param args : A variable of type String[];
	 * The Application will contain one argument which will represent the name of the commands-input file;
	 */
	public static void main(String[] args){
	for (String s: args) {
    System.out.println("Taking data from file: " + s);
    new Controller(s);	
			}	
		}	
	}
