package showroomApp;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

public class CarInventoryOps implements InventorySearch{
	Scanner keyboard;
	String Input;
	CarInventory carDb;
	int number;
	
	public CarInventoryOps(){
		init();
	}

	@Override
	public void init() {
		 carDb = new CarInventory();
	}
	
	@Override
	public Scanner getInput(){
		Scanner keyboard = new Scanner(System.in);
		return keyboard;
	}

	@Override
	public String keyIn(){
		Input = keyboard.nextLine();
		return Input;
	}
	
	@Override
	public int keyIntegerIn(){
		number = keyboard.nextInt();
		return number;
	}

	@Override
	public void Go(){
		System.out.println("Enter (a) for adding record \nEnter (r) for retrieving record \nEnter (s) to Show all records \nEnter (q) to exit ");
		System.out.print("\nType : ");
		String InitResp = keyIn();

		if(InitResp.toLowerCase().equalsIgnoreCase("a")){
			Add();
			Return();

		}
		else if(InitResp.toLowerCase().equalsIgnoreCase("r")){
			System.out.println("Please Enter Search Term (please note format - Make/Model/Make Model) :" );
			String resp = keyIn();
			System.out.println(Search(resp));
			appDelay();
			Return();
			
		}

		else if(InitResp.toLowerCase().equalsIgnoreCase("s")){
				System.out.println(ShowAll());
				Selection();			
			}

		else if(InitResp.toLowerCase().equalsIgnoreCase("q")){
			appExit();
		}

		else inputError();
	}

	private void Selection() {
		int number;
		System.out.println("\nPlease Enter the Corresponding Index to view that model");
		number = keyIntegerIn();
		System.out.println(displayResult(number));
	}

	public String displayResult(String number){		
		HashMap<String, String> result = carDb.SearchbyIndex(number);
		String toReturn = " ";
		Iterator<Map.Entry<String, String>> it = result.entrySet().iterator();
		while (it.hasNext()) {
		    Map.Entry<String, String> entry = it.next();
		    toReturn += entry.getKey() + "  " + entry.getValue() + "\n";
		}
		return toReturn;
	}
	
	private String ShowAll() {
		System.out.println("Showing All Records --> \n\n");

		HashMap<Integer, String> result = carDb.ShowallModel();
		String toReturn = "";
		Iterator<Map.Entry<Integer, String>> it = result.entrySet().iterator();
		while (it.hasNext()) {
		    Map.Entry<Integer, String> entry = it.next();
		    toReturn += entry.getKey() + "  " + entry.getValue() + "\n";
		}
		return toReturn;
	}

	private void Add() {
		System.out.println("Enter Make --> ");
		String make = keyIn();
		System.out.println("Enter Model --> ");
		String model = keyIn();
		String key = make + " " + model;
		System.out.println("Enter Description --> ");
		String descrip = keyIn();
		carDb.AddRecord(key, descrip);

	}
	@Override
	public String Search(String input) {
		HashMap<String, String> result = carDb.Searchable(input.trim());
		String toReturn = "";
		Iterator<Map.Entry<String, String>> it = result.entrySet().iterator();
		while (it.hasNext()) {
		    Map.Entry<String, String> entry = it.next();
		    toReturn += entry.getKey() + "  " + entry.getValue() + "\n";
		}

		return toReturn;

	}

	private void appDelay(){
		long now = System.currentTimeMillis();
		long then = now + 4000; // one second of milliseconds
	    while (System.currentTimeMillis() < then)
		{
		   //do nothing just wait
		} 
	}
	
	private void appExit() {
		System.out.print("Exiting Application!");
		System.exit(0);
	}

	public void Return(){
		Go();
	}
	
	@Override
	public void inputError() {
		System.out.println("input error! please try again\n");
		Go();		
	}



}
}
