/**
 * 
 */
package poker.hands;

import java.util.List;

import cardgames.deck.Rank;
import poker.PokerUtils;

/**
 * @author srijith
 *
 */
public class OnePairPokerHand extends PokerHand {

	Rank pairRank;
	/**
	 * @param type
	 * @param rankFrequencies
	 */
	public OnePairPokerHand(int[] rankFrequencies, List<Rank> pairRanks) {
		super(Type.one_pair, rankFrequencies);
		if(pairRanks == null || pairRanks.size() != 1)
			throw new IllegalArgumentException("Invalid input -"
					+ "must have only 1 pair Rank in the pairRanks list");
		this.pairRank = pairRanks.get(0);
	}

	/* (non-Javadoc)
	 * @see poker.hands.PokerHand#compareSameTypeHands(poker.hands.PokerHand)
	 */
	@Override
	protected int compareSameTypeHands(PokerHand otherHand) {
		if(otherHand.getClass() != this.getClass()) 
			throw new IllegalArgumentException(
					"Bad input - must be a type of "+ this.getClass());
		OnePairPokerHand otherOnePairHand = (OnePairPokerHand) otherHand;
		
		if(pairRank.equals(otherOnePairHand.pairRank)) {
			// equal pair ranks.. check high card
			return PokerUtils.compareHighCard(
					rankFrequencies, otherOnePairHand.rankFrequencies);
		}
		
		return pairRank.getVal() - otherOnePairHand.pairRank.getVal();
	}

}
