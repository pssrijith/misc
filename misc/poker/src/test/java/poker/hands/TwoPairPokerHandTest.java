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
import poker.hands.PokerHand;
import poker.hands.TwoPairPokerHand;
import poker.hands.PokerHand.Type;

/**
 * @author srijith
 *
 */
public class TwoPairPokerHandTest extends BaseTest{
	
	@Test
	public void test_createTwoPairPokerHand() {
		Card[] pair2Cards = cardsFromStringArray(
				new String[]{"C9","D9","S4","H4","H5"});
		TwoPairPokerHand hand = new TwoPairPokerHand(
				PokerUtils.getRankFrequencies(pair2Cards), 
				PokerUtils.getPairRanks(pair2Cards));
		
		Assert.assertNotNull(hand);
		Assert.assertEquals(PokerHand.Type.two_pair, hand.getType());
		
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void test_createTwoPairPokerHand_null_pairRanks() {
		Card[] pair2Cards = cardsFromStringArray(
				new String[]{"C9","D9","S2","H2","H3"});
		new TwoPairPokerHand(
				PokerUtils.getRankFrequencies(pair2Cards),null);
		
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void test_createTwoPairPokerHand_single_pairRanks() {
		Card[] pair1Card = cardsFromStringArray(
				new String[]{"C9","D9","S4","H2","H3"});
		new TwoPairPokerHand(
				PokerUtils.getRankFrequencies(pair1Card),
				PokerUtils.getPairRanks(pair1Card));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void test_compare_invalid_class() {
		List<Rank> pairRanks = new ArrayList<Rank>();
		pairRanks.add(Rank.Ace);
		pairRanks.add(Rank.Eight);
		TwoPairPokerHand hand1 = new TwoPairPokerHand(
				new int[] {}, pairRanks);
		hand1.compare(new PokerHand(Type.two_pair, new int[] {}) {
			
			@Override
			protected int compareSameTypeHands(PokerHand otherHand) {
				return 0;
			}
		});
	}
	
	@Test
	public void test_compare_high_pair1_wins() {
		// 2 pair : 2 nines , 2 fives
		Card[] highPair1Cards = cardsFromStringArray(
				new String[]{"C9","D9","S5","H5","H3"});
		// 2 pair : 2 10s, 2 twos
		Card[] lowPair1Cards = cardsFromStringArray(
				new String[]{"C10","D10","S2","H2","H3"}); // hand 2 wins
		
		TwoPairPokerHand hand1 = new TwoPairPokerHand(
				PokerUtils.getRankFrequencies(highPair1Cards),
				PokerUtils.getPairRanks(highPair1Cards));
		
		TwoPairPokerHand hand2 = new TwoPairPokerHand(
				PokerUtils.getRankFrequencies(lowPair1Cards),
				PokerUtils.getPairRanks(lowPair1Cards));
		
		Assert.assertTrue(hand1.compare(hand2) < 0);
	}
	
	@Test
	public void test_compare_same_pair1_high_pair2_wins() {
		// 2 pair : 2 nines , 2 fives
		Card[] highPair2Cards = cardsFromStringArray(
				new String[]{"C9","D9","S5","H5","H3"});// hand 1 wins
		// 2 pair : 2 nines, 2 twos
		Card[] lowPair2Cards = cardsFromStringArray(
				new String[]{"S9","H9","D2","H2","H3"}); 
		
		TwoPairPokerHand hand1 = new TwoPairPokerHand(
				PokerUtils.getRankFrequencies(highPair2Cards),
				PokerUtils.getPairRanks(highPair2Cards));
		
		TwoPairPokerHand hand2 = new TwoPairPokerHand(
				PokerUtils.getRankFrequencies(lowPair2Cards),
				PokerUtils.getPairRanks(lowPair2Cards));
		
		Assert.assertTrue(hand1.compare(hand2) > 0);
	}
	
	@Test
	public void test_compare_same_pairs_high_kicker_wins() {
		// 2 pair : 2 nines , 2 fives, kicker = 3
		Card[] lowKickerCards = cardsFromStringArray(
				new String[]{"C9","D9","S5","H5","H3"});
		// 2 pair : 2 nines, 2 fives, kicker = 4
		Card[] highKickerCards = cardsFromStringArray(
				new String[]{"S9","H9","C5","D5","H4"}); // hand 2 wins
		
		TwoPairPokerHand hand1 = new TwoPairPokerHand(
				PokerUtils.getRankFrequencies(lowKickerCards),
				PokerUtils.getPairRanks(lowKickerCards));
		
		TwoPairPokerHand hand2 = new TwoPairPokerHand(
				PokerUtils.getRankFrequencies(highKickerCards),
				PokerUtils.getPairRanks(highKickerCards));
		
		Assert.assertTrue(hand1.compare(hand2) < 0);
	}
	
	@Test
	public void test_compare_same_pairs_same_kicker_draw() {
		// 2 pair : 2 nines , 2 fives, kicker = 2
		Card[] lowKickerCards = cardsFromStringArray(
				new String[]{"C9","D9","S5","H5","H2"});
		// 2 pair : 2 nines, 2 fives, kicker = 2
		Card[] highKickerCards = cardsFromStringArray(
				new String[]{"S9","H9","C5","D5","C2"}); //draw
		
		TwoPairPokerHand hand1 = new TwoPairPokerHand(
				PokerUtils.getRankFrequencies(lowKickerCards),
				PokerUtils.getPairRanks(lowKickerCards));
		
		TwoPairPokerHand hand2 = new TwoPairPokerHand(
				PokerUtils.getRankFrequencies(highKickerCards),
				PokerUtils.getPairRanks(highKickerCards));
		
		Assert.assertTrue(hand1.compare(hand2) == 0);
	}
	
}
