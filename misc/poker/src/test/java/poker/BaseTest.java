package poker;
/**
 * 
 */


import cardgames.deck.Card;

/**
 * @author srijith
 *
 */
public class BaseTest {

	protected Card[] cardsFromStringArray(String[] input) {
		
		Card[] cards = new Card[input.length];
		int i=0;
		for(String str : input) {
			cards[i++] = new Card(str.charAt(0), Integer.parseInt(str.substring(1)));
		}
		return cards;
	}
}
