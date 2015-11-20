/**
 * 
 */
package poker.hands;

import poker.PokerUtils;

/**
 * @author srijith
 *
 */
public class HighCardPokerHand extends PokerHand {

	public HighCardPokerHand(int[] rankFrequencies) {
		super(Type.high_card, rankFrequencies);
	}
	
	/* (non-Javadoc)
	 * @see poker.hands.PokerHand#compareSameTypeHands(poker.hands.PokerHand)
	 */
	@Override
	protected int compareSameTypeHands(PokerHand otherHand) {
		if(otherHand.getClass() != this.getClass()) 
			throw new IllegalArgumentException(
					"Bad input - must be a type of "+ this.getClass());
		HighCardPokerHand otherHighCardHand = (HighCardPokerHand) otherHand;
		
		return PokerUtils.compareHighCard(
				rankFrequencies, otherHighCardHand.rankFrequencies);
	}

}
