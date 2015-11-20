package cardgames.deck;

/**
 * @author srijith
 *
 */
public enum Suit {
	Spades('S'), Clubs('C'), Diamonds('D'), Hearts('H');
	
	private char val;
	
	private Suit(char ch) {
		this.val = ch;
	}
	
	/**
	 * @return the val
	 */
	public char getVal() {
		return val;
	}
	
	public static Suit fromVal(char ch) {
		for(Suit s: Suit.values()) {
			if(s.val == ch)
				return s;
		}
		
		return null;
	}
}
