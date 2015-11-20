package cardgames.deck;

/**
 * @author srijith
 *
 */
public enum Rank {
	
	Two(2),
	Three(3),
	Four(4),
	Five(5),
	Six(6),
	Seven(7),
	Eight(8),
	Nine(9),
	Ten(10),
	Jack(11),
	Queen(12),
	King(13),
	Ace(14);
	
	private int val;
	
	private Rank(int val) {
		this.val = val;
	}
	
	/**
	 * @return the val
	 */
	public int getVal() {
		return val;
	}

	/**
	 * @param rank
	 * @return
	 */
	public static Rank fromVal(int val) {
		for(Rank r : Rank.values()) {
			if(r.val == val)
				return r;
		}
		return null;
	}
}
