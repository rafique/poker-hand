package me.rafique.poker_hand;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PokerSet {

  private Hand player1;

  private Hand player2;

  public PokerSet(Hand player1, Hand player2) {
    this.player1 = player1;
    this.player2 = player2;
  }

  public Hand getWinningPlayer() {

    int compare = player1.compareTo(player2);

    if (compare > 0) return player1;
    else if (compare < 0) return player2;

    // zero = tie
    return null;
  }
}
