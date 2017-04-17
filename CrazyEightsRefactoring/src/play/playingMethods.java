package play;

import java.util.ArrayList;

import gameEssentials.Card;

// TODO: Auto-generated Javadoc
/**
 * The Interface playingMethods.
 */
public interface playingMethods {
	
	/**
	 * Play at first turn.
	 *
	 * @return the card[]
	 */
	Card[] playAtFirstTurn();
	
	/**
	 * Play on a card.
	 *
	 * @param card the card
	 * @return the card[]
	 */
	Card[] playOnACard(Card card);
	
	/**
	 * Gets the last card.
	 *
	 * @return the last card
	 */
	
	ArrayList<Card> playOnEight();
	
	Card getLastCard();
}
