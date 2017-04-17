

public interface players {
	Card lastCard = new Card(-1,-1);
	public Card getLastPlayedCard();
	public Card[] playTurn(Card played);
	public boolean isWinner();
	public HandofCard getHand();
	public void setHand(HandofCard inputHand);
	public String getName();
	public void drawCard(Card card);
	Card[] playTurn();
}
