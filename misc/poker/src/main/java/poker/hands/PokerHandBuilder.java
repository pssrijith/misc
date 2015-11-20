/**
 * 
 */
package poker.hands;

import java.util.List;

import cardgames.deck.Card;
import cardgames.deck.Rank;
import poker.PokerUtils;

/**
 * Builder class that returns an appropriate PokerHands implementation
 * Uses rank frequency(rank histogram) to identify the poker hand type and creates the
 * appropriate PokerHand object
 * @author srijith
 */
public class PokerHandBuilder {
	
	private  final static PokerHandBuilder INSTANCE = new PokerHandBuilder();
	
	private PokerHandBuilder() {
		
	}
	
	public static PokerHandBuilder getInstance() {
		return INSTANCE;
	}
	
	public PokerHand build(Card[] cards) {
		
		if(cards.length != 5) {
			throw new IllegalArgumentException(
					"Bad input. Requires exactly 5 cards to make a pokerhand");
		}
		
		int[] rankFrequencies = PokerUtils.getRankFrequencies(cards);
		boolean isFlush = PokerUtils.checkIsFlush(cards);
		
		int highStraight= PokerUtils.getHighStraight(rankFrequencies);
		boolean isStraight = highStraight != -1;


		// #1 - Straight Flush
		if(isFlush && isStraight) {
			return new StraightPokerHand(PokerHand.Type.straight_flush,
					rankFrequencies, highStraight);
		}
		
		//#2 -  4 of a kind hand
		Rank kind4Rank = PokerUtils.getFourOfAKindRank(rankFrequencies);
		if(kind4Rank != null) {
			return new FourOfAKindPokerHand(rankFrequencies, kind4Rank);
		}
		
		//#3 -  Full House
		Rank kind3Rank = PokerUtils.getThreeOfAKindRank(rankFrequencies);
		List<Rank> pairRanks = PokerUtils.getPairRanks(rankFrequencies);
		if(kind3Rank != null && pairRanks.size() == 1) {
			return new FullHousePokerHand(
					rankFrequencies, kind3Rank, pairRanks.get(0));
		}
		
		//#4 - Flush
		if(isFlush) {
			return new FlushPokerHand(rankFrequencies);
		}
		
		// #5 - Straight
		if(isStraight) {
			return new StraightPokerHand(PokerHand.Type.straight, 
					rankFrequencies, highStraight);
		}
		
		//#6 - 3 of a kind
		if(kind3Rank != null) {
			return new ThreeOfAKindPokerHand(rankFrequencies, kind3Rank);
		}
		//#7 - 2 pairs
		if(pairRanks.size() == 2) {
			return new TwoPairPokerHand(rankFrequencies,pairRanks);
		}
		//#8 - 1 pair
		if(pairRanks.size() == 1) {
			return new OnePairPokerHand(rankFrequencies, pairRanks);
		}
		// #9 high card
		return new HighCardPokerHand(rankFrequencies);
	}
	

}
