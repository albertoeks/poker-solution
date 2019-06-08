package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class Hand {
    private List<Card> cards;

    public List<Card> getCards() {
        return cards;
    }

    public Hand(String hand) {
        StringTokenizer st = new StringTokenizer(hand, " ");
        cards = new ArrayList<>();
        while (st.hasMoreTokens()) {
            String card = st.nextToken();
            String val = card.substring(0, 1);
            Integer value = 0;
            if (val.equalsIgnoreCase("T")) {
                value = 10;
            } else if (val.equalsIgnoreCase("J")) {
                value = 11;
            } else if (val.equalsIgnoreCase("Q")) {
                value = 12;
            } else if (val.equalsIgnoreCase("K")) {
                value = 13;
            } else if (val.equalsIgnoreCase("A")) {
                value = 14;
            } else {
                value = Integer.parseInt(val);
            }
            cards.add(new Card(value, card.substring(1, 2).charAt(0)));
        }
    }

    public Integer getRank() {
        cards = cards.stream().sorted().collect(Collectors.toList());

        int pairIndex = -1;

        Integer rank = 1;

        //check for pair
        for (int i = 0; i < 4; i++) {
            if (cards.get(i).getValue().equals(cards.get(i + 1).getValue())) {
                pairIndex = i;
                rank = 2;
                i = 4;
            }
        }

        //check for 2 pair
        if (rank == 2) {
            for (int i = pairIndex + 2; i < 4; i++) {
                if (cards.get(i).getValue().equals(cards.get(i + 1).getValue())) {
                    rank = 3;
                }
            }
        }
        //check for 3 of a kind or full house
        for (int i = 0; i < 3; i++) {
            if (cards.get(i).getValue().equals(cards.get(i + 1).getValue()) &&
                    cards.get(i + 1).getValue().equals(cards.get(i + 2).getValue())) {
                rank = 4;
                if (i == 0 && cards.get(3).getValue().equals(cards.get(4).getValue()) ||
                        i == 2 && cards.get(0).getValue().equals(cards.get(1).getValue())) {
                    rank = 7;
                }
            }
        }

        //check for 4 of a kind
        for (int i = 0; i < 2; i++)
            if (cards.get(i).getValue().equals(cards.get(i + 1).getValue()) &&
                    cards.get(i + 1).getValue().equals(cards.get(i + 2).getValue()) &&
                    cards.get(i + 2).getValue().equals(cards.get(i + 3).getValue())) {
                rank = 8;
            }

        //check for straight
        if (rank == 1)
            if ((cards.get(4).getValue() - cards.get(0).getValue() == 4) ||
                    (cards.get(3).getValue() - cards.get(0).getValue() == 3 &&
                            cards.get(4).getValue() == 14 && cards.get(0).getValue() == 2)) {
                rank = 5;
            }

        //check for flush
        boolean flush;
        if (rank == 1 || rank == 5) {
            flush = true;
            for (int i = 0; i < 4; i++)
                if (cards.get(i).getSuit() != cards.get(i + 1).getSuit()) {
                    flush = false;
                }
            if (flush && rank == 5)
                rank = 9; //straight flush!
            else if (flush)
                rank = 6;
        }

        //check for royal flush
        if (rank == 9 && cards.get(4).getValue() == 14 && cards.get(0).getValue() == 10) {
            rank = 10;
        }

        return rank;
    }
}
