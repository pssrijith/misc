/**
 * 
 */
package poker.hands;

import org.junit.Assert;
import org.junit.Test;

import cardgames.deck.Card;
import poker.BaseTest;
import poker.PokerUtils;
import poker.hands.FlushPokerHand;
import poker.hands.PokerHand;
import poker.hands.PokerHand.Type;

/**
 * @author srijith
 *
 */
public class FlushPokerHandTest extends BaseTest {

	@Test
	public void test_createFlushPokerHand() {
		Card[] flushCards = cardsFromStringArray(
				new String[]{"C9","C8","C3","C4","C7"});
		FlushPokerHand hand = new FlushPokerHand(
				PokerUtils.getRankFrequencies(flushCards));
		
		Assert.assertNotNull(hand);
		Assert.assertEquals(PokerHand.Type.flush, hand.getType());
	}
	
	@Test
	public void test_compare_flush_both_hands_high_card_wins() {
		Card[] flush1 = cardsFromStringArray(
				new String[]{"C9","C8","C6","C4","C3"});
		Card[] flush2 = cardsFromStringArray(
				new String[]{"S10","S8","S3","S4","S7"});
		FlushPokerHand hand1 = new FlushPokerHand(
				PokerUtils.getRankFrequencies(flush1));
		FlushPokerHand hand2 = new FlushPokerHand(
				PokerUtils.getRankFrequencies(flush2));
		
		Assert.assertTrue(hand1.compare(hand2) < 0); // hand2 wins
		
		// hand3 - to test hands similar in top 4 cards
		Card[] flush3 = cardsFromStringArray(
				new String[]{"S9","S8","S6","S4","S2"});
		FlushPokerHand hand3 = new FlushPokerHand(
				PokerUtils.getRankFrequencies(flush3));
		Assert.assertTrue(hand1.compare(hand3) > 0); // hand1 wins
	}
	
	@Test
	public void test_compare_flush_draw() {
		Card[] flush1 = cardsFromStringArray(
				new String[]{"C9","C8","C3","C4","C2"});
		Card[] flush2 = cardsFromStringArray(
				new String[]{"S9","S8","S3","S4","S2"});
		FlushPokerHand hand1 = new FlushPokerHand(
				PokerUtils.getRankFrequencies(flush1));
		FlushPokerHand hand2 = new FlushPokerHand(
				PokerUtils.getRankFrequencies(flush2));
		
		Assert.assertTrue(hand1.compare(hand2) == 0); 
	}
	

	@Test(expected = IllegalArgumentException.class)
	public void test_compare_flush_null_input() {
		
		new FlushPokerHand(new int[] {}).compare(null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void test_compare_flush_invalid_type() {
		FlushPokerHand hand1 = new FlushPokerHand(new int[] {});
		hand1.compare(new PokerHand(Type.flush, new int[] {}) {
			
			@Override
			protected int compareSameTypeHands(PokerHand otherHand) {
				return 0;
			}
		});
	}
	
}
