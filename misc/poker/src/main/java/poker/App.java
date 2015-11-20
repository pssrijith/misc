package poker;

import java.util.Scanner;

import cardgames.deck.Card;

/**
 * Hello world!
 *
 */
public class App  {
	
	public static Card[] cardsFromStringArray(String[] input) {
		
		Card[] cards = new Card[input.length];
		int i=0;
		for(String str : input) {
			cards[i++] = new Card(str.charAt(0), Integer.parseInt(str.substring(1)));
		}
		return cards;
	}
	
	public static void printWelcome() {
		System.out.println("********** Welcome to Poker hand checker *********** \n"
				+ "Enter input for hand cards in the following format:\n"
				+ "S14 C13 D12 H2 S10\n"
				+ "(Note: card ranks are from 2 -14 with 14=Ace, 13=King and so on)\n");
	}
	
    public static void main( String[] args ) {
    	printWelcome();
    	Scanner scanner = new Scanner(System.in);
    	Poker poker = new Poker();
    	try {
			while(true) {
				try {
					System.out.println("\nEnter hand 1 cards (or Ctrl-c to terminate) : ");
					String input1 = scanner.nextLine();
					Card[] hand1 = cardsFromStringArray(input1.split(" "));
					
	
					System.out.println("Enter hand 2 cards : ");
					String input2 = scanner.nextLine();
					Card[] hand2 = cardsFromStringArray(input2.split(" "));
					System.out.println("Result : " + poker.compareHands(hand1, hand2));
				} catch(IllegalArgumentException iae) {
					System.err.println("ERROR ### - " + iae.getMessage());
				}

			}
    	} finally {
    		scanner.close();
    	}
    }
}
