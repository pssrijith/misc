/**
 * 
 */
package poker.hands;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import cardgames.deck.Card;
import cardgames.deck.Rank;
import poker.BaseTest;
import poker.PokerUtils;
import poker.hands.OnePairPokerHand;
import poker.hands.PokerHand;
import poker.hands.PokerHand.Type;

/**
 * @author srijith
 *
 */
public class OnePairPokerHandTest extends BaseTest{
	
	@Test
	public void test_createOnePairPokerHand() {
		Card[] pair1Cards = cardsFromStringArray(
				new String[]{"C9","D9","S3","H4","H5"});
		OnePairPokerHand hand = new OnePairPokerHand(
				PokerUtils.getRankFrequencies(pair1Cards), 
				PokerUtils.getPairRanks(pair1Cards));
		
		Assert.assertNotNull(hand);
		Assert.assertEquals(PokerHand.Type.one_pair, hand.getType());
		
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void test_createOnePairPokerHand_null_pairRanks() {
		Card[] pair1Cards = cardsFromStringArray(
				new String[]{"C9","D9","S3","H2","H3"});
		new OnePairPokerHand(
				PokerUtils.getRankFrequencies(pair1Cards),null);
		
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void test_createOnePairPokerHand_two_pairRanks() {
		Card[] pair2Card = cardsFromStringArray(
				new String[]{"C9","D9","S4","H4","H3"});
		new OnePairPokerHand(
				PokerUtils.getRankFrequencies(pair2Card),
				PokerUtils.getPairRanks(pair2Card));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void test_compare_invalid_class() {
		List<Rank> pairRanks = new ArrayList<Rank>();
		pairRanks.add(Rank.Ace);
		OnePairPokerHand hand1 = new OnePairPokerHand(
				new int[] {}, pairRanks);
		hand1.compare(new PokerHand(Type.one_pair, new int[] {}) {
			
			@Override
			protected int compareSameTypeHands(PokerHand otherHand) {
				return 0;
			}
		});
	}
	
	@Test
	public void test_compare_high_pair_wins() {
		// 2 pair : 2 nines
		Card[] highPair1Cards = cardsFromStringArray(
				new String[]{"C9","D9","S4","H5","H3"});
		// 2 pair : 2 10s
		Card[] lowPair1Cards = cardsFromStringArray(
				new String[]{"C10","D10","S3","H2","H12"}); // hand 2 wins
		
		OnePairPokerHand hand1 = new OnePairPokerHand(
				PokerUtils.getRankFrequencies(highPair1Cards),
				PokerUtils.getPairRanks(highPair1Cards));
		
		OnePairPokerHand hand2 = new OnePairPokerHand(
				PokerUtils.getRankFrequencies(lowPair1Cards),
				PokerUtils.getPairRanks(lowPair1Cards));
		
		Assert.assertTrue(hand1.compare(hand2) < 0);
	}
	
	
	@Test
	public void test_compare_same_pairs_high_kicker_wins() {
		// 2 pair : 2 nines ,  kickers = 6,5,3
		Card[] lowKickerCards = cardsFromStringArray(
				new String[]{"C9","D9","S6","H5","H3"});
		// 2 pair : 2 nines, 2 fives, kicker = 7,5,4
		Card[] highKickerCards = cardsFromStringArray(
				new String[]{"S9","H9","C7","D5","H4"}); // hand 2 wins
		
		OnePairPokerHand hand1 = new OnePairPokerHand(
				PokerUtils.getRankFrequencies(lowKickerCards),
				PokerUtils.getPairRanks(lowKickerCards));
		
		OnePairPokerHand hand2 = new OnePairPokerHand(
				PokerUtils.getRankFrequencies(highKickerCards),
				PokerUtils.getPairRanks(highKickerCards));
		
		Assert.assertTrue(hand1.compare(hand2) < 0);
	}
	
	@Test
	public void test_compare_same_pairs_same_kicker_draw() {
		// 2 pair : 2 nines , kickers = 3,5,2
		Card[] cards1 = cardsFromStringArray(
				new String[]{"C9","D9","S3","H5","H2"});
		// 2 pair : 2 nines, kickers = 3,5,2
		Card[] cards2 = cardsFromStringArray(
				new String[]{"S9","H9","C3","D5","C2"}); //draw
		
		OnePairPokerHand hand1 = new OnePairPokerHand(
				PokerUtils.getRankFrequencies(cards1),
				PokerUtils.getPairRanks(cards1));
		
		OnePairPokerHand hand2 = new OnePairPokerHand(
				PokerUtils.getRankFrequencies(cards2),
				PokerUtils.getPairRanks(cards2));
		
		Assert.assertTrue(hand1.compare(hand2) == 0);
	}
	
}
