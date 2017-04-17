
public class Auto {
	String serialNum = "###";
	AutoSpecs specifications;
	String type = "";
	
	public Auto(String num, String vehType){
		serialNum = num;
		type = vehType;
		init();
	}
	
	private void init(){
		if(type.equalsIgnoreCase("Sedan")){
			specifications = new SedanSpecs();
			specifications.add_Change_Property("type", "Sedan");
		}
		else if(type.equalsIgnoreCase("Truck")){
			specifications = new TruckSpecs();
			specifications.add_Change_Property("type", "Truck");
		}
		else if(type.equalsIgnoreCase("SUV")){
			specifications = new SUVspecs();
			specifications.add_Change_Property("type", "SUV");
		}
		else if(type.equalsIgnoreCase("Van")){
			specifications = new VanSpecs();
			specifications.add_Change_Property("type", "Van");
		}
		else throw new RuntimeException("\nInvalid vehicle type! Unanble to create Auto object!\n");
	}
	
	public void setProperties(String k,String v){
		specifications.add_Change_Property(k, v);
	}
	
	public boolean runComparisons(AutoSpecs spec){
		return specifications.matches(spec);
	}
	
	public String getSerialNum(){
		return serialNum;
	}
	
	public String returnStrAutoDescription(){
		String str = "";
		str += "Type: "+ type + "\nSerial Num: " + serialNum + "\n" + specifications.toString();
		return str;
	}
	
	public String toString(){
		String str = "";
		str += "Type: "+ type + "| Serial Num: " + serialNum + "| Description: " + specifications.returnDescription();
		return str;
	}
}
