import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Inventory {
	List<Auto> inv;
	IOHandler handle;
	Scanner sc;
	
	public Inventory(){
		 inv = new ArrayList<Auto>();
		 handle = new IOHandler();
	}
	
	public void addVehicle(Auto car){
		inv.add(car);
	}
	
	public void addNewVehicle(){
		String type = "", num = "", strItr = "N";
		
		if(!inv.isEmpty()){
			num = Integer.toString(inv.size()+1);
		}
		else num = Integer.toString(1);
			
		sc = handle.recieve();
		handle.disPlay("\nPlease enter the type/category for the vehicle");
		type = sc.next();
		
		Auto newVehicle = new Auto(num,type);
		
		handle.displaySpecInsertion();
		strItr = sc.next();
		
		while(strItr.equalsIgnoreCase("Y")){
			handle.displayKeyRequest();
			String key = sc.next();
			
			handle.displayValueRequest();;
			String value = sc.next();
			
			newVehicle.specifications.add_Change_Property(key, value);
			
			handle.displaySpecInsertion();
			strItr = sc.next();
		}
		inv.add(newVehicle);
	}
	
	public void updateVehicle(String num){
		String strItr = "Y";
		Auto vehicle = findByID(num);
		
		if(inv.remove(vehicle)){
			while(strItr.equalsIgnoreCase("Y")){
				handle.displayKeyUpdateRequest();;
				String key = sc.next();
					
				handle.displayValueUpdateRequest();
				String value = sc.next();
					
				vehicle.specifications.add_Change_Property(key, value);	
				handle.displaySpecInsertion();
				strItr = sc.next();
			}
		}
		inv.add(vehicle);
	}
	
	public Auto findByID(String num){
		for(Auto temp: inv){
			if(temp.getSerialNum().equalsIgnoreCase(num)){
				return temp;
			}
		}
		throw new RuntimeException("No Record Exist with that serial number!");
	}
	
	public List<Auto> search(AutoSpecs spec){
		List<Auto> returnList = new ArrayList<Auto>();
		
		for(Auto temp: inv){
			if(temp.runComparisons(spec)){
				returnList.add(temp);
			}
		}
		
		return returnList;
		
	}
}
