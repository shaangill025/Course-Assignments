package ioHandling;

import java.util.Scanner;

// TODO: Auto-generated Javadoc
/**
 * The Interface IIOHandler.
 */
public interface IIOHandler{
	
	/**
	 * Display for user.
	 */
	void displayForUser();
	
	/**
	 * Display for eights.
	 */
	void displayForEights();
	
	/**
	 * Dis play.
	 *
	 * @param message the message
	 */
	void disPlay(String message);
	
	/**
	 * Display for value sort.
	 */
	void displayForValueSort();
	
	/**
	 * Display for suit sort.
	 */
	void displayForSuitSort();
	
	/**
	 * Recieve.
	 *
	 * @return the scanner
	 */
	Scanner recieve();
}
