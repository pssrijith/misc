/**
 * 
 */
package poker;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import cardgames.deck.Card;

/**
 * @author srijith
 *
 */
public class PokerTest extends BaseTest {

	Poker poker;
	
	@Before
	public void setup() {
		poker = new Poker();
	}
	
	@Test
	public void test_compareHands_RoyalFlush_wins() {
		Card[] hand1 = cardsFromStringArray(new String[] {
				"S2","S3","S4","S5","S14"});
		Card[] hand2 = cardsFromStringArray(new String[] {
				"C10","C11","C12","C13","C14"});
		
		Poker.Result result = poker.compareHands(hand1,hand2);
		Assert.assertNotNull(result);
		Assert.assertEquals(Poker.Result.hand2Wins, result);
	}
	
	@Test
	public void test_compareHands_SteelWheel_wins() {
		// hand 1 - A,2,3,4,5 of spades (steel wheel)
		Card[] hand1 = cardsFromStringArray(new String[] {
				"S2","S3","S4","S5","S14"});//S14 -Spade Ace
		Card[] hand2 = cardsFromStringArray(new String[] {
				"C10","S10","D10","H10","C14"});//kind4Card with rank10
		
		Poker.Result result = poker.compareHands(hand1,hand2);
		Assert.assertNotNull(result);
		Assert.assertEquals(Poker.Result.hand1Wins, result);
	}
	
	@Test
	public void test_compareHands_RoyalFlush_draw() {
		// hand 1 - A,K,Q,J,10 of spades 
		Card[] hand1 = cardsFromStringArray(new String[] {
				"S14","S13","S12","S11","S10"});
		// hand 2 - A,K,Q,J,10 of clubs
		Card[] hand2 = cardsFromStringArray(new String[] {
				"C14","C13","C12","C11","C10"});
		
		Poker.Result result = poker.compareHands(hand1,hand2);
		Assert.assertNotNull(result);
		Assert.assertEquals(Poker.Result.draw, result);
	}
	
	@Test
	public void test_compareHands_RoyalFlush_wins_over_4ofAKind() {
		// hand 1 - A,K,Q,J,10 of spades 
		Card[] hand1 = cardsFromStringArray(new String[] {
				"S14","S13","S12","S11","S10"});
		// hand 2 - 4 Aces
		Card[] hand2 = cardsFromStringArray(new String[] {
				"C14","D14","S14","H14","C10"});
		
		Poker.Result result = poker.compareHands(hand1,hand2);
		Assert.assertNotNull(result);
		Assert.assertEquals(Poker.Result.hand1Wins, result);
	}
	
	@Test
	public void test_compareHands_4OfAKind_higher_rank_wins() {
		// hand 1 - 4 10 
		Card[] hand1 = cardsFromStringArray(new String[] {
				"C10","S10","D10","H10","S12"});
		// hand 2 - 4 Aces
		Card[] hand2 = cardsFromStringArray(new String[] {
				"C14","S14","D14","H14","C10"});
		
		Poker.Result result = poker.compareHands(hand1,hand2);
		Assert.assertNotNull(result);
		Assert.assertEquals(Poker.Result.hand2Wins, result);
	}
	
	@Test
	public void test_compareHands_4OfAKind_higher_kicker_wins() {
		// Note: this scenario happens only if we have 2 decks. 
		// hand 1 - 4 10, high Kicker - Queen 
		Card[] hand1 = cardsFromStringArray(new String[] {
				"C10","S10","D10","H10","S12"});
		// hand 2 - 4 10, low kicker - Jack
		Card[] hand2 = cardsFromStringArray(new String[] {
				"C10","S10","D10","H10","C11"});
		
		Poker.Result result = poker.compareHands(hand1,hand2);
		Assert.assertNotNull(result);
		Assert.assertEquals(Poker.Result.hand1Wins, result);
	}
	
	@Test
	public void test_compareHands_4OfAKind_draw() {
		// Note: this scenario happens only if we have 2 decks. 
		// hand 1 - 4 10, kicker - Queen 
		Card[] hand1 = cardsFromStringArray(new String[] {
				"C10","S10","D10","H10","S12"});
		// hand 2 - 4 10, kicker - Queen
		Card[] hand2 = cardsFromStringArray(new String[] {
				"C10","S10","D10","H10","C12"});
		
		Poker.Result result = poker.compareHands(hand1,hand2);
		Assert.assertNotNull(result);
		Assert.assertEquals(Poker.Result.draw, result);
	}
	
