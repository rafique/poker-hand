package me.rafique.poker_hand;

import java.util.ArrayList;

// @Slf4j
public class Dealer {

  /**
   * Deals the number of cards among the player hands provided.
   *
   * @param cardString
   * @param noCards
   * @param noPlayers
   * @return
   */
  public static PokerSet deal(String cardString, int noCards, int noPlayers) {

    // log.debug("dealing cards : " + cardString);

    String[] split = cardString.split("\\s+");

    ArrayList<Card> cards = new ArrayList<Card>();

    for (String card : split) {
      cards.add(new Card(card));
    }

    Hand player1 = new Hand(cards.subList(0, noCards));
    Hand player2 = new Hand(cards.subList(noCards, noCards + noCards));

    PokerSet set = new PokerSet(player1, player2);

    return set;
  }
}
