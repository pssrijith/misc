/**
 * 
 */
package poker.hands;

import cardgames.deck.Rank;

/**
 * @author srijith
 *
 */
public class FullHousePokerHand extends PokerHand {

	private Rank kind3Rank;
	private Rank pairRank;
	
	public FullHousePokerHand(int[] rankFrequencies, Rank kind3Rank, 
			Rank pairRank) {
		super(Type.full_house, rankFrequencies);
		if(kind3Rank == null || pairRank == null)
			throw new IllegalArgumentException("Bad input to constructor. "
					+ "FullHouse must have non-null kind3 and pair Ranks");
		this.kind3Rank = kind3Rank;
		this.pairRank = pairRank;
	}
	
	/**
	 * @return the kind3Rank
	 */
	public Rank getKind3Rank() {
		return kind3Rank;
	}
	
	/**
	 * @return the pairRank
	 */
	public Rank getPairRank() {
		return pairRank;
	}
	/* (non-Javadoc)
	 * @see poker.hands.PokerHand#compareSameTypeHands(poker.hands.PokerHand)
	 */
	@Override
	protected int compareSameTypeHands(PokerHand otherHand) {
		if(otherHand.getClass() != FullHousePokerHand.class)
			throw new IllegalArgumentException(
					"Bad input - must be a type of "+ this.getClass());
		
		FullHousePokerHand otherFullHouseHand = (FullHousePokerHand) otherHand;
		
		if(kind3Rank.getVal() == otherFullHouseHand.kind3Rank.getVal())
			return pairRank.getVal() - otherFullHouseHand.pairRank.getVal();
		
		return kind3Rank.getVal() - otherFullHouseHand.kind3Rank.getVal();
	}

}
