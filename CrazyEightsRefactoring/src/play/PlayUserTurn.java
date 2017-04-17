 package play;

import gameEssentials.Card;
import gameEssentials.HandofCard;
import ioHandling.IOHandler;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * The Class PlayUserTurn implements playingMethods interface.
 * It includes methods which allows user or human player to select cards will be 
 * played depending on the turn [first or against a card] and the 
 * cards in hand. 
 * 
 * 
 * @invariant !(mostSuit<0||mostSuit>4)
 * @invariant hand != null
 * @invariant lastCard != null 
 */
public class PlayUserTurn implements playingMethods {
	
	/** The Constant WILDCARD which represents a card with value 8. */
	private static final int WILDCARD = 8;
	
	/** The I/O Handler element. */
	private IOHandler iohandler;
	
	/** The last card played by the player. */
	private Card lastCard;
	
	/** The scanner for input handling. */
	private Scanner sc;
	
	/** The hand of cards of the player. */
	private HandofCard hand;

	/**
	 * Instantiates a new play user turn.
	 *
	 * @param hand the hand for the player
	 */
	public PlayUserTurn(HandofCard hand) {
		this.hand = hand;
		this.lastCard = new Card();
		this.iohandler = new IOHandler();
		this.sc = iohandler.recieve();
	}

	/* 
	 * playAtFirstTurn() details the process by which cards are played
	 * by the user when their's is the first turn or they
	 * have no card to play against.
	 * 
	 * Cohesion - procedural and Coupling - low through encapsulation
	 * 
	 * @return Card[] - array of Card played
	 * 
	 * Precondition - none
	 * Postcondition - return the array of card played by the user
	 * 					in accordance to the specified rule.
	 */
	@Override
	public Card[] playAtFirstTurn() {
		/* 
		 * selected is an ArrayList<Card> that includes all
		 * the Card played in this one turn.
		 */
		ArrayList<Card>  selected = new ArrayList<>();
		
		// asks user whether to sort hand of cards by suit or value before display
		if(doValueSort()){
			hand.sortByValue();
		}
		else if(doSuitSort()){
			hand.sortBySuit();
		}
		
		/* 
		 * player's hand is displayed with corresponding index
		 * , it receives player set index [validated to be correct] 
		 * and selected card is added to the ArrayList
		 */
		iohandler.displayForUser();
		iohandler.disPlay(hand.toString());			
		int index = sc.nextInt();
		
		/*
		 * Precondition - selected index should be between 0 and hand.size()-1
		 */
		if(index>=0&&index<hand.getCardCount()){
			Card selectedCard = hand.getCard(index);
			selected.add(selectedCard);
			hand.removeCard(selectedCard);
			
			if(selectedCard.getValue()==WILDCARD){
				ArrayList<Card> tempArray = playOnEight();
				//adding each card of the returned array to selected
				//and removing the same from hand.
				for(Card temp: tempArray){
					selected.add(temp);
					hand.removeCard(temp);
				}
			}
			
		}
		else throw new RuntimeException("Invalid Card Index selected!");
		
		/*
		 * The selected had to converted into Card[] returnCards
		 * at the end of the method to avoid the following exception
		 * Concurrent.Modification Exception
		 */
		Card[] returnCards = new Card[selected.size()];
		int i = 0;
		while(i<selected.size()){
			returnCards[i] = selected.get(i);
			i++;
		}
	
		/*
		 * Assigns the last played card to lastCard element or variable.
		 */
		lastCard = returnCards[returnCards.length-1];
		return returnCards;			
	}
		
