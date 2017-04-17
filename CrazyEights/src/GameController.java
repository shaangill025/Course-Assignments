import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class GameController{
	final int NUM_OF_DIST_CARDS = 7;
	private LinkedQueue<Card> discardPile;
	private Iterator<Card> itr;
	private Deck drawPile;
	private List<players> turns;
	//int round=1;
	IOHandler handler;
	
	public GameController(List<players> players){
		turns = players;
		drawPile = new Deck(false);
		discardPile = new LinkedQueue<Card>();
		handler = new IOHandler();
		init();
	}
 	
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
	}

	public void execGameLoop(){
		int i=0;
		Card[] cards;
		Card lastCard = null;
		

		for(players temp:turns){
			//System.out.print("\n\n" + "Player: " + temp.getName() + "\n"+ 
			//		temp.getHand().toString() + "\n\n");
			
			if(i==0){
				System.out.print("\n\n" + "------------Next Round-------" + "\n");
				cards = temp.playTurn();
			}
			else{
				itr = discardPile.getIterator();
				while(itr.hasNext()){
					lastCard =itr.next();
				}

				cards = temp.playTurn(lastCard);
			}
			
			
			while(temp.getLastPlayedCard().isJoker()){
				System.out.print("\n\nCard Draw by " + temp.getName());
				Card drawn;
				if(drawPile.cardsLeft()!=0){	  	
					drawn = drawPile.dealCard();
					temp.drawCard(drawn);
					System.out.println("\nCard drawn is : " + drawn.toString());
					cards = temp.playTurn(lastCard); 		
				}
				else{
					drawn = discardPile.dequeue();
					temp.drawCard(drawn);
					System.out.println("\nCard drawn is : " + drawn.toString());
					cards = temp.playTurn(lastCard);
				}		
			}
			
			handler.disPlay("\n\nPlayed cards by " + temp.getName() + " are: ");
			for(Card plays:cards){
				if(!plays.isJoker()){
					discardPile.enqueue(plays);
				}
				System.out.print(plays.toString() + ", ");
			}
			
			if(temp.isWinner()){
				System.out.println("\n\n" + temp.getName() + " has won the game with no cards left!");
				return;
			}
			
			i++;
		}
		sortTurns();
	}

	private void sortTurns() {
		Collections.sort(turns, new PlayerComparator());
		execGameLoop();
	}
}

class PlayerComparator implements Comparator<players>{
	@Override
	public int compare(players arg0, players arg1) {
		Card first = arg0.getLastPlayedCard();
		Card second = arg1.getLastPlayedCard();
		int i = 0;
		if(first.getValue()<second.getValue()) i=1;
		else if(first.getValue()>second.getValue()) i=-1;
		return i;
	}
}