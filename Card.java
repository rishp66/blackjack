package project;

public class Card {
	
	private int s; // suit of card
	private int v; // value of card
	
	// declare constants
	private final int SPADES = 0;
	private final int DIAMONDS = 1;
	private final int CLUBS = 2;
	private final int HEARTS = 3;
	
	
	public Card(int suit, int value) {
		s = suit;
		v = value;
	}
	
	
	public int getValue() {
		return v;
	}
	
	public int getSuit() {
		return s;
	}
	
	public String valueOfCard(Card c) {
		
		int val = c.getValue();
		String s = "";
		
		if(val == 1) {
			s += "A"; // if value of card = 1, then it is an ace
		}
		else if (val == 11){
			s += "J"; // if value of card = 11, then it is an jack
		}
		else if (val == 12) {
			s += "Q"; // if value of card = 12, then it is an queen
		}
		else if (val == 13) {
			s += "K"; // if value of card = 13, then it is an king
		}
		else {
			s += ("" + val);
		}
		
		return s;
		
	}

	public String toString() {
		int suit = this.getSuit();
		String totalCard = ""; // returns the card
		
		if(suit == SPADES) {
			totalCard = "Spade: " + valueOfCard(this);
		}
		else if(suit == DIAMONDS) {
			totalCard = "Diamond: " + valueOfCard(this);
		}
		else if(suit == CLUBS) {
			totalCard = "Club: " + valueOfCard(this);
		}
		else if(suit == HEARTS) {
			totalCard = "Heart: " + valueOfCard(this);
		}
		
		return totalCard;
	}
	
	
	
	
}
