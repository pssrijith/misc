package poker;

import java.util.ArrayList;
import java.util.List;

import cardgames.deck.Card;
import cardgames.deck.Rank;
import cardgames.deck.Suit;

/**
 * @author srijith
 *
 */
public class PokerUtils {

	
	/**
	 * @param cards
	 * @return
	 */
	public static boolean checkIsFlush(Card[] cards) {
		Suit suit = cards[0].getSuit();
		for(int i=1; i<cards.length; i++) {
			if(!suit.equals(cards[i].getSuit())) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * takes an array of cards and returns their rank frequency as an array of integers where 
	 * the i-th frequency corresponds to number of cards of rank i. e.g., rankFrequencies[10] 
	 * indicate number of cards with Rank 10 
	 * @param cards
	 * @return
	 */
	public static int[] getRankFrequencies(Card[] cards) {
		int[] rankFrequencies = new int[15]; // why 15 - to keep the index same as card. 0 and 1 index not used
		
		for(Card card : cards) {
			++rankFrequencies[card.getRank().getVal()];
		}
		return rankFrequencies;
	}
	
	/**
	 * Util function to get high straight from an array of cards.
	 * @param cards
	 * @return
	 */
	public static int getHighStraight(Card[] cards) {
		return getHighStraight(getRankFrequencies(cards));
	}

	/**
	 * if the rankCounts indicate a straight (5 consecutive ones) then return the high index of the straight
	 * if not a straight return -1;
	 * @param rankFrequencies
	 * @return
	 */
	 public static int getHighStraight(int[] rankFrequencies) {

		int consecutiveOnes = 0;
		int i=2, highStraight =-1;
		
		while(i< rankFrequencies.length && consecutiveOnes <5) {
			if(consecutiveOnes > 0 && rankFrequencies[i] != 1) {
				break; // not a straight
			}
			if(rankFrequencies[i] == 1) {
				++consecutiveOnes;
			}
			++i;
		}
		
		if(consecutiveOnes == 5) {
			highStraight =  i-1; // highStraight will be the i-1 th index
		} else if(rankFrequencies[Rank.Ace.getVal()] == 1 ){ // maybe we have A,2,3,4,5
			// check for A,2,3,4,5
			for(int j=2; j<=5; j++) {
				if(rankFrequencies[j]!=1) {
					return -1; // return -1 immediately - no possibility of straights
				}
			}
			highStraight = 5;
		}
		return highStraight;
	}

	/**
	 * @param cards
	 * @return
	 */
	public static boolean isStraight(Card[] cards) {
		return getHighStraight(cards) != -1;
	}


	
	/**
	 * @param rankFrequencies
	 * @param xOfAKind  - 4 for 4ofAKind , 3 for 3OfAKind
	 * @return
	 */
	private static Rank getXofAKindRank(int[] rankFrequencies, int xOfAKind ) {
		
		Rank kindXRank = null;
		for(int i=14; i>=2 ; i--) {
			if(rankFrequencies[i] == xOfAKind) {
				kindXRank = Rank.fromVal(i);
				break;
			}
		}
		return kindXRank;
	}
	
	/**
	 * @param cards
	 * @return
	 */
	public static Rank getFourOfAKindRank(int[] rankFrequencies) {
		return getXofAKindRank(rankFrequencies, 4);
	}
	/**
	 * @param cards
	 * @return
	 */
	public static Rank getFourOfAKindRank(Card[] cards) {
		return getFourOfAKindRank(getRankFrequencies(cards));
	}

	/**
	 * @param rankFrequencies
	 * @return
	 */
	public static Rank getThreeOfAKindRank(int[] rankFrequencies) {
		return  getXofAKindRank(rankFrequencies, 3);
	}
	
	/**
	 * @param cards
	 * @return
	 */
	public static Rank getThreeOfAKindRank(Card[] cards) {
		return getThreeOfAKindRank(getRankFrequencies(cards));
	}

	/**
	 * @param rankFrequencies
	 * @return
	 */
	public static List<Rank> getPairRanks(int[] rankFrequencies) {
		List<Rank> pairRanks = new ArrayList<Rank>();
		for(int i=14; i>=2; --i) {
			if(rankFrequencies[i] == 2)
				pairRanks.add(Rank.fromVal(i));
		}
		return pairRanks;
	}

	/**
	 * @param twoPairCards
	 * @return
	 */
	public static List<Rank> getPairRanks(Card[] cards) {
		return getPairRanks(getRankFrequencies(cards));
	}
	
	public static int compareHighCard(
			int[] hand1RankFrequencies, int[] hand2RankFrequencies) {
		int i=14, j=14;
		while(i>=2 && j>=2) {
			// we only look for cards with freq 1
			while(i>=2 && hand1RankFrequencies[i] != 1) 
				--i;
			
			while(j>=2 && hand2RankFrequencies[j] != 1)
				--j;
			
			if(i != j)
				return i-j; // we have found first card where the ranks don't match
			
			--i; --j;
		}
		
		return 0;
	}
}
