package com.company;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in, Charset.defaultCharset()))) {
            String line;
            List<Winner> winners = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                if(!line.isEmpty()) {
                    Hand h1 = new Hand(line.substring(0, 14));
                    Hand h2 = new Hand(line.substring(15));
                    winners.add(new Turn(h1, h2).getWinner());
                } else {
                    break;
                }
            }
            Long p1Wins = winners.stream().filter(winner -> winner == Winner.P1).count();
            Long p2Wins = winners.stream().filter(winner -> winner == Winner.P2).count();

            System.out.println(String.format("Player 1: [%d] hands" , p1Wins));
            System.out.println(String.format("Player 2: [%d] hands" , p2Wins));
        }
    }
}
