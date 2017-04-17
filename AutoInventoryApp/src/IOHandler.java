import java.util.Scanner;


class IOHandler implements IOHandInterface{
	
	Scanner  sc = new Scanner(System.in);
	
	@Override
	public void displaySpecInsertion() {
		System.out.println("\nWould you like to add another property? (type Y/N): ");
	}
	
	@Override
	public void displayKeyRequest() {
		System.out.println("\nType in the key for the new property: ");
	}
	
	@Override
	public void displayValueRequest() {
		System.out.println("Type in the key for the new property: ");
	}
	
	@Override
	public void displayKeyUpdateRequest() {
		System.out.println("\nType in the key for the property to update: ");
	}
	
	@Override
	public void displayValueUpdateRequest() {
		System.out.println("Type in the value for the property to update: ");
	}
	
	@Override
	public void disPlay(String message) {
		System.out.println(message);		
	}

	@Override
	public Scanner recieve() {
		return sc;		
	}
}
