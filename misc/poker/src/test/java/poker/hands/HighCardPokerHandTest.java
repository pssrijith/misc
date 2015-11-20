/**
 * 
 */
package poker.hands;

import org.junit.Assert;
import org.junit.Test;

import cardgames.deck.Card;
import poker.BaseTest;
import poker.PokerUtils;
import poker.hands.HighCardPokerHand;
import poker.hands.PokerHand;
import poker.hands.PokerHand.Type;

/**
 * @author srijith
 *
 */
public class HighCardPokerHandTest extends BaseTest{
	
	@Test
	public void test_createHighCardPokerHand() {
		Card[] highCards = cardsFromStringArray(
				new String[]{"C9","D10","S3","H4","H5"});
		HighCardPokerHand hand = new HighCardPokerHand(
				PokerUtils.getRankFrequencies(highCards));
		
		Assert.assertNotNull(hand);
		Assert.assertEquals(PokerHand.Type.high_card, hand.getType());
		
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void test_compare_invalid_class() {

		HighCardPokerHand hand1 = new HighCardPokerHand(
				new int[] {});
		hand1.compare(new PokerHand(Type.high_card, new int[] {}) {
			
			@Override
			protected int compareSameTypeHands(PokerHand otherHand) {
				return 0;
			}
		});
	}
	
	@Test
	public void test_compare_high_card_wins() {
		// high card: 2 nines
		Card[] cards1 = cardsFromStringArray(
				new String[]{"C10","D9","S4","H5","H3"});
		// 2 pair : 2 10s
		Card[] cards2 = cardsFromStringArray(
				new String[]{"C14","D10","S3","H2","H12"}); // hand 2 wins
		
		HighCardPokerHand hand1 = new HighCardPokerHand(
				PokerUtils.getRankFrequencies(cards1));
		
		HighCardPokerHand hand2 = new HighCardPokerHand(
				PokerUtils.getRankFrequencies(cards2));
		
		Assert.assertTrue(hand1.compare(hand2) < 0);
	}
	

	@Test
	public void test_compare_same_card_ranks__draw() {
		// 10,9,3,5,2
		Card[] cards1 = cardsFromStringArray(
				new String[]{"C10","D9","S3","H5","H2"});
		// 10,9,3,5,2
		Card[] cards2 = cardsFromStringArray(
				new String[]{"S10","H9","C3","D5","C2"}); //draw
		
		HighCardPokerHand hand1 = new HighCardPokerHand(
				PokerUtils.getRankFrequencies(cards1));
		
		HighCardPokerHand hand2 = new HighCardPokerHand(
				PokerUtils.getRankFrequencies(cards2));
		
		Assert.assertTrue(hand1.compare(hand2) == 0);
	}
	
}
