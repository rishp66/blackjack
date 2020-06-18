package project;



public class Deck {

	private Card[] deck = new Card[52];

	public Deck() {

		deck = createCardArray();
		shuffle(deck);

	}

	// creates a deck of cards in order of their suits and numbers
	public Card[] createCardArray() {

		Card[] cards = new Card[52];

		int index = 0;
		int count = 1;

		while (index < cards.length) {

			if (count > 13) {
				count = 1;
				// checks if value of cards exceeds a deck value
			}

			if (index >= 0 && index <= 12) {
				cards[index] = new Card(0, count); // creates cards for spades

			} else if (index >= 13 && index <= 25) {
				cards[index] = new Card(1, count); // creates cards for diamonds

			} else if (index >= 26 && index <= 38) {
				cards[index] = new Card(2, count); // creates cards for clubs

			} else if (index >= 39 && index <= 51) {
				cards[index] = new Card(3, count); // creates cards for hearts

			}
			index++;
			count++;
		}

		return cards;

	}

	
	/* Shuffles the deck using swap logic by creating temporary variables for certain cards
	 * Then, it updates the value in the random position and the array of cards to be swapped
	 * 
	 */
	public void shuffle(Card[] c) {

		int random = 0;

		for (int i = 0; i < 52; i++) {

			random = (int) (Math.random() * 52); // generates random value from 0-51

			Card c1 = c[i];
			Card c2 = c[random];
			c[random] = c1;
			c[i] = c2;

		}

	}

	// return a specific card at a certain index in the array
	public Card getSpecificCard(int count) {
		return deck[count];
	}

	
	// used for TESTING purposes to make sure that Deck contained all 52 cards
	// outputted the deck
	public String toString() {

		String totalDeck = "";

		int count = 0;

		for (Card c : deck) {
			totalDeck += c.toString();
			totalDeck += "  ";
			count++;
			if (count == 4) {
				totalDeck += "\n----------------------------------------------\n";
				count = 0;
			}

		}

		return totalDeck;
	}

}
