package play;
import gameEssentials.Card;
import gameEssentials.HandofCard;

/**
 * The Class Player.
 * Incorporating human/user player 
 * behavior and methods
 */

/**
 * @invariant hand != null
 * @invariant name != null
 * @invariant turnMethod != null
 */
public class Computer implements players{
	
	/** The hand of cards of the player. */
	private HandofCard hand;
	
	/** The name of the player. */
	private String name;
	
	/** The turn method - playingMethods object. */
	private playingMethods turnMethod;

	/**
	 * Instantiates a new computer.
	 *
	 * @param name the name
	 */
	public Computer(String name){
		init(name);
	}
	
	/**
	 * Instantiates a new player.
	 *
	 * @param name String
	 * @param hand HandofCard of the player
	 */
	public Computer(String name, HandofCard hand){
		this.name = name;
		this.hand = hand;
	}
	/*
	 *  Inits the Computer class
	 *  @param str String - name of the player
	 * 
	 * Precondition - none
	 * Postcondition -  assigns the name and creates hand as a new HandofCard() object
	 */
	private void init(String str){
		this.hand = new HandofCard();
		this.name = str;
	}
	
	/* 
	 * Drawn card added to hand of player
	 * 
	 * @param card draw from either discard
	 * pile or the deck
	 * 
	 * Precondition - none
	 * Postcondition - adds the drawn card to the hand of the player
	 */
	@Override
	public void drawCard(Card card){
		hand.addCard(card);
	}	
	
	/* 
	 * creates a playingMethods object as PlayBotTurn() and then call the appropriate 
	 * play turn method which will be playAtFirstTurn()
	 * 
	 * @return Card[] array
	 * 
	 * Precondition - Player has a valid hand
	 * Postcondition - turnMethod is created as PlayBotTurn(hand) and then call the appropriate 
	 * 				play turn method which will be playAtFirstTurn()
	 */
	@Override
	public Card[] playTurn(){
		this.turnMethod = new PlayBotTurn(hand);
		return turnMethod.playAtFirstTurn();
	}
	
	/* 
	 * creates a PlayUserTurn object and then call appropriate 
	 * play turn method
	 * 
	 * @param the card played by the previous player
	 * @return Card[] array
	 * 
	 * Precondition - Player has a valid hand
	 * Postcondition - turnMethod is created as PlayBotTurn(hand) and then call the appropriate 
	 * 				play turn method which will be playOnACard(played: Card)
	 */
	@Override
	public Card[] playTurn(Card played){
		this.turnMethod = new PlayBotTurn(hand);
		return turnMethod.playOnACard(played);
	}
	
	/* 
	 * sets hand of the player, assigning parameter
	 * @param inputHand HandofCard
	 */
	@Override
	public void setHand(HandofCard inputHand){
		hand = inputHand;
	}
	
	/* 
	 * @return name of the player
	 */
	@Override
	public String getName(){
		return name;
	}
	
	/* 
	 * @return hand HandofCard of the player
	 */
	@Override
	public HandofCard getHand(){
		return hand;
	}
	
	/* 
	 * @return last played card by the player
	 */
	@Override
	public Card getLastPlayedCard(){
		Card lastCard = turnMethod.getLastCard();
		return lastCard;
	}
}
