package com.company;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Turn {
    private Hand p1;
    private Hand p2;

    public Turn(Hand p1, Hand p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    public Winner getWinner() {
        int p1Sum = p1.getCards().stream().map(Card::getValue).reduce(0, (v1, v2) -> v1 + v2);
        int p2Sum = p2.getCards().stream().map(Card::getValue).reduce(0, (v1, v2) -> v1 + v2);
        if (p1.getRank() > p2.getRank()) {
            return Winner.P1;
        } else if (p1.getRank() < p2.getRank()) {
            return Winner.P2;
        } else {
            if (p1.getRank() == 10 && p2.getRank() == 10) {// this condition seems impossible.
                return Winner.PASS;
            } else if (p1.getRank() == 9 && p2.getRank() == 9) {//sum of all cards to compare
                return p1Sum > p2Sum ? Winner.P1 : Winner.P2;
            } else if (p1.getRank() == 8 && p2.getRank() == 8) {
                if (p1.getCards().get(0) == p1.getCards().get(3)) {
                    p1Sum = p1.getCards().get(0).getValue() + p1.getCards().get(1).getValue() + p1.getCards().get(2).getValue() + p1.getCards().get(3).getValue();
                } else {
                    p1Sum = p1.getCards().get(1).getValue() + p1.getCards().get(2).getValue() + p1.getCards().get(3).getValue() + p1.getCards().get(4).getValue();
                }

                if (p2.getCards().get(0) == p2.getCards().get(3)) {
                    p2Sum = p2.getCards().get(0).getValue() + p2.getCards().get(1).getValue() + p2.getCards().get(2).getValue() + p2.getCards().get(3).getValue();
                } else {
                    p2Sum = p2.getCards().get(1).getValue() + p2.getCards().get(2).getValue() + p2.getCards().get(3).getValue() + p2.getCards().get(4).getValue();
                }
                return p1Sum > p2Sum ? Winner.P1 : Winner.P2;
            } else if (p1.getRank() == 7 && p2.getRank() == 7) { //sum triples and pairs, if triples are equal then compare pairs.
                int p1Pair = 0;
                int p2Pair = 0;
                int p1Triple = 0;
                int p2Triple = 0;
                for (int i = 0; i < 3; i++) {
                    if (p1.getCards().get(i).getValue().equals(p1.getCards().get(i + 1).getValue()) &&
                            p1.getCards().get(i + 1).getValue().equals(p1.getCards().get(i + 2).getValue())) {
                        if (i == 0 && p1.getCards().get(3).getValue().equals(p1.getCards().get(4).getValue())) {
                            p1Pair = p1.getCards().get(3).getValue() + p1.getCards().get(4).getValue();
                            p1Triple = p1.getCards().get(0).getValue() + p1.getCards().get(1).getValue() + p1.getCards().get(2).getValue();
                        }
                        if (i == 2 && p1.getCards().get(0).getValue().equals(p1.getCards().get(1).getValue())) {
                            p1Pair = p1.getCards().get(0).getValue() + p1.getCards().get(1).getValue();
                            p1Triple = p1.getCards().get(2).getValue() + p1.getCards().get(3).getValue() + p1.getCards().get(4).getValue();
                        }
                    }
                    if (p2.getCards().get(i).getValue().equals(p2.getCards().get(i + 1).getValue()) &&
                            p2.getCards().get(i + 1).getValue().equals(p2.getCards().get(i + 2).getValue())) {
                        if (i == 0 && p2.getCards().get(3).getValue().equals(p2.getCards().get(4).getValue())) {
                            p2Pair = p2.getCards().get(3).getValue() + p2.getCards().get(4).getValue();
                            p2Triple = p2.getCards().get(0).getValue() + p2.getCards().get(1).getValue() + p2.getCards().get(2).getValue();
                        }
                        if (i == 2 && p2.getCards().get(0).getValue().equals(p2.getCards().get(1).getValue())) {
                            p2Pair = p2.getCards().get(0).getValue() + p2.getCards().get(1).getValue();
                            p2Triple = p2.getCards().get(2).getValue() + p2.getCards().get(3).getValue() + p2.getCards().get(4).getValue();
                        }
                    }
                }
                if(p1Triple == p2Triple){
                    return p1Pair > p2Pair ? Winner.P1 : Winner.P2;
                }
                return p1Triple > p2Triple ? Winner.P1 : Winner.P2;
            } else if (p1.getRank() == 6 && p2.getRank() == 6) { //sum all their values
                return p1Sum > p2Sum ? Winner.P1 : Winner.P2;
            } else if (p1.getRank() == 5 && p2.getRank() == 5) {// sum all their values
                return p1Sum > p2Sum ? Winner.P1 : Winner.P2;
            } else if (p1.getRank() == 4 && p2.getRank() == 4) {// sum the three cards
                int[] p1Remaining = new int[1];
                int[] p2Remaining = new int[1];
                if (p1.getCards().get(0).getValue() == (p1.getCards().get(2).getValue())) {
                    p1Sum = p1.getCards().get(0).getValue() + p1.getCards().get(1).getValue() + p1.getCards().get(2).getValue();
                    p1Remaining = new int[]{p1.getCards().get(3).getValue() , p1.getCards().get(4).getValue()};
                } else if (p1.getCards().get(1).getValue() == (p1.getCards().get(3).getValue())) {
                    p1Sum = p1.getCards().get(1).getValue() + p1.getCards().get(2).getValue() + p1.getCards().get(3).getValue();
                    p1Remaining = new int[]{p1.getCards().get(0).getValue() , p1.getCards().get(4).getValue()};
                } else if (p1.getCards().get(2).getValue() == (p1.getCards().get(4)).getValue()) {
                    p1Sum = p1.getCards().get(2).getValue() + p1.getCards().get(3).getValue() + p1.getCards().get(4).getValue();
                    p1Remaining = new int[]{p1.getCards().get(0).getValue() , p1.getCards().get(1).getValue()};
                }

                if (p2.getCards().get(0).getValue() == (p2.getCards().get(2).getValue())) {
                    p2Sum = p2.getCards().get(0).getValue() + p2.getCards().get(1).getValue() + p2.getCards().get(2).getValue();
                    p2Remaining = new int[]{p2.getCards().get(3).getValue() , p2.getCards().get(4).getValue()};
                } else if (p2.getCards().get(1).getValue() == (p2.getCards().get(3).getValue())) {
                    p2Sum = p2.getCards().get(1).getValue() + p2.getCards().get(2).getValue() + p2.getCards().get(3).getValue();
                    p2Remaining = new int[]{p2.getCards().get(0).getValue() , p2.getCards().get(4).getValue()};
                } else if (p2.getCards().get(2).getValue() == (p2.getCards().get(4).getValue())) {
                    p2Sum = p2.getCards().get(2).getValue() + p2.getCards().get(3).getValue() + p2.getCards().get(4).getValue();
                    p2Remaining = new int[]{p2.getCards().get(0).getValue() , p2.getCards().get(1).getValue()};
                }
                if(p1Sum == p2Sum){
                    int p1RemainingMax = Arrays.stream(p1Remaining).max().getAsInt();
                    int p1RemainingMin = Arrays.stream(p1Remaining).min().getAsInt();
                    int p2RemainingMax = Arrays.stream(p2Remaining).max().getAsInt();
                    int p2RemainingMin = Arrays.stream(p2Remaining).min().getAsInt();
                    if(p1RemainingMax == p2RemainingMax){
                        return p1RemainingMin > p2RemainingMin ? Winner.P1: Winner.P2;
                    } else {
                        return p1RemainingMax > p2RemainingMax ? Winner.P1: Winner.P2;
                    }
                } else {
                    return p1Sum > p2Sum ? Winner.P1 : Winner.P2;
                }
            } else if (p1.getRank() == 3 && p2.getRank() == 3) {
                int p1Remaining = 0, p1Pair1 = 0, p1Pair2 = 0;
                int p2Remaining = 0,p2Pair1 = 0, p2Pair2= 0;
                if (p1.getCards().get(0).getValue().equals(p1.getCards().get(1).getValue()) &&
                        p1.getCards().get(2).getValue().equals(p1.getCards().get(3).getValue())) {
                    p1Pair1 = p1.getCards().get(0).getValue() + p1.getCards().get(1).getValue();
                    p1Pair2 = p1.getCards().get(2).getValue() + p1.getCards().get(3).getValue();
                    p1Remaining = p1.getCards().get(4).getValue();
                } else if (p1.getCards().get(0).getValue().equals(p1.getCards().get(1).getValue()) &&
                        p1.getCards().get(3).getValue().equals(p1.getCards().get(4).getValue())) {
                    p1Pair1 = p1.getCards().get(0).getValue() + p1.getCards().get(1).getValue();
                    p1Pair2 = p1.getCards().get(3).getValue() + p1.getCards().get(4).getValue();
                    p1Remaining = p1.getCards().get(2).getValue();
                } else if (p1.getCards().get(1).getValue().equals(p1.getCards().get(2).getValue()) &&
                        p1.getCards().get(3).getValue().equals(p1.getCards().get(4).getValue())) {
                    p1Pair1 = p1.getCards().get(1).getValue() + p1.getCards().get(2).getValue() ;
                    p1Pair2 = p1.getCards().get(3).getValue() + p1.getCards().get(4).getValue();
                    p1Remaining = p1.getCards().get(0).getValue();
                }

                if (p2.getCards().get(0).getValue().equals(p2.getCards().get(1).getValue()) &&
                        p2.getCards().get(2).getValue().equals(p2.getCards().get(3).getValue())) {
                    p2Pair1 = p2.getCards().get(0).getValue() + p2.getCards().get(1).getValue() ;
                    p2Pair2 = p2.getCards().get(2).getValue() + p2.getCards().get(3).getValue();
                    p2Remaining = p2.getCards().get(4).getValue();
                } else if (p2.getCards().get(0).getValue().equals(p2.getCards().get(1).getValue()) &&
                        p2.getCards().get(3).getValue().equals(p2.getCards().get(4).getValue())) {
                    p2Pair1 = p2.getCards().get(0).getValue() + p2.getCards().get(1).getValue() ;
                    p2Pair2 = p2.getCards().get(3).getValue() + p2.getCards().get(4).getValue();
                    p2Remaining = p2.getCards().get(2).getValue();
                } else if (p2.getCards().get(1).getValue().equals(p2.getCards().get(2).getValue()) &&
                        p2.getCards().get(3).getValue().equals(p2.getCards().get(4).getValue())) {
                    p2Pair1 = p2.getCards().get(1).getValue() + p2.getCards().get(2).getValue() ;
                    p1Pair2 = p2.getCards().get(3).getValue() + p2.getCards().get(4).getValue();
                    p2Remaining = p2.getCards().get(0).getValue();
                }
                if(p1Pair1 == p2Pair1 && p1Pair2 == p2Pair2){
                    return p1Remaining > p2Remaining ? Winner.P1 : Winner.P2;
                } else if(p1Pair1 == p2Pair1 && p1Pair2 != p2Pair2) {
                    return p1Pair2 > p2Pair2 ? Winner.P1 : Winner.P2;
                } else if(p1Pair1 != p2Pair1 && p1Pair2 == p2Pair2){
                    return p1Pair1 > p2Pair1 ? Winner.P1 : Winner.P2;
                }
            } else if (p1.getRank() == 2 && p2.getRank() == 2) {
                int pairIndex = -1;
                //check for pair
                for (int i = 0; i < 4; i++) {
                    if (p1.getCards().get(i).getValue().equals(p1.getCards().get(i + 1).getValue())) {
                        pairIndex = i;
                        i = 4;
                    }
                }
                p1Sum = p1.getCards().get(pairIndex).getValue() + p1.getCards().get(pairIndex+1).getValue();
                p1.getCards().remove(pairIndex);
                p1.getCards().remove(pairIndex);
                List<Card> p1Remaining = p1.getCards().stream().sorted().collect(Collectors.toList());
                for (int i = 0; i < 4; i++) {
                    if (p2.getCards().get(i).getValue().equals(p2.getCards().get(i + 1).getValue())) {
                        pairIndex = i;
                        i = 4;
                    }
                }
                p2Sum = p2.getCards().get(pairIndex).getValue() + p2.getCards().get(pairIndex+1).getValue();
                p2.getCards().remove(pairIndex);
                p2.getCards().remove(pairIndex);
                List<Card> p2Remaining = p2.getCards().stream().sorted().collect(Collectors.toList());
                if(p1Sum == p2Sum){
                    for(int i = 0 ; i < 3 ; i++){
                        if(p1Remaining.get(i).getValue() != p2Remaining.get(i).getValue()){
                            return p1Remaining.get(i).getValue() > p2Remaining.get(i).getValue() ? Winner.P1 : Winner.P2;
                        }
                    }
                } else {
                    return p1Sum > p2Sum ? Winner.P1 : Winner.P2;
                }
            } else {
                return p1Sum > p2Sum ? Winner.P1 : Winner.P2;
            }
        }
        return Winner.PASS;
    }

    public static void main(String[] args) {
        String i1 = "4H 4C 6S 7S KD 2C 3S 9S 9D TD";// case 2
        Hand h1 = new Hand(i1.substring(0, 14));
        Hand h2 = new Hand(i1.substring(15));
        Turn t1 = new Turn(h1 , h2);
        System.out.println("Winner = " + (t1.getWinner() == Winner.P2 ? Winner.P2 : Winner.P1));

        String i2 = "5D 8C 9S JS AC 2C 5C 7D 8S QH"; //case 1
        h1 = new Hand(i2.substring(0, 14));
        h2 = new Hand(i2.substring(15));
        Turn t2 = new Turn(h1 , h2);
        System.out.println("Winner = " + (t2.getWinner() == Winner.P2 ? Winner.P2 : Winner.P1));

        String i3 = "2D 9C AS AH AC 3D 6D 7D TD QD"; //case 4
        h1 = new Hand(i3.substring(0, 14));
        h2 = new Hand(i3.substring(15));
        Turn t3 = new Turn(h1 , h2);
        System.out.println("Winner = " + (t3.getWinner() == Winner.P2 ? Winner.P2 : Winner.P1));

        String i4 = "4D 6S 9H QH QC 3D 6D 7H QD QS"; //case 2
        h1 = new Hand(i4.substring(0, 14));
        h2 = new Hand(i4.substring(15));
        Turn t4 = new Turn(h1 , h2);
        System.out.println("Winner = " + (t4.getWinner() == Winner.P2 ? Winner.P2 : Winner.P1));

        String i5 = "2H 2D 4C 4D 4S 3C 3D 3S 9S 9D"; // case 7
        h1 = new Hand(i5.substring(0, 14));
        h2 = new Hand(i5.substring(15));
        Turn t5 = new Turn(h1 , h2);
        System.out.println("Winner = " + (t5.getWinner() == Winner.P2 ? Winner.P2 : Winner.P1));

        String i6 = "2H 2D 4C 4D 8S 3C 3D 9S 9S 7D"; // case 3
        h1 = new Hand(i6.substring(0, 14));
        h2 = new Hand(i6.substring(15));
        Turn t6 = new Turn(h1 , h2);
        System.out.println("Winner = " + (t6.getWinner() == Winner.P2 ? Winner.P2 : Winner.P1));

        String i7 = "1H 2H 3H 4H 5H 2C 3C 4C 5C 6C"; // case 9
        h1 = new Hand(i7.substring(0, 14));
        h2 = new Hand(i7.substring(15));
        Turn t7 = new Turn(h1 , h2);
        System.out.println("Winner = " + (t7.getWinner() == Winner.P2 ? Winner.P2 : Winner.P1));

        String i8 = "4H 4H 4H 4H 5H 2C 2C 2C 2C 6C"; // case 8
        h1 = new Hand(i8.substring(0, 14));
        h2 = new Hand(i8.substring(15));
        Turn t8 = new Turn(h1 , h2);
        System.out.println("Winner = " + (t8.getWinner() == Winner.P2 ? Winner.P2 : Winner.P1));

        String i9 = "1H 2C 3H 4S 5C 2H 3C 4H 5S 6C"; // case 5
        h1 = new Hand(i9.substring(0, 14));
        h2 = new Hand(i9.substring(15));
        Turn t9 = new Turn(h1 , h2);
        System.out.println("Winner = " + (t9.getWinner() == Winner.P2 ? Winner.P2 : Winner.P1));

        String i10 = "1H 2H 3H 4H 5H 2S 3S 4S 5S 6S"; // case 6
        h1 = new Hand(i10.substring(0, 14));
        h2 = new Hand(i10.substring(15));
        Turn t10 = new Turn(h1 , h2);
        System.out.println("Winner = " + (t10.getWinner() == Winner.P2 ? Winner.P2 : Winner.P1));

        String i11 = "TH JH QH KH AH 2S 3S 4S 5S 6S"; // case 10
        h1 = new Hand(i11.substring(0, 14));
        h2 = new Hand(i11.substring(15));
        Turn t11 = new Turn(h1 , h2);
        System.out.println("Winner = " + (t11.getWinner() == Winner.P2 ? Winner.P2 : Winner.P1));
    }
}
