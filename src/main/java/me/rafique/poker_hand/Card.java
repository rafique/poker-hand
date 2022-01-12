package me.rafique.poker_hand;

import lombok.Getter;

@Getter
public class Card implements Comparable<Card> {

  private Suite suite;
  private Value value;

  public Card(String string) {
    int length = string.length();
    if (length < 2 || length > 2) {
      throw new IllegalArgumentException("Invalid card " + string);
    }

    suite = Suite.valueOf("" + string.charAt(length - 1));
    value = Value.fromValue(string.substring(0, length - 1));
  }

  @Override
  public String toString() {
    return value.name() + "-of-" + suite.getTitle();
  }

  @Override
  public int compareTo(Card o) {
    return this.value.compareTo(o.getValue());
  }

  public int compare(Card other) {
    return value.compareTo(other.value);
  }
}
