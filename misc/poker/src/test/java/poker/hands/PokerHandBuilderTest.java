/**
 * 
 */
package poker.hands;

import org.junit.Assert;
import org.junit.Test;

import cardgames.deck.Card;
import poker.BaseTest;
import poker.hands.FourOfAKindPokerHand;
import poker.hands.FullHousePokerHand;
import poker.hands.PokerHand;
import poker.hands.PokerHandBuilder;
import poker.hands.StraightPokerHand;

/**
 * @author srijith
 *
 */
public class PokerHandBuilderTest extends BaseTest {
	
	private PokerHandBuilder builder = PokerHandBuilder.getInstance();
	
	@Test(expected=IllegalArgumentException.class)
	public void test_build_badInput() {
		
		builder.build(cardsFromStringArray(
				new String[]{"D10","D11","D12"}));
		
	}
	
	@Test
	public void test_build_royalStraightFlush() {
		
		Card[] royalStraightFlushCards = cardsFromStringArray(
				new String[]{"D10","D11","D12","D13","D14"});
		
		PokerHand hand = builder.build(royalStraightFlushCards);
		Assert.assertNotNull(hand);
		Assert.assertTrue(hand instanceof StraightPokerHand);
		
	}
	
	@Test
	public void test_build_babyStraightFlush() {
		
		Card[] babyStraightFlushCards = cardsFromStringArray(
				new String[]{"D2","D3","D4","D5","D14"});
		
		PokerHand hand = builder.build(babyStraightFlushCards);
		Assert.assertNotNull(hand);
		Assert.assertTrue(hand instanceof StraightPokerHand);	
	}
	
	@Test
	public void test_build_straightFlush() {
		
		Card[] straightFlushCards = cardsFromStringArray(
				new String[]{"D6","D3","D4","D5","D7"});
		
		PokerHand hand = builder.build(straightFlushCards);
		Assert.assertNotNull(hand);
		Assert.assertTrue(hand instanceof StraightPokerHand);
		
	}

	@Test
	public void test_build_4OfAKind() {
		
		Card[] kind4Cards = cardsFromStringArray(
				new String[]{"D6","H6","S6","C6","D7"});
		
		PokerHand hand = builder.build(kind4Cards);
		Assert.assertNotNull(hand);
		Assert.assertTrue(hand instanceof FourOfAKindPokerHand);
		
	}
	
	@Test
	public void test_build_full_house() {
		Card[] fullHouse = cardsFromStringArray(
				new String[]{"C10","D10","H10","S14","C14"});
		
		PokerHand hand = builder.build(fullHouse);
		Assert.assertNotNull(hand);
		Assert.assertTrue(hand instanceof FullHousePokerHand);
		
	}
}
