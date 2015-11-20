package poker;

import cardgames.deck.Card;
import poker.hands.PokerHand;
import poker.hands.PokerHandBuilder;

/**
 * @author srijith
 *
 */
public class Poker {

	/**
	 * The main Poker class
	 * @author srijith
	 *
	 */
	public enum Result {
		hand1Wins,
		hand2Wins,
		draw;
	}

	/**
	 * takes 2 hands (each hand an array of 5 cards) and creates PokherHand objects via the PokerHandBuilder
	 * Returns Result.hand1Wins if cards1 is better than cards2, Result.hand2Wins if vice versa and Result.draw 
	 * when the poker hands are the same.
	 * 
	 * @param cards1
	 * @param cards2
	 * @return
	 */
	public Result compareHands(Card[] cards1, Card[] cards2) {
		
		PokerHand hand1 = PokerHandBuilder.getInstance().build(cards1);
		PokerHand hand2 = PokerHandBuilder.getInstance().build(cards2);
		
		int compareVal = hand1.compare(hand2);
		
		return (compareVal == 0 ? Result.draw:
			( compareVal > 0 ? Result.hand1Wins : Result.hand2Wins));
	}

}