	/* 
	 * playOnACard(Card card) details the process by which cards are played
	 * by the player against a previously played card.
	 * @param card played by the previous player
	 * 
	 * Cohesion - procedural and Coupling - low through encapsulation
	 * @return Card[] - array of Card played
	 * 
	 * Precondition - played != null
	 * Postcondition - return the array of card played by the user
	 * 					in accordance to the specified rule
	 */
	@Override
	public Card[] playOnACard(Card played){
		/* 
		 * selected is an ArrayList<Card> that includes all
		 * the Card played in this one turn.
		 */
		ArrayList<Card>  selected = new ArrayList<>();

		if(doValueSort()){
			hand.sortByValue();
		}
		else if(doSuitSort()){
			hand.sortBySuit();
		}
		
		/* 
		 * Played card is displayed as well as player's hand is 
		 * displayed with corresponding index, it receives player 
		 * set index [validated to be correct] and selected card 
		 * is added to the ArrayList
		 */
		iohandler.disPlay("\nPlayed card is the following\n" + played.toString());
		iohandler.displayForUser();
		iohandler.disPlay(hand.toString());
		int index = sc.nextInt();
		Card selectedCard = hand.getCard(index);
		
		/*
		 * 
		 */
		boolean validCardFlag = selectCardValidate(played);

		if(validCardFlag){				
				 selected.add(selectedCard);
				 hand.removeCard(selectedCard);
				 
				 if(selectedCard.getValue()==WILDCARD){
						ArrayList<Card> tempArray = playOnEight();
						//adding each card of the returned array to 
						//selected and removing the same from hand.
						for(Card temp: tempArray){
							selected.add(temp);
							hand.removeCard(temp);
						}
				}
		}
		else selected.add(new Card());
		
		/*
		 * The selected had to converted into Card[] returnCards
		 * at the end of the method to avoid the following exception
		 * Concurrent.Modification Exception
		 */
		Card[] returnCards = new Card[selected.size()];
		int j = 0;
		while(j<selected.size()){
			returnCards[j] = selected.get(j);
			j++;
		}
		
		/*
		 * Assigns the last played card to lastCard element or variable.
		 */
		lastCard = returnCards[returnCards.length-1];
		return returnCards;
	}
	
	/*
	 * Play on eight or after a Wildcard selection.
	 *
	 * Cohesion - functional and Coupling - low through encapsulation
	 * @return the array list'
	 * 
	 * Precondition - this method is called after a Wildcard selection
	 * Postcondition - returns ArrayList of cards that are obtained after
	 * 				playing a wildcard of value 8.
	 */
	@Override
	public ArrayList<Card> playOnEight(){
		Card selectCard = new Card();
		ArrayList<Card> returnArray = new ArrayList<>();
		
		do{
			iohandler.displayForEights();
			iohandler.disPlay(hand.toString());
			int index = sc.nextInt();
			if(index>=0&&index<hand.getCardCount()){
				selectCard = hand.getCard(index);
				returnArray.add(selectCard);
			}
		}while(selectCard.getValue()==WILDCARD);
		
		return returnArray;
	}
	
	/*
	 * Validates if the hand of the player contains a playable card.
	 * Cohesion - functional and Coupling - low through encapsulation
	 * 
	 * @param playCard the played card by the previous player
	 * @return true if cards are available in hand to play.
	 * 
	 * Precondition - playCard != null and the hand != null
	 * Postcondition - returns true if cards are available in hand to play
	 * 				   , returns false if no option exist an 
	 */
	
	private boolean selectCardValidate(Card playCard){
		boolean flag = false;
		Card[] handArray = hand.getHandofCards();	

		for(Card inHand: handArray){
			if(playCard.getSuit()==inHand.getSuit()){		
				flag = true;
			}
			else if(playCard.getValue()==inHand.getValue()){		
				flag = true;
			}
			else if(inHand.getValue()==WILDCARD){		
				flag = true;
			}
		}
		return flag;
	}
	
	/* 
	 * return the lastCard
	 */
	@Override
	public Card getLastCard() {
		return lastCard;
	}
	
	/**
	 * Do value sort.
	 * Cohesion - functional and Coupling - low through encapsulation
	 * Prompts user asking for value sort of hand
	 * @return true, if successful
	 */
	private boolean doValueSort(){
			iohandler.displayForValueSort();
			String value = sc.next();
			if("y".equalsIgnoreCase(value)){
				return true;
			}
			return false;
	}
	
	/**
	 * Do suit sort.
	 * Cohesion - functional and Coupling - low through encapsulation
	 * Prompts user asking for value sort of hand
	 * @return true, if successful
	 */
	private boolean doSuitSort(){
			iohandler.displayForSuitSort();
			String sort = sc.next();
			if("y".equalsIgnoreCase(sort)){
				return true;
			}
			return false;
	}
}
