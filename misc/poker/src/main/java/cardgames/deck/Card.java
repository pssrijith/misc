package cardgames.deck;

/**
 * @author srijith
 *
 */
public class Card {
	
	private Suit suit;
	private Rank rank;
	
	public Card(Suit suit, Rank rank) {
		if(suit == null || rank == null)
			throw new IllegalArgumentException("invalid suit and rank");
		this.suit = suit;
		this.rank = rank;
	}
	
	public Card(char suitChar, int rankVal) {
		this(Suit.fromVal(suitChar), Rank.fromVal(rankVal));
	}

	/**
	 * @return the suit
	 */
	public Suit getSuit() {
		return suit;
	}
	
	/**
	 * @return the rank
	 */
	public Rank getRank() {
		return rank;
	}
}