	@Test
	public void test_compareHands_4OfAKind_wins_over_fullHouse() {
		
		Card[] hand1 = cardsFromStringArray(new String[] {
				"C10","S10","D10","H10","S12"});
		Card[] hand2 = cardsFromStringArray(new String[] {
				"C8","S8","D8","H13","C13"});	
		
		Poker.Result result = poker.compareHands(hand1,hand2);
		Assert.assertNotNull(result);
		Assert.assertEquals(Poker.Result.hand1Wins, result);
	}
	
	@Test
	public void test_compareHands_fullHouse_kind3_higherRank_wins() {
		// hand 1 - 3 Rank7 cards, 2 Ace Cards
		Card[] hand1 = cardsFromStringArray(new String[] {
				"C7","S7","D7","H14","S14"});
		// hand 2 - 3 Rank8 cards, 2 King Cards
		Card[] hand2 = cardsFromStringArray(new String[] {
				"C8","S8","D8","H13","C13"});
		
		Poker.Result result = poker.compareHands(hand1,hand2);
		Assert.assertNotNull(result);
		Assert.assertEquals(Poker.Result.hand2Wins, result);
	}
	
	@Test
	public void test_compareHands_fullHouse_pairHigherRank_wins() {
		// hand 1 - 3 Rank7 cards, 2 Ace Cards
		Card[] hand1 = cardsFromStringArray(new String[] {
				"C7","S7","D7","H14","S14"});
		// hand 2 - 3 Rank7 cards, 2 King Cards
		Card[] hand2 = cardsFromStringArray(new String[] {
				"H7","C7","S7","H13","C13"});
		
		Poker.Result result = poker.compareHands(hand1,hand2);
		Assert.assertNotNull(result);
		Assert.assertEquals(Poker.Result.hand1Wins, result);
	}
	
	@Test
	public void test_compareHands_fullHouse_draw() {
		// hand 1 - 3 Rank7 cards, 2 Ace Cards
		Card[] hand1 = cardsFromStringArray(new String[] {
				"C7","S7","D7","H14","S14"});
		// hand 2 - 3 Rank7 cards, 2 Ace Cards
		Card[] hand2 = cardsFromStringArray(new String[] {
				"H7","C7","S7","D14","C14"});
		
		Poker.Result result = poker.compareHands(hand1,hand2);
		Assert.assertNotNull(result);
		Assert.assertEquals(Poker.Result.draw, result);
	}
	
	@Test
	public void test_compareHands_fullHouse_wins_overFlush() {
		// hand 1 - 3 Rank7 cards, 2 Ace Cards
		Card[] hand1 = cardsFromStringArray(new String[] {
				"C7","S7","D7","H8","S8"});
		// hand 2 - 3 Rank7 cards, 2 Ace Cards
		Card[] hand2 = cardsFromStringArray(new String[] {
				"H7","H8","H3","H11","H14"});
		
		Poker.Result result = poker.compareHands(hand1,hand2);
		Assert.assertNotNull(result);
		Assert.assertEquals(Poker.Result.hand1Wins, result);
	}
	
	@Test
	public void test_compareHands_flush_bothhands_highcard_wins() {
		
		Card[] hand1 = cardsFromStringArray(new String[] {
				"C7","C8","C3","C4","C10"});
		Card[] hand2 = cardsFromStringArray(new String[] {
				"D5","D6","D2","D13","D14"});
		
		Poker.Result result = poker.compareHands(hand1, hand2);
		Assert.assertNotNull(result);
		Assert.assertEquals(Poker.Result.hand2Wins, result);
	}
	
	@Test
	public void test_compareHands_flush_bothhands_draw() {
		
		Card[] hand1 = cardsFromStringArray(new String[] {
				"C7","C8","C3","C4","C10"});
		Card[] hand2 = cardsFromStringArray(new String[] {
				"S7","S8","S3","S4","S10"});
		
		Poker.Result result = poker.compareHands(hand1, hand2);
		Assert.assertNotNull(result);
		Assert.assertEquals(Poker.Result.draw, result);
	}
	
	@Test
	public void test_compareHands_flush_wins_over_straight() {
		
		Card[] hand1 = cardsFromStringArray(new String[] {
				"C7","C8","C3","C4","C10"});
		Card[] hand2 = cardsFromStringArray(new String[] {
				"H10","C11","S12","D13","C14"});
		
		Poker.Result result = poker.compareHands(hand1, hand2);
		Assert.assertNotNull(result);
		Assert.assertEquals(Poker.Result.hand1Wins, result);
	}
	
