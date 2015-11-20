/**
 * 
 */
package poker;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import cardgames.deck.Card;
import cardgames.deck.Rank;

/**
 * @author srijith
 *
 */
public class PokerUtilsTest extends BaseTest {

	@Test
	public void test_checkIsFlush() {
		
		Card[] flushHand = cardsFromStringArray(
						new String[] {"S10","S2","S5","S6","S14"});
		Assert.assertTrue(PokerUtils.checkIsFlush(flushHand));
		
		Card[] nonFlushHand = cardsFromStringArray(
				new String[] {"S10","S8","S13","S14","C10"});
		Assert.assertFalse(PokerUtils.checkIsFlush(nonFlushHand));
	}
	
	@Test
	public void test_getRankFrequencies() {
		Card[] cards = 
				cardsFromStringArray(new String[] {"S10","C10","D10","S13","S14"});
		Assert.assertArrayEquals(new int[] {0,0,0,0,0,0,0,0,0,0,3,0,0,1,1},
				PokerUtils.getRankFrequencies(cards));
	}
	
	@Test
	public void test_getHighStraight() {
		Card[] nonStraightHand =cardsFromStringArray(
				new String[] {"S10","C10","D10","S13","S14"});
		Assert.assertEquals(-1, PokerUtils.getHighStraight(nonStraightHand));
		
		Card[] aceHighStraight = cardsFromStringArray(
				new String[] {"S10","S11","S12","S13","S14"});
		Assert.assertEquals(Rank.Ace.getVal(), 
				PokerUtils.getHighStraight(aceHighStraight));
		
		Card[] babyStraight = cardsFromStringArray(
				new String[] {"S14","S2","S3","S4","S5"});
		Assert.assertEquals(Rank.Five.getVal(), 
				PokerUtils.getHighStraight(babyStraight));
		
		Card[] kingHighStraight = cardsFromStringArray(
				new String[] {"S10","S11","S12","S13","S9"});
		Assert.assertEquals(Rank.King.getVal(), 
				PokerUtils.getHighStraight(kingHighStraight));
	}
	
	
	@Test
	public void test_isStraight() {
		Card[] nonStraightHand =cardsFromStringArray(
				new String[] {"S10","C10","D10","S13","S14"});
		Assert.assertFalse(PokerUtils.isStraight(nonStraightHand));
		
		Card[] aceHighStraight = cardsFromStringArray(
				new String[] {"S10","S11","S12","S13","S14"});
		Assert.assertTrue(PokerUtils.isStraight(aceHighStraight));
		
		Card[] babyStraight = cardsFromStringArray(
				new String[] {"S14","S2","S3","S4","S5"});
		Assert.assertTrue(PokerUtils.isStraight(babyStraight));
		
		Card[] kingHighStraight = cardsFromStringArray(
				new String[] {"S10","S11","S12","S13","S9"});
		Assert.assertTrue(PokerUtils.isStraight(kingHighStraight));
	}
	
	@Test
	public void test_getFourOfAKindRank() {
		Card[] kind4Cards = cardsFromStringArray(
				new String[] {"C8","D8","S8","H8","S5"});
		Rank kind4Rank = PokerUtils.getFourOfAKindRank(kind4Cards);
		Assert.assertNotNull(kind4Rank);
		Assert.assertEquals(8, kind4Rank.getVal());

		Card[] kind3Cards = cardsFromStringArray(
				new String[] {"C7","D8","S8","H8","S5"});
		Assert.assertNull(PokerUtils.getFourOfAKindRank(kind3Cards));
	}
	
	@Test
	public void test_getThreeOfAKindRank() {
		Card[] kind3Cards = cardsFromStringArray(
				new String[] {"C8","D8","S8","H7","S5"});
		Rank kind3Rank = PokerUtils.getThreeOfAKindRank(kind3Cards);
		Assert.assertNotNull(kind3Rank);
		Assert.assertEquals(8, kind3Rank.getVal());
		
		Card[] nonKind3Cards = cardsFromStringArray(
				new String[] {"C8","D8","S6","H7","S5"});
		Assert.assertNull(PokerUtils.getThreeOfAKindRank(nonKind3Cards));
	}
	
	@Test
	public void test_getPairRanks() {
		Card[] twoPairCards = cardsFromStringArray(
				new String[] {"C8","D8","S7","H7","S5"});
		List<Rank> pairRanks = PokerUtils.getPairRanks(twoPairCards);
		Assert.assertNotNull(pairRanks);
		Assert.assertEquals(2, pairRanks.size());
		Assert.assertEquals(8, pairRanks.get(0).getVal());
		Assert.assertEquals(7, pairRanks.get(1).getVal());
		
		Card[] onePairCards = cardsFromStringArray(
				new String[] {"C8","D8","S7","H6","S5"});
		pairRanks = PokerUtils.getPairRanks(onePairCards);
		Assert.assertNotNull(pairRanks);
		Assert.assertEquals(1, pairRanks.size());
		Assert.assertEquals(8, pairRanks.get(0).getVal());
		
		Card[] noPairCards = cardsFromStringArray(
				new String[] {"C8","D3","S7","H6","S5"});
		pairRanks = PokerUtils.getPairRanks(noPairCards);
		Assert.assertNotNull(pairRanks);
		Assert.assertEquals(0, pairRanks.size());
	}

}
