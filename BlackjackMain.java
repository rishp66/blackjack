package project;

import java.util.*;

public class BlackjackMain {
	private static Scanner s = new Scanner(System.in); // uses Scanner to ask user questions
	private static ArrayList<Hand> hands = new ArrayList<Hand>(); // hands for the each of the player's hands
	private static int player = 0; // represents the current player
	private static int numOfPlayers = 0; // represents the number of players
	private static ArrayList<Integer> bets = new ArrayList<Integer>(); // keeps track of player's bets
	private static int startAmount = 0; // starting amount for the round
	
	private static ArrayList<String> whoWon = new ArrayList<String>(); // represents winners for the game

	public static void main(String[] args) {
		startGame();
	}

	
	/* Starts the game of blackjack by asking how many players, how much the startAmount is
	 * Also, adds hands corresponding to how many players there are
	 * Then, it asks how much each player would like to bet
	 * Updates the value of the dealer in each arrayList since the dealer is at index 0
	 * Changes the value of player to 1 since dealer is at index 0
	 */
	public static void startGame() {
		System.out.println("Welcome to Blackjack!");
		System.out.println("\nHow many players? (NOT INCLUDING DEALER)");
		

		numOfPlayers = s.nextInt();
		
		System.out.println("\nHow much is the starting amount?");
		startAmount = s.nextInt();

		for (int i = 0; i <= numOfPlayers; i++) {
			// index at 0 represents the dealer
			hands.add(new Hand());
		}
	
		int money = 0;
		for(int i = 1; i <= numOfPlayers; i++) {
			System.out.println("\nHow much does Player " + i + " bet?");
			money = s.nextInt();
			bets.add(money);
		}
		bets.add(0, 0); // represents the dealer at 0 so each element will be corresponding to the hands arrayList

		player = 1; // changes since element at 0 is the dealer
		firstTurn();
	}

	
	/* Method that does the firstTurn of the game
	 * 1. Checks if the player has an ace on the first Turn and displays both possible hand values
	 * Otherwise it will display the hand value 
	 * If the player gets blackjack on their first Turn, it outputs Blackjack and automatically adds
	 * that player to the list of winners
	 */
	public static void firstTurn() {
		
		if (hands.get(player).hasAce()) { // checks if player has ace on first turn and displays two values
			System.out.print("Hand: " + hands.get(player).toString() + "  SUM: " + hands.get(player).totalHandVal()
					+ " OR " + (hands.get(player).totalHandVal() + 10));
		} 
		
		if (hands.get(player).totalHandVal() == 21
				|| hands.get(player).hasAce() && hands.get(player).totalHandVal() + 10 == 21) {
			System.out.println("\nPlayer " + player + " has Blackjack!");
			whoWon.add("Player " + player + " | Amount Won: " + (bets.get(player) + startAmount));
			
			
			System.out.println("Next Player's Turn.");
			if (numOfPlayers == 1) {
				dealerTurn();
			}
			nextPlayerTurn();
		}
		decide();

	}
	
	/* Method that lets the player choose whether they would like hit or stand
	 * First, checks if the player has an ace with a boolean value
	 * If the player picks 0 with an ace in their possesion, it first asks them if they would like to change
	 * the value of the ace and then adds an imaginary card to their total Hand
	 * If the player has busted, their money reduces to 0 and it moves on to the next player or dealer in a certain situation
	 * If the user hits stand, then they will not recieve a card
	 * However, if they have an ace in their possesion, it will still ask them if they want to change the value
	 * Finally, it moves on to the next player
	 * 
	 */

	public static void decide() {
		int decide = 0;
		int choose = 0;
		System.out.println("\nPlayer " + player + "'s Turn --");
		System.out.println("Player " + player + " Hand: " + hands.get(player).toString());
		System.out.println("Hand Value: " + hands.get(player).totalHandVal());

		System.out.println("\nChoose 0 to Hit. Choose 1 to Stand.");
		decide = s.nextInt();
		boolean b = hands.get(player).hasAce();
		if (decide == 0) {
			hands.get(player).addCard();
			if (b) {
				System.out.println(
						"First, would you like your ace to be a 1 or a 11? Type 1 for the value of 1 and 2 for the value of 11?");
				choose = s.nextInt();
				if (choose == 1) {
					System.out.println("Your A will be a 1.");
				} else {
					hands.get(player).addAce(true);
				}
			}

			if (hands.get(player).totalHandVal() > 21) {
				System.out.println(hands.get(player).toString() + " Value: " + hands.get(player).totalHandVal());
				System.out.println("Player " + player + " has BUSTED!");
				bets.set(player, 0);
			
				if (numOfPlayers == 1) {
					dealerTurn();
				} else {
					nextPlayerTurn();
				}
			} else {
				decide();
			}

		} else if (decide == 1) {
			System.out.println("Player " + player + " Hand: " + hands.get(player).toString());
			System.out.println("Hand Value: " + hands.get(player).totalHandVal());
			if (b) {
				System.out.println(
						"Would you like your ace to be a 1 or a 11? Type 1 for the value of 1 and 2 for the value of 11?");
				choose = s.nextInt();
				if (choose == 1) {
					System.out.println("Your A will be a 1.");
				} else {
					hands.get(player).addAce(true);
					System.out.println("Player " + player + " Hand: " + hands.get(player).toString());
					System.out.println("Hand Value: " + hands.get(player).totalHandVal());
				}
			}
			nextPlayerTurn();
		}

	}

