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
	 * @author srijith
	 *
	 */
	public enum Result {
		hand1Wins,
		hand2Wins,
		draw;
	}

	/**
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
