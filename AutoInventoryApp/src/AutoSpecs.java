import java.util.HashMap;
import java.util.Map;

public class AutoSpecs {
	Map<String,String> properties;
	
	public AutoSpecs(){
		properties = new HashMap<String,String>();
	}
	
	public AutoSpecs(Map<String,String> input){
		properties = input;
	}
	
	public void add_Change_Property(String key, String value){
		properties.put(key, value);
	}

	public Map<String,String> getMap(){
		return properties;
	}
	
	boolean matches(AutoSpecs spec){
		return false;};
	
	public String returnDescription(){
		String str= "";
		return str;
	}
}


