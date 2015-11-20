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
public class ThreeOfAKindPokerHand extends PokerHand {
	
	private Rank kind3Rank;

	public ThreeOfAKindPokerHand(int[] rankFrequencies, Rank kind3Rank) {
		super(Type.three_of_a_kind, rankFrequencies);
		if(kind3Rank == null)
			throw new IllegalArgumentException(
					"Invalid input, mist have a non-null kind3Rank");
		this.kind3Rank = kind3Rank;
	}
	
	/**
	 * @return the kind3Rank
	 */
	public Rank getKind3Rank() {
		return kind3Rank;
	}
	
	/* (non-Javadoc)
	 * @see poker.hands.PokerHand#compareSameTypeHands(poker.hands.PokerHand)
	 */
	@Override
	protected int compareSameTypeHands(PokerHand otherHand) {
		if(otherHand.getClass() != this.getClass()) 
			throw new IllegalArgumentException(
					"Bad input - must be a type of "+ this.getClass());
		
		ThreeOfAKindPokerHand otherKind3Hand = (ThreeOfAKindPokerHand) otherHand;
		if(kind3Rank.equals(otherKind3Hand.kind3Rank)) {
			return PokerUtils.compareHighCard(
					rankFrequencies, otherKind3Hand.rankFrequencies);
		}
		return kind3Rank.getVal() - otherKind3Hand.kind3Rank.getVal();
	}



}
