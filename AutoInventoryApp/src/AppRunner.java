import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class AppRunner {
	public static void main(String[] Args){
		
	Inventory inv = new Inventory();
	Auto car1 = new Auto("1","Sedan");
	Auto car2 = new Auto("2","Sedan");
	Auto car3 = new Auto("3","SUV");
	Auto car4 = new Auto("4","Truck");
	AutoSpecs spec = new AutoSpecs();
	//Map<String,String> customSpec = new HashMap<String,String>();
	
	car1.setProperties("maker","Toyota");
	car1.setProperties("model","Celica");
	car1.setProperties("year","2009");
	car1.setProperties("color","Silver");
	car1.setProperties("drive","2WD");
	car1.setProperties("mpg","23");
	car1.setProperties("num of doors","2");
	
	car2.setProperties("maker","Mitsubishi");
	car2.setProperties("model","Lancer ES");
	car2.setProperties("year","2010");
	car2.setProperties("color","White");
	car2.setProperties("drive","2WD");
	car2.setProperties("mpg","26.5");
	car2.setProperties("num of doors","4");
	
	car4.setProperties("maker","Ford");
	car4.setProperties("model","F150");
	car4.setProperties("year","2014");
	car4.setProperties("color","Black");
	car4.setProperties("drive","4WD");
	car4.setProperties("mpg","18");
	car4.setProperties("load capacity","2");
	
	car3.setProperties("maker","Honda");
	car3.setProperties("model","CRV");
	car3.setProperties("year","2015");
	car3.setProperties("color","White");
	car3.setProperties("drive","4WD");
	car3.setProperties("mpg","22");
	car3.setProperties("num of passengers","5");
	
	inv.addVehicle(car1);
	inv.addVehicle(car2);
	inv.addVehicle(car3);
	inv.addVehicle(car4);
	
	spec.add_Change_Property("color", "White");
	System.out.print(inv.findByID("1").returnStrAutoDescription());
	List<Auto> required = inv.search(spec);
	System.out.print("\n\n" + required.toString());

	inv.addNewVehicle();
	System.out.print(inv.inv.toString());

	}
}
