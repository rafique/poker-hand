package me.rafique.poker_hand;

/**
 * Ranks of a Poker hand.
 *
 * <p>A poker hand consists of a combination of five playing cards, ranked in the following
 * ascending order (lowest to highest):
 *
 * @author Rafique
 */
public enum HandRank {
  HIGH_CARD,
  PAIR,
  TWO_PAIRS,
  THREE_OF_A_KIND,
  STRAIGHT,
  FLUSH,
  FULL_HOUSE,
  FOUR_OF_A_KIND,
  STRAIGHT_FLUSH,
  ROYAL_FLUSH;
}
