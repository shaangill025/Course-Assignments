import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

public class DataHandler {
		public static void getData(String filename, CarRecords car){
	        String make = null, model = null, descrip = null;
	        try {
	            FileReader fileReader = new FileReader(filename);            
	            BufferedReader bufferedReader = new BufferedReader(fileReader);//Wrap FileReader in BufferedReader.

	            while((make = bufferedReader.readLine()) != null && (model = bufferedReader.readLine()) != null && (descrip = bufferedReader.readLine()) != null) {
	             	car.AddRecord(make,model,descrip);
	            } 
	            bufferedReader.close();
	        }
	        catch(IOException ex) {
	            System.out.println("Error reading file"); 
	            ex.printStackTrace();                  
	        }
		}
		
		public static void getData(String filename, CustomerRecord cust){
	        String fname = null, lname = null, number = null, intent = null;
	        try {
	            FileReader fileReader = new FileReader(filename);            
	            BufferedReader bufferedReader = new BufferedReader(fileReader);//Wrap FileReader in BufferedReader.

	            while((fname = bufferedReader.readLine()) != null && (lname = bufferedReader.readLine()) != null && (number = bufferedReader.readLine()) != null && (intent = bufferedReader.readLine()) != null) {
	             	cust.AddRecord(fname,lname,number,intent);
	            } 
	            bufferedReader.close();
	        }
	        catch(IOException ex) {
	            System.out.println("Error reading file"); 
	            ex.printStackTrace();                  
	        }
		}
		
		public static void saveData(String filename, CarRecords car){
		    FileWriter fileWriter;  
		    BufferedWriter bufferedWriter; 
			try {
				fileWriter = new FileWriter(filename,true);
				bufferedWriter = new BufferedWriter(fileWriter); 
				//Once writing objects are instantiated, the existing content of the file would be wiped out...
	            Iterator<Map.Entry<String, String>> it = car.getIterator();
	            while ( it.hasNext() ) {
	            	Map.Entry<String, String> pair = (Map.Entry<String, String>)it.next();
	            	bufferedWriter.write( pair.getKey() );
	            	bufferedWriter.write(System.getProperty ( "line.separator" ));
	            	bufferedWriter.write( pair.getValue() );
	            	bufferedWriter.write(System.getProperty ( "line.separator" ));
	    		}
	            bufferedWriter.close();
	        }
	        catch(IOException ex) {
	            System.out.println("Error writing to file");
	            ex.printStackTrace();
	        }
		}
		
		public static void saveData(String filename,CustomerRecord cust){
		    FileWriter fileWriter;  
		    BufferedWriter bufferedWriter; 
			try {
				fileWriter = new FileWriter(filename,true);
				bufferedWriter = new BufferedWriter(fileWriter); 
				//Once writing objects are instantiated, the existing content of the file would be wiped out...
	            Iterator<Map.Entry<String, String>> it = cust.getIterator();
	            while ( it.hasNext() ) {
	            	Map.Entry<String, String> pair = (Map.Entry<String, String>)it.next();
	            	bufferedWriter.write( pair.getKey() );
	            	bufferedWriter.write(System.getProperty ( "line.separator" ));
	            	bufferedWriter.write( pair.getValue() );
	            	bufferedWriter.write(System.getProperty ( "line.separator" ));
	    		}
	            bufferedWriter.close();
	        }
	        catch(IOException ex) {
	            System.out.println("Error writing to file");
	            ex.printStackTrace();
	        }
		}

}
