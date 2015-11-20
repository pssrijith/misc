/**
 * 
 */
package poker.hands;

import poker.PokerUtils;

/**
 * @author srijith
 *
 */
public class FlushPokerHand extends PokerHand {

	public FlushPokerHand(int[] rankFrequencies) {
		super(Type.flush, rankFrequencies);
	}
	
	/* (non-Javadoc)
	 * @see poker.hands.PokerHand#compareSameTypeHands(poker.hands.PokerHand)
	 */
	@Override
	protected int compareSameTypeHands(PokerHand otherHand) {
		if(otherHand.getClass() != FlushPokerHand.class)
			throw new IllegalArgumentException(
				"Bad input - must be a type of "+ this.getClass());
		
		FlushPokerHand otherFlushHand = (FlushPokerHand) otherHand;
		
		return PokerUtils.compareHighCard(
				rankFrequencies, otherFlushHand.rankFrequencies);
	}

}
