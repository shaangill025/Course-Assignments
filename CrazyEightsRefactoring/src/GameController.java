import gameEssentials.Card;
import gameEssentials.Deck;
import gameEssentials.HandofCard;
import ioHandling.IOHandler;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import play.players;

/**
 * The Class GameController is the control oriented class for this project.
 * It contains the player and their turn order, discard pile of cards and 
 * remaining deck as draw pile. The methods included are execGameLoop(), 
 * sortTurns(), etc.
 * 
 * @invariant turns.size()=>2.
 */
public class GameController{
	
	/** The Constant NUM_OF_DIST_CARDS. gives the initial 
	 * number cards distributed equally to each player at start*/
	final static int NUM_OF_DIST_CARDS = 7;
	
	/** Queue Data structure containing the cards played by players*/
	private LinkedQueue<Card> discardPile;
	
	/** The draw pile is the deck, or the cards left in the 
	 * deck after distribution. */
	private Deck drawPile;
	
	/** The ArrayList containing players objects and the order of turns*/
	private List<players> turns;
	
	/** I/O Handler object*/
	private IOHandler iohandler;
	
	/**
	 * Instantiates a new game controller.
	 *
	 * @param players - ArrayList collection of players
	 */
	public GameController(List<players> players){
		this.turns = players;
		if(turns.size()<2) throw new RuntimeException("Atleast 2 players required!");
		
		this.drawPile = new Deck(false);
		this.discardPile = new LinkedQueue<>();
		this.iohandler = new IOHandler();
		init();
	}
 	
	/**
	 * Inits the GameController class
	 * dealing with distribution of cards to players and 
	 * calling the execGameLoop().
	 * 
	 * Precondition - none
	 * 
	 * Postcondition - populates the hand of cards of players in the list,
	 * 					updates the remaining deck, then executes the game loop
	 */
	private void init() {
		drawPile.shuffle();
		for(players temp:turns){
			HandofCard hand = new HandofCard();
			int i=0;
			while(i<NUM_OF_DIST_CARDS){
				hand.addCard(drawPile.dealCard());
				i++;
			}
			temp.setHand(hand);
		}
		execGameLoop();
	}

	/**
	 * Exec game loop, is called from either init() or sortTurns().
	 * Cohesion -  procedural  and Coupling - low [changes could 
	 * 										be made without affecting other methods implementation]
	 * 
	 * Precondition - called from within init() or sortTurns()
	 * Postcondition - player plays in accordance to the appropriate 
	 * 				methodology which are added to the discard pile, or calls doDraw()
	 * 				to draw card from appropriate pile to play the required 
	 * 				suit, value or wildcard.
	 */
	public void execGameLoop(){
		//counter - used mainly for first turn
		int turnNum=0;
		
		Card[] cards;
		Card lastPlayCard = new Card();
		Iterator<Card> itr;


		for(players temp:turns){
			/*	For Testing
			 * System.out.print("\n\n" + "Player: " + temp.getName() + "\n"+ 
			 * temp.getHand().toString() + "\n\n");
			 */				
			if(turnNum==0){
				iohandler.disPlay("\n" + "------------Start of new round-------");
				cards = temp.playTurn();
			}
			else{
				itr = discardPile.getIterator();
				while(itr.hasNext()){
					lastPlayCard =itr.next();
				}
				cards = temp.playTurn(lastPlayCard);
			}
			
			Card lastCard = temp.getLastPlayedCard();
			
			if(doCardDraw(lastCard,temp)){
				cards = temp.playTurn(lastPlayCard);
			}
			
			iohandler.disPlay("\n\nPlayed cards by " + temp.getName() + " are: ");
			String strPlayedCards = addCardtoPile(cards);
			iohandler.disPlay(strPlayedCards);
			
			if(isWinner(temp)){
				iohandler.disPlay("\n\n" + temp.getName() + " has won the game with no cards left!");
				return;
			}
			turnNum++;
		}
		this.sortTurns();
	}
	
