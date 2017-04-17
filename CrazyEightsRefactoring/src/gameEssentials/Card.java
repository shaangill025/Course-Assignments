package gameEssentials;
 // TODO: Auto-generated Javadoc

 /**
  * The Class Card.
  */
 public class Card{
   
   /** The Constant SPADES. */
   public final static int SPADES = 0;   // Codes for the 4 suits, plus Joker.
   
   /** The Constant HEARTS. */
   public final static int HEARTS = 1;
   
   /** The Constant DIAMONDS. */
   public final static int DIAMONDS = 2;
   
   /** The Constant CLUBS. */
   public final static int CLUBS = 3;
   
   /** The Constant JOKER. */
   public final static int JOKER = 4;

   
   /** The Constant ACE. */
   public final static int ACE = 1;      // Codes for the non-numeric cards.
   
   /** The Constant JACK. */
   public final static int JACK = 11;    //   Cards 2 through 10 have their 
   
   /** The Constant QUEEN. */
   public final static int QUEEN = 12;   //   numerical values for their codes.
   
   /** The Constant KING. */
   public final static int KING = 13;

   /** The suit. */
   private final int suit; 
   
   /** The value. */
   private final int value;
   
   /**
    * Instantiates a new card.
    */
   public Card() {
      suit = JOKER;
      value = 1;
   }
   
   /**
    * Instantiates a new card.
    *
    * @param theValue the the value
    * @param theSuit the the suit
    */
   public Card(int theValue, int theSuit) {
      if (theSuit != SPADES && theSuit != HEARTS && theSuit != DIAMONDS && 
            theSuit != CLUBS)
         throw new IllegalArgumentException("Illegal playing card suit");
      if (theValue < 1 && theValue > 13)
         throw new IllegalArgumentException("Illegal playing card value");
      value = theValue;
      suit = theSuit;
   }

   /**
    * Gets the suit.
    *
    * @return the suit
    */
   public int getSuit() {
      return suit;
   }
   
   /**
    * Gets the value.
    *
    * @return the value
    */
   public int getValue() {
      return value;
   }
   
   /**
    * Gets the suit as string.
    *
    * @return the suit as string
    */
   public String getSuitAsString() {
      switch ( suit ) {
      case SPADES:   return "Spades";
      case HEARTS:   return "Hearts";
      case DIAMONDS: return "Diamonds";
      case CLUBS:    return "Clubs";
      default:       return "Joker";
      }
   }
   
   /**
    * Gets the value as string.
    *
    * @return the value as string
    */
   public String getValueAsString() {
      if (suit == JOKER)
         return "" + Integer.toString(value);
      else {
         switch ( value ) {
         case 1:   return "Ace";
         case 2:   return "2";
         case 3:   return "3";
         case 4:   return "4";
         case 5:   return "5";
         case 6:   return "6";
         case 7:   return "7";
         case 8:   return "8";
         case 9:   return "9";
         case 10:  return "10";
         case 11:  return "Jack";
         case 12:  return "Queen";
         default:  return "King";
         }
      }
   }
   
   /**
    * Checks if is joker.
    *
    * @return true, if is joker
    */
   public boolean isJoker(){
	   if(suit==JOKER){
		   return true;
	   }
	   return false;
   }
   
   /* (non-Javadoc)
    * @see java.lang.Object#toString()
    */
   @Override
   public String toString() {
      if (suit == JOKER) {
         if (value == 1)
            return "Joker";
         else
            return "Joker #" + value;
      }
      else
         return getValueAsString() + " of " + getSuitAsString();
   }
 }