package Main;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.TreeMap;



public class WordCount{
	   TreeMap<String, Integer> frequencyData;
	   File givenFile;
	   
	public WordCount(File file){
		this.givenFile = file;
		frequencyData = new TreeMap<String, Integer>();
		process();
	}
	
	private void process() {
	    Scanner in;
		try {
			in = new Scanner(givenFile);
			while (in.hasNext()) {
				 String word = in.next();				      
				 if(frequencyData.containsKey(word)) {
				    // if we have already seen this word before,
				    // increment its count by one
				    Integer count = (Integer)frequencyData.get(word);
				    frequencyData.put(word, new Integer(count.intValue() + 1));
				 } else {
				    // we haven't seen this word, so add it with count of 1
				    frequencyData.put(word, new Integer(1));
				 }
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void setFile(File textFile) {
		givenFile = textFile;
	}

	public String read(){
		String str = "";
		FileReader fr = null;
		BufferedReader textReader = null;
		
		try {
			fr = new FileReader(givenFile.getAbsolutePath());
		    textReader = new BufferedReader(fr);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		
	    try {
			int numLines = 0;
			while ((textReader.readLine())!=null)
			{
			  numLines++;
			}
			textReader.close();
			BufferedReader reader = new BufferedReader(fr);
			int i=0;
			while(i<numLines){
				str+= reader.readLine();
				i++;
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return str;
	}

	public String sort() {
		String returnStr = "";
		 returnStr += "---------------------------";
		 returnStr += "    Occurrences    Word";

	     for(String word: frequencyData.keySet())
	     {
	    	 int index = frequencyData.get(word);
	    	 returnStr += "%15d    %s\n" + Integer.toString(index) + ", " + word;
	     }

	     returnStr += "----------------------------";
		return returnStr;
	}

	public int count(String text) {
		if (frequencyData.containsKey(text))
	      {  
		   return frequencyData.get(text); 
	      }
	      else
	      {  
	         return 0;
	      }
	} 
}
		