package me.rafique.poker_hand;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/** Description of class. */
// @Slf4j
public class Main {

  public static void main(String[] args) throws IOException {

    int player1 = 0;
    int player2 = 0;

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    String line;

    while ((line = br.readLine()) != null) {

      PokerSet set = Dealer.deal(line, 5, 2);
      Hand winner = set.getWinningPlayer();

      if (winner == set.getPlayer1()) player1++;
      else if (winner == set.getPlayer2()) player2++;
    }

    System.out.println("Player 1: " + player1);
    System.out.println("Player 2: " + player2);
  }
}
