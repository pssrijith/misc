/**
 * 
 */
package poker.hands;

import org.junit.Assert;
import org.junit.Test;

import cardgames.deck.Card;
import cardgames.deck.Rank;
import poker.BaseTest;
import poker.PokerUtils;
import poker.hands.FullHousePokerHand;
import poker.hands.PokerHand;
import poker.hands.PokerHand.Type;

/**
 * @author srijith
 *
 */
public class FullHousePokerHandTest extends BaseTest {
	
	@Test
	public void test_createFullHousePokerHand() {
		Card[] fullHouseCards = cardsFromStringArray(
				new String[]{"C9","D9","S9","H3","H3"});
		FullHousePokerHand hand = new FullHousePokerHand(
				PokerUtils.getRankFrequencies(fullHouseCards), 
				PokerUtils.getThreeOfAKindRank(fullHouseCards), 
				PokerUtils.getPairRanks(fullHouseCards).get(0));
		
		Assert.assertNotNull(hand);
		Assert.assertEquals(PokerHand.Type.full_house, hand.getType());
		Assert.assertEquals(Rank.fromVal(9),hand.getKind3Rank());
		Assert.assertEquals(Rank.fromVal(3), hand.getPairRank());
		
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void test_createFullHousePokerHand_nullkind3Rank() {
		Card[] fullHouseCards = cardsFromStringArray(
				new String[]{"C9","D9","S9","H3","H3"});
		new FullHousePokerHand(
				PokerUtils.getRankFrequencies(fullHouseCards),null, 
				PokerUtils.getPairRanks(fullHouseCards).get(0));
		
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void test_createFullHousePokerHand_nullPairRank() {
		Card[] fullHouseCards = cardsFromStringArray(
				new String[]{"C9","D9","S9","H3","H3"});
		new FullHousePokerHand(
				PokerUtils.getRankFrequencies(fullHouseCards), 
				PokerUtils.getThreeOfAKindRank(fullHouseCards), 
				null);
		
	}
	
	@Test
	public void test_compare_fullHouse_kind3_higher_wins() {
		Card[] higherKind3Cards = cardsFromStringArray(
				new String[]{"C9","D9","S9","H3","H3"});
		Card[] lowerKind3Cards = cardsFromStringArray(
				new String[]{"C7","D7","S7","H3","H3"});
		FullHousePokerHand hand1 = new FullHousePokerHand(
				PokerUtils.getRankFrequencies(higherKind3Cards), 
				PokerUtils.getThreeOfAKindRank(higherKind3Cards), 
				PokerUtils.getPairRanks(higherKind3Cards).get(0));
		FullHousePokerHand hand2 = new FullHousePokerHand(
				PokerUtils.getRankFrequencies(lowerKind3Cards), 
				PokerUtils.getThreeOfAKindRank(lowerKind3Cards), 
				PokerUtils.getPairRanks(lowerKind3Cards).get(0));
		
		Assert.assertTrue(hand1.compare(hand2) > 0);
		
	}

	@Test
	public void test_compare_fullHouse_pair_higher_wins() {
		Card[] higherPairCards = cardsFromStringArray(
				new String[]{"C9","D9","S9","H10","H10"});
		Card[] lowerPairCards = cardsFromStringArray(
				new String[]{"H9","C9","D9","H3","H3"});
		FullHousePokerHand hand1 = new FullHousePokerHand(
				PokerUtils.getRankFrequencies(higherPairCards), 
				PokerUtils.getThreeOfAKindRank(higherPairCards), 
				PokerUtils.getPairRanks(higherPairCards).get(0));
		FullHousePokerHand hand2 = new FullHousePokerHand(
				PokerUtils.getRankFrequencies(lowerPairCards), 
				PokerUtils.getThreeOfAKindRank(lowerPairCards), 
				PokerUtils.getPairRanks(lowerPairCards).get(0));
		
		Assert.assertTrue(hand1.compare(hand2) > 0);
	}
	
	@Test
	public void test_compare_fullHouse_draw() {
		Card[] cards1 = cardsFromStringArray(
				new String[]{"C9","D9","S9","H10","H10"});
		Card[] cards2 = cardsFromStringArray(
				new String[]{"H9","C9","D9","H10","H10"});
		FullHousePokerHand hand1 = new FullHousePokerHand(
				PokerUtils.getRankFrequencies(cards1), 
				PokerUtils.getThreeOfAKindRank(cards1), 
				PokerUtils.getPairRanks(cards1).get(0));
		FullHousePokerHand hand2 = new FullHousePokerHand(
				PokerUtils.getRankFrequencies(cards2), 
				PokerUtils.getThreeOfAKindRank(cards2), 
				PokerUtils.getPairRanks(cards2).get(0));
		
		Assert.assertTrue(hand1.compare(hand2) == 0);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void test_compare_invalid_class() {
		
		FullHousePokerHand hand1 = new FullHousePokerHand(
				new int[] {}, Rank.Eight, Rank.King);
		hand1.compare(new PokerHand(Type.full_house, new int[] {}) {
			
			@Override
			protected int compareSameTypeHands(PokerHand otherHand) {
				return 0;
			}
		});
	}
}
