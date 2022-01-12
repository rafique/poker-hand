package me.rafique.poker_hand;

import lombok.Getter;

@Getter
public enum Suite {
  D("Diamonds"),
  H("Hearts"),
  S("Spades"),
  C("Clubs");

  private String title;

  Suite(String title) {
    this.title = title;
  }

  @Override
  public String toString() {
    return this.title;
  }
}
