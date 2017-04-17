import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

public class CustomerRecord {
	private HashMap<String,String> cust;

	public CustomerRecord(){
		Init();
	}

	private void Init() {
		cust = new HashMap<String,String>();
	}

	public void AddRecord(String FirstName,String LastName,String ContactNumber,String comments){
		Set<String> collection = cust.keySet();
		if(!collection.contains(FirstName+" "+LastName+", "+ContactNumber)){
			cust.put((FirstName+" "+LastName+", "+ContactNumber).toLowerCase(), comments);
		}
	}

	public Iterator<Entry<String, String>> getIterator() {
		return cust.entrySet().iterator();
	}
}
