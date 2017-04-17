import java.util.ArrayList;

public class Computer implements players{
	public static final int WILDCARD = 8;
	private HandofCard hand;
	private int mostSuit;
	private Card lastCard;
	private String name;
	
	public Computer(String name){
		init(name);
	}
	
	private void init(String inp){
		hand = new HandofCard();
		mostSuit = getMostSuits();
		lastCard = new Card();
		name = inp;
	}
	
	@Override
	public void drawCard(Card card){
		hand.addCard(card);
	}	
	
	private int getMostSuits() {
		int numSpades=0,numHearts=0,numClubs=0,numDiamond=0;
		
		for(Card temp: hand.getHandofCards()){
			if(temp.getSuit()==0) numSpades++;
			else if(temp.getSuit()==1) numHearts++;
			else if(temp.getSuit()==2) numDiamond++;
			else if(temp.getSuit()==3) numClubs++;
		}
		
		if(Math.max(numSpades,numHearts)==numSpades
				&&Math.max(numDiamond,numSpades)==numSpades
				&&Math.max(numClubs,numSpades)==numSpades) return 0;	
		else if(Math.max(numSpades,numHearts)==numHearts
				&&Math.max(numDiamond,numHearts)==numHearts
				&&Math.max(numClubs,numHearts)==numHearts) return 1;	
		else if(Math.max(numDiamond,numClubs)==numDiamond
				&&Math.max(numDiamond,numHearts)==numDiamond
				&&Math.max(numDiamond,numSpades)==numDiamond) return 2;
		else return 3;
	}

	@Override
	public Card[] playTurn(){
		ArrayList<Card> Cards = new ArrayList<Card>();	
		hand.sortBySuit();
		hand.sortByValue();
		ArrayList<Card> tempArray = hand.getHandofCards(); 
		Card[] handArray = new Card[tempArray.size()];
		
		int i=0;
		while(i<tempArray.size()){
			handArray[i] = tempArray.get(i);
			i++;
		}
		
		Card firstCard = new Card();
		mostSuit = getMostSuits();
		Boolean carry = false;
		
		for(Card temp: handArray){
			if(temp.getValue()==WILDCARD){
				Cards.add(temp);
				hand.removeCard(temp);
				carry = true;
				do{
					firstCard = playAfterEight();
					Cards.add(firstCard);
					hand.removeCard(firstCard);
			    }while(firstCard.getValue()==WILDCARD);						
			}
		}
			
		if(!carry){
			for(Card temp: handArray){
				if(temp.getSuit()==mostSuit){
					firstCard=temp;	
					carry = true;
				}
			    if(temp.getValue()<firstCard.getValue()&&carry
						&&temp.getValue()!=WILDCARD){
					firstCard=temp;
				}
			}
			Cards.add(firstCard);
			hand.removeCard(firstCard);
		}
			
		Card[] returnCards = new Card[Cards.size()];
		int j=0;
		while(j<Cards.size()){
			returnCards[j] = Cards.get(j);
			j++;
		}
		lastCard = returnCards[returnCards.length-1];
		return returnCards;
	}
		
	@Override
	public Card[] playTurn(Card played){
		ArrayList<Card> Cards = new ArrayList<Card>();	
		ArrayList<Card> tempArray = hand.getHandofCards(); 
		Card[] handArray = new Card[tempArray.size()];
		
		int i=0;
		while(i<tempArray.size()){
			handArray[i] = tempArray.get(i);
			i++;
		}
		
		Boolean carryFlag = false;
		Card selectedCard = new Card();
		
		for(Card temp: handArray){
			if(temp.getValue()==played.getValue()){
				selectedCard = temp;
				carryFlag=true;
			}	
			if(temp.getSuit()==mostSuit&&carryFlag) 
				break;
			else if(temp.getSuit()==played.getSuit()&&
		    		!carryFlag&&temp.getValue()!=WILDCARD){
				selectedCard = temp;
				carryFlag = true;
			}
		}
		
		if(!carryFlag){
			for(Card temp: handArray){
			    if(temp.getValue()==WILDCARD){
					Card nextCard;
					Cards.add(temp);
					hand.removeCard(temp);
					carryFlag = true;
					do{
						nextCard = playAfterEight();
						Cards.add(nextCard);
						hand.removeCard(nextCard);
					}while(nextCard.getValue()==WILDCARD);
			    }
			}
		}
		else if(carryFlag){
			Cards.add(selectedCard);
			hand.removeCard(selectedCard);
		}
		else Cards.add(new Card());
		
		Card[] returnCards = new Card[Cards.size()];
		int j=0;
		while(j<Cards.size()){
			returnCards[j] = Cards.get(j);
			j++;
		}

		lastCard = returnCards[returnCards.length-1];	
		return returnCards;
	}
	
	private Card playAfterEight() {
		Card required = new Card();
		ArrayList<Card> tempArray = hand.getHandofCards(); 
		
		int i=0; 
		Card[] handArray = new Card[tempArray.size()];
		while(i<tempArray.size()){
			handArray[i] = tempArray.get(i);
			i++;
		}
		
		mostSuit = getMostSuits();
		for(Card temp: handArray){
		    if(temp.getSuit()== mostSuit&&
		    		temp.getValue()!=8){
		    	
				required = temp;
				if(temp.getValue()>required.getValue()&&
						temp.getSuit()==required.getSuit()){
					required=temp;
				}
			}
		}
		return required;
	}

	public boolean isWinner(){
		if(hand.getCardCount() == 0) return true;
		else return false;
	}

	@Override
	public Card getLastPlayedCard() {
		return lastCard;
	}
	
	@Override
	public void setHand(HandofCard inputHand){
		hand = inputHand;
	}
	
	@Override
	public String getName(){
		return name;
	}
	
	@Override
	public HandofCard getHand(){
		return hand;
	}
}
