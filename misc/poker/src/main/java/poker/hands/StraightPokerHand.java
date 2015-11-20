/**
 * 
 */
package poker.hands;

/**
 * @author srijith
 *
 */
class StraightPokerHand extends PokerHand {

	private int highStraight;

	/**
	 * @param type - either straight or straight_flush
	 * @param rankFrequencies
	 * @param highStraight
	 */
	public StraightPokerHand(Type type, int[] rankFrequencies, int highStraight) {
		super(type, rankFrequencies);
		if(type == null || 
				!(type.equals(Type.straight_flush) || type.equals(Type.straight)))
			throw new IllegalArgumentException(
					"Invalid type - should be either straight_flush or straight");
		this.highStraight = highStraight;
	}
	
	/**
	 * @return the highStraight
	 */
	public int getHighStraight() {
		return highStraight;
	}
	
	/* (non-Javadoc)
	 * @see poker.PokerHand#compareSameTypeHands(poker.PokerHand)
	 */
	@Override
	protected int compareSameTypeHands(PokerHand otherHand) {
		if(! (otherHand instanceof StraightPokerHand) ) {
			throw new IllegalArgumentException(
				"Bad input. compareSameTypeHands called with  different Poker "
				+ "type instances");
		}
		StraightPokerHand otherStraightFlush = 
				(StraightPokerHand) otherHand;
		return highStraight - otherStraightFlush.highStraight;
	}

}
