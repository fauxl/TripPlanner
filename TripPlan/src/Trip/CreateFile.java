package Trip;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;  // Import the IOException class to handle errors
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import javax.servlet.http.HttpServlet;

public class CreateFile extends HttpServlet{
	private static final long serialVersionUID = 1L;

	

	public CreateFile () {
		super();
	}

	
  public  static void Create() {
    try {
      File myObj = new File("C:\\Users\\FauxL\\Documents\\Workspace\\TripPlan\\WebContent\\triparray.txt");
      if (myObj.createNewFile()) {
        System.out.println("File created: " + myObj.getName());
      } else {
        System.out.println("File already exists.");
      }
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }
  
	
  public  static void Write(String number) {
	  try {
	      String log = "C:\\Users\\FauxL\\Documents\\Workspace\\TripPlan\\WebContent\\triparray.txt";
	      PrintWriter out = new PrintWriter(new FileWriter(log, true));
	      String newLine = System.getProperty("line.separator");
	      if(number!=null) {
	      out.write(number+newLine);}
	      out.close();
	      System.out.println("Successfully wrote to the file.");
	    } catch (IOException e) {
	      System.out.println("An error occurred.");
	      e.printStackTrace();
	    }
	  }
	
  public  static boolean Check(String number) throws FileNotFoundException {
	
	  Collection<String> posti = Read();

	  
	  if (posti != null && posti.size() != 0) {
			Iterator<?> it = posti.iterator();
			while (it.hasNext()) {
				String luogo = (String) it.next();

			// read next line
			if(!luogo.equalsIgnoreCase(number)){
				return true;
			}
		} 
	  }
	  return false;
  
  }

  public  static Collection<String> Delete(String number) throws FileNotFoundException {
	
	  Collection<String> posti = Read();
	  Collection<String> newposto = new LinkedList<String>();
	  
      String log = "C:\\Users\\FauxL\\Documents\\Workspace\\TripPlan\\WebContent\\triparray.txt";
	  PrintWriter writer = new PrintWriter(log);
	  
	  if (posti != null && posti.size() != 0) {
			Iterator<?> it = posti.iterator();
			while (it.hasNext()) {
				String luogo = (String) it.next();

			// read next line
				if(number!=null && luogo!=null) {
					System.out.println(number);
					System.out.println(luogo);

			if(!luogo.equalsIgnoreCase(number)){
				  //System.out.println(luogo);

				newposto.add(luogo);

			}
//			  System.out.println(luogo);
				}
		}
			writer.print("");
			writer.close();
			// System.out.println(newposto.size());

			  return newposto;
	  }return posti;

  }

  
  
  
  public  static Collection<String> Read() {
	  BufferedReader reader;
	  Collection<String> posto = new LinkedList<String>();
		
	  try {
			reader = new BufferedReader(new FileReader(
					"C:\\Users\\FauxL\\Documents\\Workspace\\TripPlan\\WebContent\\triparray.txt"));
			String  line = "";
			while (line != null) {
				 line = reader.readLine();
				posto.add(line);
			} 

			reader.close();
			return posto;	
		} catch (IOException e) {
			e.printStackTrace();
			return null;

		}
	  }			

	
  
  
}