/**
 * 
 */
package poker.hands;

import java.util.List;

import cardgames.deck.Rank;

/**
 * @author srijith
 *
 */
public class TwoPairPokerHand extends PokerHand {

	List<Rank> pairRanks;
	public TwoPairPokerHand(int[] rankFrequencies, List<Rank> pairRanks) {
		super(Type.two_pair, rankFrequencies);
		if(pairRanks == null || pairRanks.size() <2)
			throw new IllegalArgumentException("Invalid input -"
					+ "must have atleast 2 pair Ranks");
		this.pairRanks = pairRanks;
	}

	/* (non-Javadoc)
	 * @see poker.hands.PokerHand#compareSameTypeHands(poker.hands.PokerHand)
	 */
	@Override
	protected int compareSameTypeHands(PokerHand otherHand) {
		if(otherHand.getClass() != this.getClass()) 
			throw new IllegalArgumentException(
					"Bad input - must be a type of "+ this.getClass());
		
		TwoPairPokerHand other2PairHand = (TwoPairPokerHand) otherHand;
		
		for(int i=0; i<pairRanks.size(); i++) {
			Rank pairRank = pairRanks.get(i);
			Rank otherHandPairRank = other2PairHand.pairRanks.get(i);
			if(!pairRank.equals(otherHandPairRank)) {
				return pairRank.getVal() - otherHandPairRank.getVal();
			} 
			// else same pairs - look at the next pair
		}
		// if we reached here,then both hands have 2 identical pairs - check kicker
		int i=14, j=14;
		while(i>=2 && rankFrequencies[i] != 1)
			--i;
		while(j>=2 && other2PairHand.rankFrequencies[j] != 1)
			--j;
		if(i != j)
			return i-j;
		
		// if we reach here we have same kicker too.. so we have a draw
		return 0;
	}

}
