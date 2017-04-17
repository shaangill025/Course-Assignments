package gameEssentials;
import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Class HandofCard.
 */
public class HandofCard{

    /** The hand. */
    private ArrayList<Card> hand;   // The cards in the hand.
    
    /**
     * Instantiates a new handof card.
     */
    public HandofCard() {
        hand = new ArrayList<Card>();
    }

    /**
     * Clear.
     */
    public void clear() {
        hand.clear();
    }
    
    /**
     * Gets the handof cards.
     *
     * @return the handof cards
     */
    //return ArrayList<Card> arraylist
    public Card[] getHandofCards() {
    	Card[] handArray = new Card[hand.size()];
    	
    	int i=0;
		while(i<hand.size()){
			handArray[i] = hand.get(i);
			i++;
		}
        return handArray;
    }

    /**
     * Adds the card.
     *
     * @param c the c
     */
    public void addCard(Card c) {
        if (c == null)
            throw new NullPointerException("Can't add a null card to a hand.");
        hand.add(c);
    }

    /**
     * Removes the card.
     *
     * @param c the c
     */
    public void removeCard(Card c) {
        hand.remove(c);
    }

    /**
     * Removes the card.
     *
     * @param position the position
     */
    public void removeCard(int position) {
        if (position < 0 || position >= hand.size()){
            throw new IllegalArgumentException("Position does not exist in hand: " + position);
        }
        hand.remove(position);
    }

    /**
     * Gets the card count.
     *
     * @return the card count
     */
    public int getCardCount() {
        return hand.size();
    }

    /**
     * Gets the card.
     *
     * @param position the position
     * @return the card
     */
    public Card getCard(int position) {
        if (position < 0 || position >= hand.size())
            throw new IllegalArgumentException("Position does not exist in hand: "
                    + position);
        return hand.get(position);
    }

    /**
     * Sort by suit.
     */
    public void sortBySuit() {
        ArrayList<Card> newHand = new ArrayList<Card>();
        while (hand.size() > 0) {
            int pos = 0;  // Position of minimal card.
            Card c = hand.get(0);  // Minimal card.
            for (int i = 1; i < hand.size(); i++) {
                Card c1 = hand.get(i);
                if ( c1.getSuit() < c.getSuit() ||
                        (c1.getSuit() == c.getSuit() && c1.getValue() < c.getValue()) ) {
                    pos = i;
                    c = c1;
                }
            }
            hand.remove(pos);
            newHand.add(c);
        }
        hand = newHand;
    }

    /**
     * Sort by value.
     */
    public void sortByValue() {
        ArrayList<Card> newHand = new ArrayList<Card>();
        while (hand.size() > 0) {
            int pos = 0;  // Position of minimal card.
            Card c = hand.get(0);  // Minimal card.
            for (int i = 1; i < hand.size(); i++) {
                Card c1 = hand.get(i);
                if ( c1.getValue() < c.getValue() ||
                        (c1.getValue() == c.getValue() && c1.getSuit() < c.getSuit()) ) {
                    pos = i;
                    c = c1;
                }
            }
            hand.remove(pos);
            newHand.add(c);
        }
        hand = newHand;
    }
    
    /**
     * Checks for suit.
     *
     * @param suit the suit
     * @return true, if successful
     */
    public boolean hasSuit(int suit){
    	boolean returnSuit = false;
    	for(Card temp: hand){
    		if(temp.getSuit()==suit){
    			returnSuit = true;
    			return returnSuit;
    		}
    		else returnSuit = false;
    	}
    	return returnSuit;
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString(){
    	String str1 = "";
    	String str2 = "";
    	int i=0;
    	for(Card temp: hand){
    		str1 += "	" + i + "	"; 
    		str2 += temp.toString() + " : ";
    		i++;
    	}
		return str1 + "\n" + str2;
    	
    }

	/**
	 * Gets the most suit.
	 *
	 * @return the most suit
	 */
	public int getMostSuit() {
		int numSpades=0;
		int numHearts=0;
		int numClubs=0;
		int numDiamond=0;
		
		for(Card temp: hand){
			if(temp.getSuit()==0) 
				numSpades++;
			else if(temp.getSuit()==1) 
				numHearts++;
			else if(temp.getSuit()==2) 
				numDiamond++;
			else if(temp.getSuit()==3) 
				numClubs++;
		}
		
		if(Math.max(numSpades,numHearts)==numSpades
				&&Math.max(numDiamond,numSpades)==numSpades
				&&Math.max(numClubs,numSpades)==numSpades) 
			return 0;	
		else if(Math.max(numSpades,numHearts)==numHearts
				&&Math.max(numDiamond,numHearts)==numHearts
				&&Math.max(numClubs,numHearts)==numHearts) 
			return 1;	
		else if(Math.max(numDiamond,numClubs)==numDiamond
				&&Math.max(numDiamond,numHearts)==numDiamond
				&&Math.max(numDiamond,numSpades)==numDiamond) 
			return 2;
		else return 3;
	}
} 
