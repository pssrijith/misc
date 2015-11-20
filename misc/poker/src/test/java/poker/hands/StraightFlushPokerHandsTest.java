/**
 * 
 */
package poker.hands;

import org.junit.Assert;
import org.junit.Test;

import cardgames.deck.Card;
import poker.BaseTest;
import poker.PokerUtils;
import poker.hands.PokerHand;
import poker.hands.StraightPokerHand;

/**
 * @author srijith
 *
 */
public class StraightFlushPokerHandsTest extends BaseTest {
	
	@Test
	public void test_createStraightFlushPokerHands() {
	
		Card[] cards = cardsFromStringArray(
				new String[] {"S10","S11","S12","S13","S14"});

		StraightPokerHand hand = 
				new StraightPokerHand(PokerHand.Type.straight_flush, 
						PokerUtils.getRankFrequencies(cards), 
						PokerUtils.getHighStraight(cards));
		
		Assert.assertEquals(PokerHand.Type.straight_flush, hand.type);
		Assert.assertEquals(14, hand.getHighStraight());
	}
	
	@Test
	public void test_createStraightPokerHands() {
	
		Card[] cards = cardsFromStringArray(
				new String[] {"S10","S11","S12","S13","S14"});

		StraightPokerHand hand = 
				new StraightPokerHand(PokerHand.Type.straight, 
						PokerUtils.getRankFrequencies(cards), 
						PokerUtils.getHighStraight(cards));
		
		Assert.assertEquals(PokerHand.Type.straight, hand.type);
		Assert.assertEquals(14, hand.getHighStraight());
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void test_createStraightPokerHands_invalidType() {
	
		Card[] cards = cardsFromStringArray(
				new String[] {"S10","S11","S12","S13","S14"});

		new StraightPokerHand(PokerHand.Type.full_house, 
						PokerUtils.getRankFrequencies(cards), 
						PokerUtils.getHighStraight(cards));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test_createStraightPokerHands_nullType() {
	
		Card[] cards = cardsFromStringArray(
				new String[] {"S10","S11","S12","S13","S14"});
		new StraightPokerHand(null, 
						PokerUtils.getRankFrequencies(cards), 
						PokerUtils.getHighStraight(cards));
	}
	
	@Test
	public void test_compare_firstHand_wins() {
		Card[] royalStraight = cardsFromStringArray(
				new String[] {"S10","S11","S12","S13","S14"});
		
		Card[] babyStraight = cardsFromStringArray(
				new String[] {"C14","C5","C2","C3","C4"});
		
		StraightPokerHand royalStraightHand = 
				new StraightPokerHand(PokerHand.Type.straight_flush,
						PokerUtils.getRankFrequencies(royalStraight), 
						PokerUtils.getHighStraight(royalStraight));
		
		StraightPokerHand babyStraightHand = 
				new StraightPokerHand(PokerHand.Type.straight_flush,
						PokerUtils.getRankFrequencies(babyStraight), 
						PokerUtils.getHighStraight(babyStraight));
		Assert.assertTrue(royalStraightHand.compare(babyStraightHand) > 0);	
	}
	
	@Test
	public void test_compare_secondHand_wins() {
		
		
		Card[] babyStraight = cardsFromStringArray(
				new String[] {"C14","C5","C2","C3","C4"});
		Card[] straight = cardsFromStringArray(
				new String[] {"S2","S3","S4","S5","S6"});
		
		StraightPokerHand babyStraightHand = 
				new StraightPokerHand(PokerHand.Type.straight_flush,
						PokerUtils.getRankFrequencies(babyStraight), 
						PokerUtils.getHighStraight(babyStraight));
		
		StraightPokerHand straightHand = 
				new StraightPokerHand(PokerHand.Type.straight_flush,
						PokerUtils.getRankFrequencies(straight), 
						PokerUtils.getHighStraight(straight));
		
		
		Assert.assertTrue(babyStraightHand.compare(straightHand) < 0);	
	}
	
	
	@Test
	public void test_compare_draw() {
		Card[] spadesStraightFlush = cardsFromStringArray(
				new String[] {"S10","S11","S12","S13","S14"});
		
		Card[] clubsStraightFlush = cardsFromStringArray(
				new String[] {"C10","C11","C12","C13","C14"});
		
		StraightPokerHand spadesStraightFlushHand = 
				new StraightPokerHand(PokerHand.Type.straight_flush,
						PokerUtils.getRankFrequencies(spadesStraightFlush), 
						PokerUtils.getHighStraight(spadesStraightFlush));
		
		StraightPokerHand clubsStraightFlushHand = 
				new StraightPokerHand(PokerHand.Type.straight_flush,
						PokerUtils.getRankFrequencies(clubsStraightFlush), 
						PokerUtils.getHighStraight(clubsStraightFlush));
		
		Assert.assertEquals(0, spadesStraightFlushHand.compare(clubsStraightFlushHand));
		
	}
	
	@Test(expected= IllegalArgumentException.class)
	public void test_compare_invalidArgument() {
		
		Card[] spadesStraightFlush = cardsFromStringArray(
				new String[] {"S10","S11","S12","S13","S14"});
		
		StraightPokerHand spadesStraightFlushHand = 
				new StraightPokerHand(PokerHand.Type.straight_flush,
						PokerUtils.getRankFrequencies(spadesStraightFlush), 
						PokerUtils.getHighStraight(spadesStraightFlush));
		
		spadesStraightFlushHand.compare(new PokerHand(PokerHand.Type.straight_flush,
				PokerUtils.getRankFrequencies(spadesStraightFlush)) {

			@Override
			protected int compareSameTypeHands(PokerHand otherHand) {
				return 0;
			}
			
		});
	}

}