	// keeps going through each player and repeats the process in decision
	// it essentially updates the value of the current player
	public static void nextPlayerTurn() {
		if (player == numOfPlayers) {
			player = 0;
			dealerTurn();
		} else {
			player++;
			decide();
		}
	}

	// checks if the dealer has blackjack and then everyone loses or the dealer
	// keeps drawing until the dealer reaches 17 or over
	// after that, it moves on to check the winners of the game
	public static void dealerTurn() {
		System.out.println("The dealer is now going.");
		System.out.println("Dealer Hand: " + hands.get(player).toString());
		if (hands.get(player).totalHandVal() == 21) {
			System.out.println("Dealer got 21! Everyone has lost.");
			playAgain();
		}
		while (hands.get(player).totalHandVal() < 17) {
			hands.get(player).addCard();
			System.out.println("Dealer has drawn another card.\n");
			System.out.println(
					"Dealer Hand: " + hands.get(player).toString() + "Value: " + hands.get(player).totalHandVal());
			System.out.println();
		}
		checkWinners();

	}

	/* After ending the dealer's turn, it checks the winners of the game;
	 * First, it checks if the dealer has busted and then everyone else is declared a winner
	 * After that, if that condition is not true, it goes through each hand and updates who won
	 * based on their individual hand values
	 * Finally, it asks the player if they want to play again
	 */
	public static void checkWinners() {
		
		if(hands.get(0).totalHandVal() > 21) {
			System.out.println("Dealer Busted.");
			for(int i = 0; i < hands.size(); i++) {
				if(hands.get(i).totalHandVal() <= 21) {
					whoWon.add("Player: " + i + " | Amount Won: " + (bets.get(i) + startAmount));
					
				}
			}
			System.out.println(" Winners: " + whoWon);
			
		}
		
		for (int i = 1; i <= numOfPlayers; i++) {
			
			//if the whoWon arrayList already has a player, it skips it and moves on
			if (whoWon.contains("Player " + i)) {
				
			}
			// if statement checks if player has larger value than dealer and player is less than or equal to 21
			// also checks if dealer is greater than 21 and player is less than or equal to 21
			// finally, checks if player has 21
			// then adds them to winner
			else if (((hands.get(i).totalHandVal() > hands.get(0).totalHandVal()) && hands.get(i).totalHandVal() <= 21)
					|| (hands.get(0).totalHandVal() > 21 && hands.get(i).totalHandVal() <= 21)
					|| hands.get(i).totalHandVal() == 21) {
				whoWon.add("Player " + i + "| Amount Won: " + (bets.get(i) + startAmount));
			}
		}
		System.out.println("Winners: ");
		if (whoWon.isEmpty()) {
			System.out.println("No one has won the game.");
		} else {
			System.out.println(whoWon);
		}
		
		
		playAgain();

	}
	
	
	/* Asks the user if they would like to play again and if they type 0
	 * It will clear all the arrayLists and then start the game over again
	 * Otherwise, the program will output a thank you message and then terminate
	 */
	public static void playAgain() {
		
		System.out.println("Type 0 to play again. Type 1 to quit.");
		int choose = s.nextInt();
		if(choose == 0) {
			for(int i = 0; i < hands.size(); i++) {
				hands.get(i).clear();
			}
			hands.clear();
			for(int i = 0; i < whoWon.size(); i++) {
				whoWon.remove(i);
			}
			whoWon.clear();
			startGame();
			
		}
		else if(choose == 1) {
			System.out.println("Thanks for Playing!");
			System.exit(0);
		}
		
	}

}