	/**
	 * addCardtoPile, add the played cards to the pile and display them on console
	 * Cohesion - functional and Coupling - low [changes could be made without affecting 
	 * 								 other methods implementation]
	 * @return the string indicating the played card
	 * 
	 * Precondition - is called within the execGameLoop
	 * Postcondition - the played cards are added to discardPile queue and string of those
	 * 					cards returned
	 */
	private String addCardtoPile(Card[] cards) {
		String str = "";
		for(Card plays:cards){
			discardPile.enqueue(plays);
			str += plays.toString();
		}
		return str;
	}
	
	/**
	 * is winner, return the boolean indicating if players has run out of cards
	 * Cohesion - functional and Coupling - low [changes could be made without affecting 
	 * 								 other methods implementation]
	 * @return true if player is winner or else
	 * 
	 * Precondition - is called within the execGameLoop at end of player list iteration loop
	 * 					to check if player has won or not
	 * Postcondition - returns suitable boolean variable indicative of the number of cards left
	 * 				 	 in hand. 
	 * 				
	 */
	private boolean isWinner(players plr) {
		HandofCard currHand = plr.getHand();
		if(currHand.getCardCount()==0){
			return true;
		}
		return false;
	}

	/**
	 * Do card draw.
	 * Cohesion - functional and Coupling - low [changes could be made without affecting 
	 * 								 other methods implementation]
	 *	
	 * @param lastCard - the last played card by the player
	 * @param player - the player under consideration
	 * @return true - if the player does not required suit, 
	 * 				  value or wildcard in hand
	 * 
	 * Precondition - is called from within the execGameLoop when a player does not have the 
	 * 					required card in hand to play turn. The game methodology is set up so that
	 * 					if player does not have the required card then playTurn() method returns a 
	 * 					Joker which represents the flag.
	 * Postcondition - a card from the deck [drawpile] or the discard pile is added to the 
	 * 					hand of the player
	 */
	private boolean doCardDraw(Card lastCard, players player){
		while(lastCard.isJoker()){
			iohandler.disPlay("\n\nCard Draw by " + player.getName());
			Card drawn;
			if(drawPile.cardsLeft()!=0){	 
				drawn = drawPile.dealCard();
				player.drawCard(drawn);
				iohandler.disPlay("\nCard drawn is : " + drawn.toString());
				return true;	
			}
			else{
				drawn = discardPile.dequeue();
				player.drawCard(drawn);
				iohandler.disPlay("\nCard drawn is : " + drawn.toString());
				return true;  
			}		
		}
		return false;
	}
	
	/**
	 * Sort turns, Sorts the turns [ArrayList] of players based 
	 * on value of the last card played by them.
	 * Cohesion - functional and Coupling - low [changes could be made without affecting 
	 * 								 other methods implementation]
	 * 
	 * Precondition - is called within the execGameLoop when each player has had it's turn
	 * Postcondition - players list is sorted accordingly and then calls execGameLoop() so 
	 * 					that new gameLoop is run.
	 */
	private void sortTurns() {
		Collections.sort(turns, new PlayerComparator());
		execGameLoop();
	}
}

class PlayerComparator implements Comparator<players>{
	/**
	 * Compare two players
	 *	cohesion - functional
	 * @param arg0 - player first
	 * @param arg1 - player second or other player
	 * 
	 * @return 1 - if first player last played card value is less than
	 * 				second player last played card value
	 * @return -1 - if first player last played card value is more than
	 * 				second player last played card value
	 */
	@Override
	public int compare(players arg0, players arg1) {
		Card first = arg0.getLastPlayedCard();
		Card second = arg1.getLastPlayedCard();
		int i = 0;
		if(first.getValue()<second.getValue()) 
			i=1;
		else if(first.getValue()>second.getValue()) 
			i=-1;
		return i;
	}
}