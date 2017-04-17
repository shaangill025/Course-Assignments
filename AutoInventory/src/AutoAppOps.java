import java.util.ArrayList;
import java.util.Scanner;

public class AutoAppOps implements InvSearchApp{
	private Scanner sc;
	String input;
	CarRecords cars;
	CustomerRecord cust;
	ArrayList<String> result;
	
	public AutoAppOps(){
		appInit();
	}
	
	@Override
	public void appInit() {
		sc = new Scanner(System.in);
		cars = new CarRecords();
		cust = new CustomerRecord();
		//result = new ArrayList<String>();
		DataHandler.getData("carRecords.txt", cars);	
		DataHandler.getData("Customers.txt", cust);	

	}
	
	@Override
	public String getInput() {		
		input = sc.nextLine();
		return input;
	}
	
	@Override
	public String ShowAll() {
		System.out.println("\nShowing All Records -->\n");
		result = cars.ShowallModel();
		String toReturn = "";
		int k=0;
		while(k<result.size()){
			toReturn += (k+1) + "  " + result.get(k) + "\n";
		    k++;
		}
		return toReturn;
	}

	@Override
	public void run() {
		System.out.println("Query model info (q) or Enter a vehicle purchase intent (i) or Exit (e):");
		String InitResp = getInput();

		if(InitResp.toLowerCase().equalsIgnoreCase("q")){
			System.out.println(ShowAll());

			System.out.println("Please Enter the corresponding index number for more: ");
			int index = sc.nextInt();
			
			if(!(index>0&&index<cars.size())){
				System.out.println("\nInvalid index selected, please try again!\n");	
				sc = new Scanner(System.in);
				run();
			}
			
			System.out.println("\n"+cars.SearchbyIndex(result.get(index-1))+"\n");
			
			try {
			    Thread.sleep(2000);
			} catch(InterruptedException ex) {
			    Thread.currentThread().interrupt();
			}
			System.out.println("\n");	
			sc = new Scanner(System.in);
			run();
		}
		
		else if(InitResp.toLowerCase().equalsIgnoreCase("i")){
			System.out.println("Please Enter your first name :" );
			String fname = getInput();
			System.out.println("\nPlease Enter your last name :" );
			String lname = getInput();
			System.out.println("\nPlease Enter your Contact Number :" );
			String num = getInput();
			System.out.println("\nPlease Enter your Purchase intent:" );
			String comment = getInput();

			cust.AddRecord(fname, lname, num, comment);
			DataHandler.saveData("Customers.txt", cust);
			
			try {
			    Thread.sleep(1000);
			} catch(InterruptedException ex) {
			    Thread.currentThread().interrupt();
			}
			System.out.println("\n");
			sc = new Scanner(System.in);
		    run();
		}

		else if(InitResp.toLowerCase().equalsIgnoreCase("e")){
				DataHandler.saveData("carRecords.txt", cars);
				System.exit(0);			
			}
		
		else{
			sc = new Scanner(System.in);
			System.out.println("\nInvalid Option Selected, please try again!\n");
			run();
		}

	}

}
