/**
 * 
 */
package poker.hands;

import cardgames.deck.Rank;
import poker.PokerUtils;

/**
 * @author srijith
 *
 */
public class FourOfAKindPokerHand extends PokerHand {

	private Rank kind4Rank;
	
	public FourOfAKindPokerHand(int[] rankFrequencies, Rank kind4Rank) {
		super(Type.four_of_a_kind, rankFrequencies);
		if(kind4Rank == null)
			throw new IllegalArgumentException("Bad input. "
				+ "FourOfAKind poker hand must have a non-null kind4Rank");
		this.kind4Rank = kind4Rank;
	}
	
	/* (non-Javadoc)
	 * @see poker.hands.PokerHand#compareSameTypeHands(poker.hands.PokerHand)
	 */
	@Override
	protected int compareSameTypeHands(PokerHand otherHand) {
		if(!(otherHand instanceof FourOfAKindPokerHand)) {
			throw new IllegalArgumentException(
				"Bad input. compareSameTypeHands called with  different Poker "
					+ "type instances");
		}
		
		FourOfAKindPokerHand kind4OtherHand = (FourOfAKindPokerHand) otherHand;
		
		if(kind4Rank.equals(kind4OtherHand.kind4Rank)) {
			// check high card - kicker
			return  PokerUtils.compareHighCard(
					rankFrequencies, kind4OtherHand.rankFrequencies);
		}
		return kind4Rank.getVal() - kind4OtherHand.kind4Rank.getVal();
	}
}
