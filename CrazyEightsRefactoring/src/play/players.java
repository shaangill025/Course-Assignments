package play;
import gameEssentials.Card;
import gameEssentials.HandofCard;

// TODO: Auto-generated Javadoc
/**
 * The Interface players.
 */
public interface players {
	
	/**
	 * Play turn.
	 *
	 * @return the card[]
	 */
	Card[] playTurn();
	
	/**
	 * Play turn.
	 *
	 * @param played the played
	 * @return the card[]
	 */
	Card[] playTurn(Card played);
	
	/**
	 * Gets the hand.
	 *
	 * @return the hand
	 */
	HandofCard getHand();
    
    /**
     * Sets the hand.
     *
     * @param inputHand the new hand
     */
    void setHand(HandofCard inputHand);
	
	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	String getName();
	
	/**
	 * Draw card.
	 *
	 * @param card the card
	 */
	void drawCard(Card card);	
	
	/**
	 * Gets the last played card.
	 *
	 * @return the last played card
	 */
	Card getLastPlayedCard();
}
