import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;


public class CarRecords {
	private HashMap<String,String> record;

	public CarRecords(){
		Init();
	}

	private void Init() {
		record = new HashMap<String,String>();
	}

	public void AddRecord(String make,String model,String descrip){
		Set<String> collection = record.keySet();
		if(!collection.contains(make+"-"+model)){
			record.put((make+"-"+model).toLowerCase(), descrip);
		}
	}

	public void RemoveRecord(String make,String model){
		Set<String> collection = record.keySet();
		if(collection.contains(make+"-"+model)){
			record.remove((make+"-"+model).toLowerCase());
		}
	}
	
	public ArrayList<String> ShowallModel() {
		ArrayList<String> allModel = new ArrayList<String>();
		for(Map.Entry<String,String> entry : record.entrySet()){
			allModel.add(entry.getKey());
		}
		return allModel;
	}

	public String SearchbyIndex(String search) {
		String toReturn = "";
		for(Entry<String, String> entry : record.entrySet()){
		if(entry.getKey().equalsIgnoreCase(search)){
		    toReturn = "For "+ entry.getKey() + ", the Description is as follow: \n " + entry.getValue();
			}
		}
		return toReturn;
	}

	public Iterator<Entry<String, String>> getIterator() {
 		return record.entrySet().iterator();
	}
	
	public int size(){
		return record.size();
	}
}
