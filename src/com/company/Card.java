package com.company;

public class Card implements Comparable<Card>{
    private Integer value;
    private Character suit;

    public Card(Integer value, Character suit) {
        this.value = value;
        this.suit = suit;
    }

    @Override
    public int compareTo(Card o) {
        return this.value.compareTo(o.value);
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Character getSuit() {
        return suit;
    }

    public void setSuit(Character suit) {
        this.suit = suit;
    }
}
