package me.rafique.poker_hand;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
// @Slf4j
public class Hand implements Comparable<Hand> {

  private List<Card> cards;

  private Map<Value, Integer> valueMap = new HashMap<Value, Integer>();
  private Map<Suite, Integer> suiteMap = new HashMap<Suite, Integer>();

  /** Value of the highest card at hand */
  private Value highestCard;

  public Hand(List<Card> list) {
    this.cards = list;

    // sort the cards with value
    Collections.sort(this.cards);

    // Default to highest card value
    this.highestCard = cards.get(cards.size() - 1).getValue();

    for (Card card : this.cards) {
      Integer count = this.valueMap.getOrDefault(card.getValue(), 0);
      valueMap.put(card.getValue(), ++count);
    }

    for (Card card : this.cards) {
      Integer count = this.suiteMap.getOrDefault(card.getSuite(), 0);
      suiteMap.put(card.getSuite(), ++count);
    }
  }

  public int getNumberOfCards() {
    return cards.size();
  }

  public HandRank getRank() {

    if (isSameSuite() && isStraight()) {
      if (cards.get(0).getValue() == Value.TEN) return HandRank.ROYAL_FLUSH;
      else return HandRank.STRAIGHT_FLUSH;
    }

    if (hasFourOfAKind()) {
      return HandRank.FOUR_OF_A_KIND;
    }

    if (hasFullHouse()) {
      return HandRank.FULL_HOUSE;
    }

    if (isSameSuite()) {
      return HandRank.FLUSH;
    }

    if (hasFourOfAKind()) {
      return HandRank.FOUR_OF_A_KIND;
    }

    if (isStraight()) {
      return HandRank.STRAIGHT;
    }

    if (hasThreeOfAKind()) {
      return HandRank.THREE_OF_A_KIND;
    }

    if (hasTwoPairs()) {
      return HandRank.TWO_PAIRS;
    }

    if (hasPair()) {
      return HandRank.PAIR;
    }

    // Default is the lowest Rank

    HandRank rank = HandRank.HIGH_CARD;
    return rank;
  }

  private boolean isSameSuite() {

    for (Entry<Suite, Integer> entry : this.suiteMap.entrySet()) {
      if (entry.getValue() >= 5) {
        return true;
      }
    }

    return false;
  }

  /**
   * All five cards in consecutive value order
   *
   * @return
   */
  private boolean isStraight() {

    Card first = cards.get(0);
    Card last = cards.get(cards.size() - 1);

    if ((first.getValue().ordinal() + 4) == last.getValue().ordinal()) {
      return true;
    }

    return false;
  }

  /**
   * Three of a kind and a Pair
   *
   * @return
   */
  private boolean hasFullHouse() {

    if (hasTwoPairs() && hasThreeOfAKind()) {
      return true;
    }

    return false;
  }

  /**
   * Four cards of the same value
   *
   * @return
   */
  private boolean hasFourOfAKind() {

    for (Entry<Value, Integer> entry : this.valueMap.entrySet()) {
      if (entry.getValue() >= 4) {
        highestCard = entry.getKey();
        return true;
      }
    }

    return false;
  }

  /**
   * Three cards of the same value
   *
   * @return
   */
  private boolean hasThreeOfAKind() {

    for (Entry<Value, Integer> entry : this.valueMap.entrySet()) {
      if (entry.getValue() >= 3) {
        highestCard = entry.getKey();
        return true;
      }
    }

    return false;
  }

  private boolean hasTwoPairs() {

    // FIXME:
    List<Value> pairs = new ArrayList<>();
    for (Entry<Value, Integer> entry : this.valueMap.entrySet()) {
      if (entry.getValue() >= 2) {
        pairs.add(entry.getKey());
      }
    }

    Collections.sort(pairs);
    if (pairs.size() >= 2) {
      highestCard = pairs.get(pairs.size() - 1);
      return true;
    }
    return false;
  }

  /**
   * Two cards of same value
   *
   * @return
   */
  private boolean hasPair() {
    for (Entry<Value, Integer> entry : this.valueMap.entrySet()) {
      if (entry.getValue() >= 2) {
        highestCard = entry.getKey();
        return true;
      }
    }

    return false;
  }

  @Override
  public int compareTo(Hand other) {

    HandRank rank = getRank();
    HandRank oRank = other.getRank();

    if (rank != oRank) {
      // log.debug(rank + " rank trumps over " + oRank);
      return rank.compareTo(oRank);
    }

    //    log.debug("rank tied: " + rank);

    // equal rank
    int compared = getHighestCard().compareTo(other.getHighestCard());

    if (compared != 0) {
      //      log.debug(getHighestCard() + " <> " + other.getHighestCard());
      return compared;
    }

    //    log.debug("rank and high card both tied");

    // break tie with second/third highest cards

    for (int i = 4; i >= 0; i--) {

      compared = getCards().get(i).compareTo(other.getCards().get(i));

      if (compared != 0) {
        //        log.debug("tie broken");
        return compared;
      }
    }

    //    log.debug("final tie with rank and lowest cards");
    return 0;
  }

  private Value getHighestCard() {
    return highestCard;
  }
}
