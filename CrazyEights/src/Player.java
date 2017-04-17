import java.util.ArrayList;
import java.util.Scanner;

public class Player implements players{
	public static final int WILDCARD = 8;
	private IOHandler inputHandler;
	private Scanner sc;
	private HandofCard hand;
	private String name;
	private Card lastCard;
	
	public Player(String str){
		init(str);
	}
	
	private void init(String inp){
		this.name = inp;
		hand = new HandofCard();
		inputHandler = new IOHandler();
		lastCard = new Card();
		sc = inputHandler.recieve();
	}
	
	@Override
	public void setHand(HandofCard inputHand){
		hand = inputHand;
	}
	
	@Override
	public HandofCard getHand(){
		return hand;
	}
	
	@Override
	public void drawCard(Card card){
		hand.addCard(card);
	}
	
	@Override
	public Card[] playTurn() {
		Card selectedCard = new Card();
		int index = 0;
		ArrayList<Card>  selected = new ArrayList<Card>();
		Card[] returnCards;
		
		inputHandler.disPlay("\n" + name + "'s is the First turn");
		
		inputHandler.disPlay("\n\nType Y if you want to sort cards by value else type N");
		String value = sc.next();
		if(value.equalsIgnoreCase("y")){
			hand.sortByValue();
		}
		inputHandler.disPlay("\nType Y if you want to sort cards by suit else type N");
		String suit = sc.next();
		if(suit.equalsIgnoreCase("y")){
			hand.sortBySuit();
		}
		
		inputHandler.displayForUser();
		System.out.print(hand.toString());			
		index = sc.nextInt();
		if(index>=0&&index<hand.getCardCount()){
			selectedCard = hand.getCard(index);
			selected.add(selectedCard);
			hand.removeCard(selectedCard);
			
			while(selectedCard.getValue()==WILDCARD){
				inputHandler.displayForEights();
				System.out.print(hand.toString());
				index = sc.nextInt();
				if(index>=0&&index<hand.getCardCount()){
					selectedCard = hand.getCard(index);
					selected.add(selectedCard);
					hand.removeCard(selectedCard);
				}
			}
		}

		returnCards = new Card[selected.size()];
		int i = 0;
		while(i<selected.size()){
			returnCards[i] = selected.get(i);
			i++;
		}
	
		lastCard = returnCards[returnCards.length-1];
		return returnCards;		
		
	}
	
	@Override
	public Card[] playTurn(Card played){
		Card selectedCard = new Card();
		int index = 0;
		
		Card[] returnCards;
		ArrayList<Card>  selected = new ArrayList<Card>();
		
		inputHandler.disPlay("\n\nType Y if you want to sort cards by value else type N");
		String value = sc.next();
		if(value.equalsIgnoreCase("y")){
			hand.sortByValue();
		}
		inputHandler.disPlay("\nType Y if you want to sort cards by suit else type N");
		String suit = sc.next();
		if(suit.equalsIgnoreCase("y")){
			hand.sortBySuit();
		}
		
		inputHandler.disPlay("\nPlayed card is the following\n" + played.toString());
		
		inputHandler.displayForUser();
		System.out.print(hand.toString());
		index = sc.nextInt();
		selectedCard = hand.getCard(index);
		
		ArrayList<Card> tempArray = hand.getHandofCards(); 
		Card[] handArray = new Card[tempArray.size()];
		
		int i=0;
		while(i<tempArray.size()){
			handArray[i] = tempArray.get(i);
			i++;
		}
		
		boolean cardCheckFlag = false;
		for(Card inHand: handArray){
			if(played.getSuit()==inHand.getSuit()&
					selectedCard.getSuit()==played.getSuit()){
					
				cardCheckFlag = true;
			}
			else if(played.getValue()==inHand.getValue()&&
					selectedCard.getValue()==played.getValue()){	
			
				cardCheckFlag = true;
			}
			else if(inHand.getValue()==WILDCARD&&
					selectedCard.getValue()==WILDCARD){
				
				cardCheckFlag = true;
			}
		}
		
		if(cardCheckFlag){				
				 selected.add(selectedCard);
				 hand.removeCard(selectedCard);		
				 
				 while(selectedCard.getValue()==WILDCARD){
						inputHandler.displayForEights();
						System.out.print(hand.toString());
						index = sc.nextInt();
						selectedCard = hand.getCard(index);
						selected.add(selectedCard);
						hand.removeCard(selectedCard);
				}
		}
		else selected.add(new Card());
		
		returnCards = new Card[selected.size()];
		int j = 0;
		while(j<selected.size()){
			returnCards[j] = selected.get(j);
			j++;
		}
		
		lastCard = returnCards[returnCards.length-1];
		return returnCards;
	}
	
	@Override
	public boolean isWinner(){
		if(hand.getCardCount() == 0) 
			return true;
		else return false;
	}
	
	@Override
	public String getName(){
		return name;
	}
	
	@Override
	public Card getLastPlayedCard(){
		return lastCard;
	}

}
