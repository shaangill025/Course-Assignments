import java.util.Scanner;

class IOHandler implements IIOHandler{
	Scanner  sc = new Scanner(System.in);
	
	@Override
	public void displayForEights() {
	    System.out.println("You picked up a card of value 8.");
	    System.out.println("You can now select a card of your choosing to put into the discard pile");
	    System.out.println("Your hand of card is displayed below :");
	    System.out.println();		
	}
	
	@Override
	public void displayForUser() {
	    System.out.println("Pick one card by typing the index [0-6+] of the card.");
	    System.out.println("Your hand of card is displayed below :");
	    System.out.println();		
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