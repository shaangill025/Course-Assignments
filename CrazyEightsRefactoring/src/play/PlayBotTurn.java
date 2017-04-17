package play;

import gameEssentials.Card;
import gameEssentials.HandofCard;

import java.util.ArrayList;

/**
 * The Class PlayBotTurn implements playingMethods interface.
 * It includes methods which define how and which cards will be 
 * played depending on the turn [first or against a card] and the 
 * cards in hand. 
 * 
 * @invariant !(mostSuit<0||mostSuit>4)
 * @invariant hand != null
 * @invariant lastCard != null 
 */
public class PlayBotTurn implements playingMethods{
	
	/** The Constant WILDCARD which represents a card with value 8. */
	private static final int WILDCARD = 8;
	
	/** The last card played by the player. */
	private Card lastCard;
	
	/** The hand of cards of the player. */
	private HandofCard hand;
	
	/** The most suit that a player has in the hand of cards. */
	private int mostSuit;

	/**
	 * Instantiates a new play bot turn.
	 *
	 * @param hand HandofCard for the player
	 */
	public PlayBotTurn(HandofCard hand) {
		this.hand = hand;
		this.lastCard = new Card();
	}

	/* 
	 * playAtFirstTurn() details the process by which cards are played
	 * by AI or Computer player when their's is the first turn or they
	 * have no card to play against.
	 * @return Card[] containing played card or cards
	 * 
	 * Cohesion - procedural and Coupling - low through encapsulation
	 * 
	 * Precondition - none
	 * Postcondition - return the array of cards played in accordance to 
	 * 					the play methodology 
	 */
	@Override
	public Card[] playAtFirstTurn(){
		/* 
		 * cardsArray is an ArrayList<Card> that includes all
		 * the Card played in this one turn.
		 */
		ArrayList<Card> cardsArray = new ArrayList<>();	
		
		/* Array of Card that includes all the cards 
		 * in the hand of card of the player
		 */
		Card[] handArray = hand.getHandofCards(); 
		
		Card selectedCard = new Card();
		mostSuit = hand.getMostSuit();
		
		//A boolean flag used to avoid going in unnecessary loops if
		//suitable card has already been selected.
		Boolean carry = false;
	
		/* 
		 * Turn methodology is interchangeable, this 
		 * implementation gives preference to 
		 * having a WILDCARD in the hand of cards
	    */ 

		for(Card temp:handArray){
			if(temp.getValue()==WILDCARD){
				carry = true;
				cardsArray.add(temp);
				hand.removeCard(temp);
				
				ArrayList<Card> tempArray = playOnEight();
				for(Card selCards: tempArray){
					cardsArray.add(selCards);
					hand.removeCard(selCards);
				}			
			}
		}
		
		if(!carry){
			for(Card temp: handArray){
				if(temp.getSuit()==mostSuit){
					selectedCard=temp;
					carry = true;
				}
				else if(temp.getValue()<selectedCard.getValue()&&
						temp.getValue()!=WILDCARD&&carry){
					selectedCard=temp;
				}
			}
			cardsArray.add(selectedCard);
			hand.removeCard(selectedCard);
		}
		
		/*
		 * The cardsArray had to converted into Card[] returnCards
		 * at the end of the method to avoid the following exception
		 * Concurrent.Modification Exception
		 */
		Card[] returnCards = new Card[cardsArray.size()];
		int i=0;
		while(i<cardsArray.size()){
			returnCards[i] = cardsArray.get(i);
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
	 * by AI or Computer player against a previously played card.
	 * @param the card played by the previous player.
	 * @return Card[] containing played card or cards
	 * 
	 * Cohesion - procedural and Coupling - low through encapsulation
	 * 
	 * Precondition - played != null
	 * Postcondition - return the array of cards played in accordance to 
	 * 					the play methodology 
	 */
	@Override
	public Card[] playOnACard(Card played){
		/* 
		 * cardsArray is an ArrayList<Card> that includes all
		 * the Card played in this one turn.
		 */
		ArrayList<Card> cardsArray = new ArrayList<>();	
		Card[] handArray = hand.getHandofCards(); 
		
		//A boolean flag used to avoid going in unnecessary loops if
		//suitable card has already been selected.
		Boolean carry = false;
		Card selectedCard = new Card();
		
		/* Turn methodology is interchangeable, this 
		 * implementation gives preference to 
		 * having a same value or same suit in the 
		 * hand of cards*/ 
		
		for(Card temp: handArray){
			if(temp.getValue()==played.getValue()){
				selectedCard = temp;
				carry=true;
				break;
			}	
			else if(temp.getSuit()==played.getSuit()&&temp.getValue()!=WILDCARD){
				selectedCard = temp;
				carry = true;
			}
		}
		
		if(carry){
			cardsArray.add(selectedCard);
			hand.removeCard(selectedCard);
		}
		else if(!carry){
			ArrayList<Card> tempArray = new ArrayList<>();
			for(Card temp: handArray){
				if(temp.getValue()==WILDCARD){
					cardsArray.add(temp);
					hand.removeCard(temp);		
					tempArray = playOnEight();
					break;
				}
			}
			for(Card selCards: tempArray){
				cardsArray.add(selCards);
				hand.removeCard(selCards);
			}	
		}	
		else 
			cardsArray.add(new Card());	
		
		/*
		 * The cardsArray had to converted into Card[] returnCards
		 * at the end of the method to avoid the following exception
		 * Concurrent.Modification Exception
		 */
		Card[] returnCards = new Card[cardsArray.size()];
		int i=0;
		while(i<cardsArray.size()){
			returnCards[i] = cardsArray.get(i);
			i++;
		}
		
		/*
		 * Assigns the last played card to lastCard element or variable.
		 */
		lastCard = returnCards[returnCards.length-1];
		return returnCards;
	}
	
	
	/**
	 * Play on eight or after a Wildcard selection.
	 *
	 * Cohesion - functional and Coupling - low through encapsulation
	 * 
	 * @return the array list'
	 * 
	 * Precondition - this method is called after a Wildcard selection
	 * Postcondition - returns ArrayList of cards that are obtained after
	 * 				playing a wildcard of value 8.
	 */
	@Override
	public ArrayList<Card> playOnEight(){
		/* 
		 * cardsArray is an ArrayList<Card> that includes all
		 * the Card played in this one turn.
		 */
		ArrayList<Card> returnArray = new ArrayList<>();
		Card cardPicked = new Card();
		/* Array of Card that includes all the cards 
		 * in the hand of card of the player
		 */
		Card[] handArray = hand.getHandofCards(); 

		do{
			mostSuit = hand.getMostSuit();	
			for(Card temp: handArray){
			    if(temp.getSuit()== mostSuit&&temp.getValue()!=8){
			    	cardPicked = temp;
			    }
			    else if(temp.getValue()>cardPicked.getValue()&&
							temp.getSuit()==cardPicked.getSuit()){
						cardPicked=temp;
				}		
			}	
			returnArray.add(cardPicked);
	    }while(cardPicked.getValue()==WILDCARD);	
		
		return returnArray;
	}
	
	/* 
	 * @return the last played card.
	 */
	@Override
	public Card getLastCard() {
		return lastCard;
	}
}
