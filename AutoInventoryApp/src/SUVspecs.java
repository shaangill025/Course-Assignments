import java.util.HashMap;
import java.util.Map;


public class SUVspecs extends AutoSpecs{
	Map<String,String> properties;
	String[] rangeProperties;

	public SUVspecs(){
		properties = new HashMap<String,String>();
		rangeProperties = new String[]{"Product Price","mpg"};
	}
	
	public void setRangeProperties(String[] rangeProperties) {
		this.rangeProperties = rangeProperties;
	}
	
	public void add_Change_Property(String key, String value){
		properties.put(key, value);
	}
	
	@Override
	public boolean matches(AutoSpecs spec) {
		Map<String,String> customAttr = spec.getMap();
		boolean match=false;
	
		
		for(String name: customAttr.keySet()){
		    String desc = customAttr.get(name);
		    boolean rangePresent = rangePropertycheck(name);		    
		    
		    if(properties.get(name).equalsIgnoreCase(desc)){
		    	match = true;
		    }
		    else if(rangePresent){
		    	String[] strRange = desc.split("-");
		    	int startRange = Integer.parseInt(strRange[0]);
		    	int endRange = Integer.parseInt(strRange[1]);
		    	String property = properties.get(name);
		    	int value = Integer.parseInt(property);
		    	if(endRange>startRange&&
		    			startRange<value&&value<endRange){
		    		match = true;
		    	}
		    }
		    else return false;
		}
		return match;
	}
	
	private boolean rangePropertycheck(String name){
		for(String temp: rangeProperties){
			if(name.equalsIgnoreCase(temp)) return true;
		}
		return false;
	}
	
	public String toString(){
		String str = "";
		for(Map.Entry<String,String> temp: properties.entrySet()){
			str += temp.getKey() + ": " + temp.getValue() + "\n";
		}
		return str;
	}
	
	public String returnDescription(){
		String str = "";		
		str=properties.get("maker") + " " + properties.get("model") + " " + properties.get("year");
		return str; 
	}
}
