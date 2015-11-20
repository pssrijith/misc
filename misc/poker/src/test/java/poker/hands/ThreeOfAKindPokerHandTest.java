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
import poker.hands.PokerHand;
import poker.hands.ThreeOfAKindPokerHand;
import poker.hands.PokerHand.Type;

/**
 * @author srijith
 *
 */
public class ThreeOfAKindPokerHandTest extends BaseTest {

	public void test_createThreeOfAKindPokerHand() {
		Card[] kind3Cards = cardsFromStringArray(
				new String[]{"C9","D9","S9","H4","H5"});
		ThreeOfAKindPokerHand hand = new ThreeOfAKindPokerHand(
				PokerUtils.getRankFrequencies(kind3Cards), 
				PokerUtils.getThreeOfAKindRank(kind3Cards));
		
		Assert.assertNotNull(hand);
		Assert.assertEquals(PokerHand.Type.three_of_a_kind, hand.getType());
		Assert.assertEquals(Rank.fromVal(9),hand.getKind3Rank());
		
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void test_createThreeOfAKindPokerHand_nullkind3Rank() {
		Card[] kind3Cards = cardsFromStringArray(
				new String[]{"C9","D9","S9","H2","H3"});
		new ThreeOfAKindPokerHand(
				PokerUtils.getRankFrequencies(kind3Cards),null);
		
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void test_compare_invalid_class() {
		
		ThreeOfAKindPokerHand hand1 = new ThreeOfAKindPokerHand(
				new int[] {}, Rank.Eight);
		hand1.compare(new PokerHand(Type.three_of_a_kind, new int[] {}) {
			
			@Override
			protected int compareSameTypeHands(PokerHand otherHand) {
				return 0;
			}
		});
	}
	
	@Test
	public void test_compare_higher_kind3_wins() {
		
		Card[] highKind3Cards = cardsFromStringArray(
				new String[]{"C9","D9","S9","H2","H3"});
		Card[] lowKind3Cards = cardsFromStringArray(
				new String[]{"S4","D4","H4","H2","H3"});
		
		ThreeOfAKindPokerHand hand1 = new ThreeOfAKindPokerHand(
				PokerUtils.getRankFrequencies(highKind3Cards),
				PokerUtils.getThreeOfAKindRank(highKind3Cards));
		ThreeOfAKindPokerHand hand2 = new ThreeOfAKindPokerHand(
				PokerUtils.getRankFrequencies(lowKind3Cards),
				PokerUtils.getThreeOfAKindRank(lowKind3Cards));
		
		Assert.assertTrue(hand1.compare(hand2) > 0);
	}

	@Test
	public void test_compare_same_kind3_high_card_wins() {
		
		Card[] cards1 = cardsFromStringArray(
				new String[]{"C9","D9","S9","H2","H4"});
		Card[] cards2 = cardsFromStringArray(
				new String[]{"S9","H9","C9","H3","H4"}); // wins H3 > H2
		
		ThreeOfAKindPokerHand hand1 = new ThreeOfAKindPokerHand(
				PokerUtils.getRankFrequencies(cards1),
				PokerUtils.getThreeOfAKindRank(cards1));
		ThreeOfAKindPokerHand hand2 = new ThreeOfAKindPokerHand(
				PokerUtils.getRankFrequencies(cards2),
				PokerUtils.getThreeOfAKindRank(cards2));
		
		Assert.assertTrue(hand1.compare(hand2) < 0);
	}
	

	@Test
	public void test_compare_same_kind3_same_cards_draw() {
		
		Card[] cards1 = cardsFromStringArray(
				new String[]{"C9","D9","S9","H2","H4"});
		Card[] cards2 = cardsFromStringArray(
				new String[]{"S9","H9","C9","H2","H4"}); 
		
		ThreeOfAKindPokerHand hand1 = new ThreeOfAKindPokerHand(
				PokerUtils.getRankFrequencies(cards1),
				PokerUtils.getThreeOfAKindRank(cards1));
		ThreeOfAKindPokerHand hand2 = new ThreeOfAKindPokerHand(
				PokerUtils.getRankFrequencies(cards2),
				PokerUtils.getThreeOfAKindRank(cards2));
		
		Assert.assertTrue(hand1.compare(hand2) == 0);
	}
}
