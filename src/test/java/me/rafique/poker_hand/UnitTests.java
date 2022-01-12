package me.rafique.poker_hand;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class UnitTests {

  @Test
  public void testSuites() throws Exception {
    assertThat(Suite.valueOf("S")).isEqualTo(Suite.S);
  }

  @Test
  public void testValue() throws Exception {
    assertThat(Value.fromValue("K")).isEqualTo(Value.KING);

    assertTrue(Value.ACE.compareTo(Value.KING) > 0);

    assertTrue(Value.JACK.compareTo(Value.KING) < 0);

    // assertEquals(Value.FIVE.compareTo(Value.FIVE), 0);
  }

  @Test
  public void testCard() throws Exception {

    Card card = new Card("4H");

    assertThat(card.getSuite()).isEqualTo(Suite.H);
    assertThat(card.getValue()).isEqualTo(Value.FOUR);

    card = new Card("TS");

    assertThat(card.getSuite()).isEqualTo(Suite.S);
    assertThat(card.getValue()).isEqualTo(Value.TEN);
  }

  @Test
  public void testHandRanking() throws Exception {

    // PokerSet set = Dealer.deal("4H 4C 6S 7S KD 2C 3S 9S 9D TD", 5, 2);

    PokerSet set = Dealer.deal("4H AC 6S 7S KD 2C 3S 9S 9D TD", 5, 2);
    assertEquals(set.getPlayer1().getNumberOfCards(), 5);
    assertEquals(set.getPlayer2().getNumberOfCards(), 5);

    // System.out.println(set);

    set = Dealer.deal("4H 4C 6S 7S KD 2C 3S 9S 9D TD", 5, 2);
    assertEquals(set.getPlayer1().getRank(), HandRank.PAIR);

    set = Dealer.deal("4H 4C 6S 6S KD 2C 3S 9S 9D TD", 5, 2);
    assertEquals(set.getPlayer1().getRank(), HandRank.TWO_PAIRS);

    set = Dealer.deal("4H 4C 6S 6H 6D 2C 3S 9S 9D TD", 5, 2);
    assertEquals(set.getPlayer1().getRank(), HandRank.FULL_HOUSE);

    set = Dealer.deal("4H 6C 6S 6H 6D 2C 3S 9S 9D TD", 5, 2);
    assertEquals(set.getPlayer1().getRank(), HandRank.FOUR_OF_A_KIND);

    set = Dealer.deal("4H 5C 6S 7H 8D 2C 3S 9S 9D TD", 5, 2);
    assertEquals(set.getPlayer1().getRank(), HandRank.STRAIGHT);

    set = Dealer.deal("4H 5H 6H 7H 8H 2C 3S 9S 9D TD", 5, 2);
    assertEquals(set.getPlayer1().getRank(), HandRank.STRAIGHT_FLUSH);

    set = Dealer.deal("TH JH QH KH AH 2C 3S 9S 9D TD", 5, 2);
    assertEquals(set.getPlayer1().getRank(), HandRank.ROYAL_FLUSH);

    set = Dealer.deal("2D 9C AS AH AC 3D 6D 7D TD QD", 5, 2);
    assertEquals(set.getPlayer2().getRank(), HandRank.FLUSH);
  }

  @Test
  public void testHandWinning() throws Exception {

    PokerSet set = Dealer.deal("4H 4C 6S 7S KD 2C 3S 9S 9D TD", 5, 2);
    assertEquals(set.getWinningPlayer(), set.getPlayer2());

    set = Dealer.deal("5D 8C 9S JS AC 2C 5C 7D 8S QH", 5, 2);
    assertEquals(set.getWinningPlayer(), set.getPlayer1());

    set = Dealer.deal("2D 9C AS AH AC 3D 6D 7D TD QD", 5, 2);
    assertEquals(set.getWinningPlayer(), set.getPlayer2());

    set = Dealer.deal("4D 6S 9H QH QC 3D 6D 7H QD QS", 5, 2);
    assertEquals(set.getWinningPlayer(), set.getPlayer1());

    set = Dealer.deal("2H 2D 4C 4D 4S 3C 3D 3S 9S 9D", 5, 2);
    assertEquals(set.getWinningPlayer(), set.getPlayer1());

    set = Dealer.deal("2H 2D 4C 4D 9S 3C 3D 3S 9S 9D", 5, 2);
    assertEquals(set.getWinningPlayer(), set.getPlayer2());

    set = Dealer.deal("2H 2D 4C 4D 9S 3C 3D 5D 5D 9D", 5, 2);
    assertEquals(set.getWinningPlayer(), set.getPlayer2());

    set = Dealer.deal("AH AD 4C 4D 9S 3C 3D 5D 5D 9D", 5, 2);
    assertEquals(set.getWinningPlayer(), set.getPlayer1());
  }
}
