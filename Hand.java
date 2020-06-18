package project;

import java.util.ArrayList;

public class Hand {
	
	private Deck d = new Deck(); // creates new Deck for hand to be created
	
	private ArrayList<Card> hand = new ArrayList<Card>(); // the hand
	
	public int deckCount = 0; // element counter for the deck
	
	public Hand() {
		
		int index = 0;
		
		while (index < 2) {
			addCard();
			index++;
		}
		
		
	}
	
	// adds a specific card to the deck
	public void addCard() {
		
		hand.add(d.getSpecificCard(deckCount));
		deckCount++;
	}
	
	// if the user wishes to change the value of the ace, it creates an invisible card that
	// adds to the total but is not seen for the user and acts like the value of the card changed
	public void addAce(boolean containsAnAce) {
		hand.add(new Card (0, 10));
	}
	
	
	// returns the counter for element in the deck
	public int getCardFromDeck() {
		return deckCount;
	}
	
	
	// checks if hand has an ace
	public boolean hasAce() {
		
		for(int i = 0; i < hand.size(); i++) {
			if(hand.get(i).getValue() == 1) {
				return true;
			}
		}
		
		return false;
	}
	
	// returns true if a cards contains a certain value
	// otherwise returns false
	public boolean contains(int cardValue) {
		
		for(int i = 0; i < hand.size(); i++) {
			if(hand.get(i).getValue() == cardValue) {
				return true;
			}
		}
		
		return false;
		
	}
	
	// checks total value of cards
	public int totalHandVal() {
		int sum = 0;
		
		for(int i = 0; i < hand.size(); i++) {
			if(hand.get(i).getValue() >= 10 && hand.get(i).getValue() <= 13) {
				sum += 10;
				// checks if value of card is a greater than 10 or less/equal to than 13
				// in real life blackjack, these cards are worth 10 points so updates hand Value as such
			}
			else {
				sum += hand.get(i).getValue();
			}
		}
		
		return sum;
	}
	
	
	// clears the hand of all the cards
	public void clear() {
		hand.clear();
	}
	
	
	//outputs the hand into a String value that can be printed out
	public String toString() {
		
		String totalHand = "(";
		for(Card c: hand) {
			totalHand += c.toString();
			totalHand += " | ";
		}
		totalHand = totalHand.substring(0, totalHand.length()-2); // gets rid of the extra space and divider at the end
		totalHand += ")";
		return totalHand;
	}
	
	

}