	@Test
	public void test_compareHands_straight_high_straight_wins() {
		// hand1 - ace high - A,K,Q,J,10
		Card[] hand1 = cardsFromStringArray(new String[] {
				"C14","H13","S12","C11","D10"});
		// hand 2 - 5 high - A,2,3,4,5
		Card[] hand2 = cardsFromStringArray(new String[] {
				"H5","C4","S3","D2","H14"});
		
		Poker.Result result = poker.compareHands(hand1, hand2);
		Assert.assertNotNull(result);
		Assert.assertEquals(Poker.Result.hand1Wins, result);
	}
	
	@Test
	public void test_compareHands_straight_draw() {
		Card[] hand1 = cardsFromStringArray(new String[] {
				"C7","H8","S9","C10","D11"});
		Card[] hand2 = cardsFromStringArray(new String[] {
				"H7","C8","H9","D10","H11"});
		
		Poker.Result result = poker.compareHands(hand1, hand2);
		Assert.assertNotNull(result);
		Assert.assertEquals(Poker.Result.draw, result);
	}
	
	@Test
	public void test_compareHands_flush_wins_over_3ofAKind() {
		
		Card[] hand1 = cardsFromStringArray(new String[] {
				"H10","C11","S12","D13","C14"});
		Card[] hand2 = cardsFromStringArray(new String[] {
				"S8", "H8","D8","S2","C7"});
		
		Poker.Result result = poker.compareHands(hand1, hand2);
		Assert.assertNotNull(result);
		Assert.assertEquals(Poker.Result.hand1Wins, result);
	}
	
	@Test
	public void test_compareHands_3ofAKind_high_kind3_wins() {
		
		Card[] hand1 = cardsFromStringArray(new String[] {
				"H10","C10","S10","D13","C14"});
		Card[] hand2 = cardsFromStringArray(new String[] {
				"S8", "H8","D8","S2","C7"});
		
		Poker.Result result = poker.compareHands(hand1, hand2);
		Assert.assertNotNull(result);
		Assert.assertEquals(Poker.Result.hand1Wins, result);
	}
	
	@Test
	public void test_compareHands_3ofAKind_same_kind3_high_card_wins() {
		
		Card[] hand1 = cardsFromStringArray(new String[] {
				"H10","C10","S10","D14","C3"});
		Card[] hand2 = cardsFromStringArray(new String[] {
				"S10", "H10","D10","C5","S14"});
		
		Poker.Result result = poker.compareHands(hand1, hand2);
		Assert.assertNotNull(result);
		Assert.assertEquals(Poker.Result.hand2Wins, result);
	}
	
	@Test
	public void test_compareHands_3ofAKind_draw() {
		
		Card[] hand1 = cardsFromStringArray(new String[] {
				"H10","C10","S10","D14","C3"});
		Card[] hand2 = cardsFromStringArray(new String[] {
				"S10","H10","D10","S14","C3"});
		
		Poker.Result result = poker.compareHands(hand1, hand2);
		Assert.assertNotNull(result);
		Assert.assertEquals(Poker.Result.draw, result);
	}
	
	@Test
	public void test_compareHands_3ofAKind_wins_over_2_pair() {
		/// 3 of a kind : 3 Tens
		Card[] hand1 = cardsFromStringArray(new String[] {
				"H10","C10","S10","D14","C3"});
		// 2 pair : 2 Tens, 2 Aces
		Card[] hand2 = cardsFromStringArray(new String[] {
				"S10","H10","D14","S14","C3"}); 
		
		Poker.Result result = poker.compareHands(hand1, hand2);
		Assert.assertNotNull(result);
		Assert.assertEquals(Poker.Result.hand1Wins, result);
	}
	
	@Test
	public void test_compareHands_2pair_high_pair1_wins() {
		// 2 pair - tens and aces
		Card[] hand1 = cardsFromStringArray(new String[] {
				"S10","H10","D14","S14","C3"}); 
		// 2 pair - Jacks and aces
		Card[] hand2 = cardsFromStringArray(new String[] {
				"C11","D11","D12","S12","C5"}); 
		
		Poker.Result result = poker.compareHands(hand1, hand2);
		Assert.assertNotNull(result);
		Assert.assertEquals(Poker.Result.hand1Wins, result);
	}
	
	@Test
	public void test_compareHands_2pair_high_pair2_wins() {
		Card[] hand1 = cardsFromStringArray(new String[] {
				"S10","H10","D14","S14","C3"}); 
		Card[] hand2 = cardsFromStringArray(new String[] {
				"S11","H11","C14","H14","C2"}); 
		
		Poker.Result result = poker.compareHands(hand1, hand2);
		Assert.assertNotNull(result);
		Assert.assertEquals(Poker.Result.hand2Wins, result);
	}
	
