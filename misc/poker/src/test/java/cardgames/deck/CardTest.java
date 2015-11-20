/**
 * 
 */
package cardgames.deck;


import org.junit.Assert;
import org.junit.Test;

import cardgames.deck.Card;
import cardgames.deck.Rank;
import cardgames.deck.Suit;


/**
 * @author srijith
 *
 */
public class CardTest {

	@Test
	public void test_card_create() {
	
		Card card = new Card(Suit.Clubs, Rank.Jack);
		Assert.assertNotNull(card);
		Assert.assertEquals(Suit.Clubs, card.getSuit());
		Assert.assertEquals(Rank.Jack, card.getRank());
		Assert.assertEquals('C', card.getSuit().getVal());
		Assert.assertEquals(11, card.getRank().getVal());
		
		
		card = new Card('D', 14); // alternate constructor
		Assert.assertNotNull(card);
		Assert.assertEquals(Suit.Diamonds, card.getSuit());
		Assert.assertEquals(Rank.Ace, card.getRank());
		Assert.assertEquals('D', card.getSuit().getVal());
		Assert.assertEquals(14, card.getRank().getVal());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void test_card_create_invalid_suit() {
		new Card('X',12);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void test_card_create_invalid_rank() {
		new Card('C',1);
	}
	
}
