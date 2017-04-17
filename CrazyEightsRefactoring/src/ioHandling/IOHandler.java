package ioHandling;
import java.util.Scanner;

// TODO: Auto-generated Javadoc
/**
 * The Class IOHandler.
 */
public class IOHandler implements IIOHandler{
	
	/** The sc. */
	Scanner  sc = new Scanner(System.in);
	
	/* (non-Javadoc)
	 * @see ioHandling.IIOHandler#displayForEights()
	 */
	@Override
	public void displayForEights() {
	    System.out.println("You picked up a card of value 8.");
	    System.out.println("You can now select a card of your choosing to put into the discard pile");
	    System.out.println("Your hand of card is displayed below :");
	    System.out.println();		
	}
	
	/* (non-Javadoc)
	 * @see ioHandling.IIOHandler#displayForUser()
	 */
	@Override
	public void displayForUser() {
	    System.out.println("Pick one card by typing the index [0-6+] of the card.");
	    System.out.println("Your hand of card is displayed below :");
	    System.out.println();		
	}
	
	/* (non-Javadoc)
	 * @see ioHandling.IIOHandler#displayForValueSort()
	 */
	@Override
	public void displayForValueSort() {
	    System.out.println("\nType if you want to sort cards by value else type N");
	}
	
	/* (non-Javadoc)
	 * @see ioHandling.IIOHandler#displayForSuitSort()
	 */
	@Override
	public void displayForSuitSort() {
	    System.out.println("Type if you want to sort cards by suit else type N.");
	}
	
	/* (non-Javadoc)
	 * @see ioHandling.IIOHandler#disPlay(java.lang.String)
	 */
	@Override
	public void disPlay(String message) {
		System.out.println(message);		
	}

	/* (non-Javadoc)
	 * @see ioHandling.IIOHandler#recieve()
	 */
	@Override
	public Scanner recieve() {
		return sc;		
	}
}