	@Test
	public void test_compareHands_2_pair_same_high_kicker_wins() {
		Card[] hand1 = cardsFromStringArray(new String[] {
				"S10","H10","D14","S14","C3"}); 
		Card[] hand2 = cardsFromStringArray(new String[] {
				"C10","D10","H14","C14","C2"}); 
		
		Poker.Result result = poker.compareHands(hand1, hand2);
		Assert.assertNotNull(result);
		Assert.assertEquals(Poker.Result.hand1Wins, result);
	}	

	@Test
	public void test_compareHands_2_pair_and_kicker_same_draw() {
		Card[] hand1 = cardsFromStringArray(new String[] {
				"S10","H10","D14","S14","C3"}); 
		Card[] hand2 = cardsFromStringArray(new String[] {
				"C10","D10","H14","C14","C3"}); 
		
		Poker.Result result = poker.compareHands(hand1, hand2);
		Assert.assertNotNull(result);
		Assert.assertEquals(Poker.Result.draw, result);
	}	
	
	@Test
	public void test_compareHands_2_pair_wins_over_1_pair() {
		// 1 pair  - 2 aces
		Card[] hand1 = cardsFromStringArray(new String[] {
				"S9","H10","D14","S14","C3"}); 
		// 2 pair : 2 Queens , 2 tens
		Card[] hand2 = cardsFromStringArray(new String[] {
				"C10","D10","H12","C12","C3"}); // hand 2 wins
		
		Poker.Result result = poker.compareHands(hand1, hand2);
		Assert.assertNotNull(result);
		Assert.assertEquals(Poker.Result.hand2Wins, result);
	}	
	
	@Test
	public void test_compareHands_1_pair_high_pair_wins() {
		//1pair : 2 tens
		Card[] hand1 = cardsFromStringArray(new String[] {
				"S10","H10","D12","S13","C3"}); 
		// 1pair : 2 nines
		Card[] hand2 = cardsFromStringArray(new String[] {
				"C9","D9","H14","C13","C2"}); 
		
		Poker.Result result = poker.compareHands(hand1, hand2);
		Assert.assertNotNull(result);
		Assert.assertEquals(Poker.Result.hand1Wins, result);
	}	
	
	@Test
	public void test_compareHands_1_pair_same_high_kicker_wins() {
		Card[] hand1 = cardsFromStringArray(new String[] {
				"S10","H10","D12","D13","C3"}); 
		Card[] hand2 = cardsFromStringArray(new String[] {
				"C10","D10","H14","C4","C2"}); 
		
		Poker.Result result = poker.compareHands(hand1, hand2);
		Assert.assertNotNull(result);
		Assert.assertEquals(Poker.Result.hand2Wins, result);
	}	

	@Test
	public void test_compareHands_1_pair_and_kicker_same_draw() {
		Card[] hand1 = cardsFromStringArray(new String[] {
				"S10","H10","D14","S12","C3"}); 
		Card[] hand2 = cardsFromStringArray(new String[] {
				"C10","D10","H14","C12","C3"}); 
		
		Poker.Result result = poker.compareHands(hand1, hand2);
		Assert.assertNotNull(result);
		Assert.assertEquals(Poker.Result.draw, result);
	}
	
	@Test
	public void test_compareHands_1_pair_wins_over_high_card() {
		// 1 pair - tens
		Card[] hand1 = cardsFromStringArray(new String[] {
				"S10","H10","D4","S2","C3"}); 
		// high card - ace
		Card[] hand2 = cardsFromStringArray(new String[] {
				"C14","D11","H10","C9","S3"}); 
		
		Poker.Result result = poker.compareHands(hand1, hand2);
		Assert.assertNotNull(result);
		Assert.assertEquals(Poker.Result.hand1Wins, result);
	}
	
	@Test
	public void test_compareHands_high_card_wins() {
		// 1 pair - tens
		Card[] hand1 = cardsFromStringArray(new String[] {
				"S10","H11","D4","S2","C3"}); 
		// high card - ace
		Card[] hand2 = cardsFromStringArray(new String[] {
				"C14","D11","H10","C9","S3"}); 
		
		Poker.Result result = poker.compareHands(hand1, hand2);
		Assert.assertNotNull(result);
		Assert.assertEquals(Poker.Result.hand2Wins, result);
	}
	
	@Test
	public void test_compareHands_high_card_same_rank_draw() {
		// 1 pair - tens
		Card[] hand1 = cardsFromStringArray(new String[] {
				"S10","H11","D4","S2","C3"}); 
		// high card - ace
		Card[] hand2 = cardsFromStringArray(new String[] {
				"C10","D11","H4","C2","S3"}); 
		
		Poker.Result result = poker.compareHands(hand1, hand2);
		Assert.assertNotNull(result);
		Assert.assertEquals(Poker.Result.draw, result);
	}
	
}
