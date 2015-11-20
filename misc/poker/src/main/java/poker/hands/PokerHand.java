package poker.hands;

/**
 * @author srijith
 *
 */
public abstract class PokerHand {
	
	public static enum Type {
		high_card,
		one_pair,
		two_pair,
		three_of_a_kind,
		straight,
		flush,
		full_house,
		four_of_a_kind,
		straight_flush
	}
	
	protected Type type;
	protected int[] rankFrequencies;
	
	/**
	 * @param type
	 * @param rankFrequencies
	 * @param card
	 */
	public PokerHand(PokerHand.Type type, int[] rankFrequencies) {
		this.type = type;
		this.rankFrequencies = rankFrequencies;
	}
	
	/**
	 * @return the type
	 */
	public Type getType() {
		return type;
	}
	
	/**
	 * @param otherHand
	 * @return
	 */
	public int compare(PokerHand otherHand) {
		if(otherHand == null)
			throw new IllegalArgumentException("Cannot compare hand with null"); 
		if(type.ordinal() == otherHand.type.ordinal())
			return compareSameTypeHands(otherHand);
		
		return type.ordinal() - otherHand.type.ordinal();
	}

	/**
	 * @param otherHand
	 * @return
	 */
	protected abstract int compareSameTypeHands(PokerHand otherHand);

}
