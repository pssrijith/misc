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
import poker.hands.FourOfAKindPokerHand;
import poker.hands.PokerHand;
import poker.hands.PokerHand.Type;

/**
 * @author srijith
 *
 */
public class FourOfAKindPokerHandTest extends BaseTest {
	
	@Test
	public void test_createKind4PokerHands() {
	
		Card[] cards = cardsFromStringArray(
				new String[] {"S10","C10","D10","H10","S14"});

		FourOfAKindPokerHand hand = 
				new FourOfAKindPokerHand(PokerUtils.getRankFrequencies(cards), 
						PokerUtils.getFourOfAKindRank(cards));
		
		Assert.assertEquals(PokerHand.Type.four_of_a_kind, hand.type);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test_createKind4PokerHands_nullKind4Rank() {
	
		Card[] cards = cardsFromStringArray(
				new String[] {"S10","C10","D10","H10","S14"});

		new FourOfAKindPokerHand(PokerUtils.getRankFrequencies(cards),null);
	}
	
	
	@Test
	public void test_compare_higher4Kind_wins() {
		Card[] lower4KindCards = cardsFromStringArray(
				new String[] {"S10","C10","D10","H10","S14"});
		FourOfAKindPokerHand lower4KindHand = 
				new FourOfAKindPokerHand(PokerUtils.getRankFrequencies(lower4KindCards), 
						PokerUtils.getFourOfAKindRank(lower4KindCards));
		
		Card[] higher4KindCards = cardsFromStringArray(
				new String[] {"S12","C12","D12","H12","S14"});
		FourOfAKindPokerHand higher4KindHand = 
				new FourOfAKindPokerHand(PokerUtils.getRankFrequencies(higher4KindCards), 
						PokerUtils.getFourOfAKindRank(higher4KindCards));
		
		Assert.assertTrue(lower4KindHand.compare(higher4KindHand) < 0);
		Assert.assertTrue(higher4KindHand.compare(lower4KindHand) > 0);
	}
	
	@Test
	public void test_compare_same4Kind_high_kicker_wins() {
		Card[] highKicker = cardsFromStringArray(
				new String[] {"S10","C10","D10","H10","S9"});
		FourOfAKindPokerHand highKickerHand = 
				new FourOfAKindPokerHand(PokerUtils.getRankFrequencies(highKicker), 
						PokerUtils.getFourOfAKindRank(highKicker));
		
		Card[] lowKicker = cardsFromStringArray(
				new String[] {"S10","C10","D10","H10","S5"});
		FourOfAKindPokerHand lowKickerHand = 
				new FourOfAKindPokerHand(PokerUtils.getRankFrequencies(lowKicker), 
						PokerUtils.getFourOfAKindRank(lowKicker));
		
		Assert.assertTrue(highKickerHand.compare(lowKickerHand) > 0);
	}
	
	@Test
	public void test_compare_same4Kind_same_kicker_draw() {
		Card[] cards1 = cardsFromStringArray(
				new String[] {"S10","C10","D10","H10","S5"});
		FourOfAKindPokerHand hand1 = 
				new FourOfAKindPokerHand(PokerUtils.getRankFrequencies(cards1), 
						PokerUtils.getFourOfAKindRank(cards1));
		
		Card[] cards2 = cardsFromStringArray(
				new String[] {"S10","C10","D10","H10","S5"});
		FourOfAKindPokerHand hand2 = 
				new FourOfAKindPokerHand(PokerUtils.getRankFrequencies(cards2), 
						PokerUtils.getFourOfAKindRank(cards2));
		
		Assert.assertTrue(hand1.compare(hand2) == 0);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void test_compare_invalid_class() {
		
		FourOfAKindPokerHand hand1 = new FourOfAKindPokerHand(new int[] {}, Rank.Eight);
		hand1.compare(new PokerHand(Type.four_of_a_kind, new int[] {}) {
			
			@Override
			protected int compareSameTypeHands(PokerHand otherHand) {
				return 0;
			}
		});
	}

}
