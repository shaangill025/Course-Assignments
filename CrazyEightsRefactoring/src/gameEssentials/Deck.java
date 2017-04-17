package gameEssentials;

// TODO: Auto-generated Javadoc
/**
 * The Class Deck.
 */
public class Deck {
	    
    	/** The deck. */
    	private Card[] deck;
	    
    	/** The cards used. */
    	private int cardsUsed;

	    /**
    	 * Instantiates a new deck.
    	 */
    	public Deck() {
	        this(false);  // Just call the other constructor in this class.
	    }

	    /**
    	 * Instantiates a new deck.
    	 *
    	 * @param includeJokers the include jokers
    	 */
    	public Deck(boolean includeJokers) {
	        if (includeJokers)
	            deck = new Card[54];
	        else
	            deck = new Card[52];
	        int cardCt = 0; // How many cards have been created so far.
	        for ( int suit = 0; suit <= 3; suit++ ) {
	            for ( int value = 1; value <= 13; value++ ) {
	                deck[cardCt] = new Card(value,suit);
	                cardCt++;
	            }
	        }
	        if (includeJokers) {
	            deck[52] = new Card(1,Card.JOKER);
	            deck[53] = new Card(2,Card.JOKER);
	        }
	        cardsUsed = 0;
	    }

	    /**
    	 * Shuffle.
    	 */
    	public void shuffle() {
	        for ( int i = deck.length-1; i > 0; i-- ) {
	            int rand = (int)(Math.random()*(i+1));
	            Card temp = deck[i];
	            deck[i] = deck[rand];
	            deck[rand] = temp;
	        }
	        cardsUsed = 0;
	    }

	    /**
    	 * Cards left.
    	 *
    	 * @return the int
    	 */
    	public int cardsLeft() {
	        return deck.length - cardsUsed;
	    }

	    /**
    	 * Deal card.
    	 *
    	 * @return the card
    	 */
    	public Card dealCard() {
	        if (cardsUsed == deck.length)
	            throw new IllegalStateException("No cards are left in the deck.");
	        cardsUsed++;
	        return deck[cardsUsed - 1];
	        // Programming note:  Cards are not literally removed from the array
	        // that represents the deck.  We just keep track of how many cards
	        // have been used.
	    }

	    /**
    	 * Checks for jokers.
    	 *
    	 * @return true, if successful
    	 */
    	public boolean hasJokers() {
	        return (deck.length == 54);
	    }
	} // end class Deck